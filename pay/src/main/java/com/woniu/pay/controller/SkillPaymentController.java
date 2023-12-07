package com.woniu.pay.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.Pictures;
import com.woniu.pay.mapper.SkillMapper;
import com.woniu.pay.mapper.SkillPaymentMapper;
import com.woniu.pay.mapper.TestExamMapper;
import com.woniu.pay.service.SkillPaymentService;
import com.woniuxy.common.entity.DTO.SkillDTO;
import com.woniuxy.common.entity.ResponseResult;
import com.woniuxy.common.entity.Skill;
import com.woniuxy.common.entity.SkillExamPayment;
import com.woniuxy.common.entity.TestExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("skill")
public class SkillPaymentController {
    @Autowired
    SkillPaymentService skillPaymentService;
    @Autowired
    SkillMapper skillMapper;
    @Autowired
    SkillPaymentMapper skillPaymentMapper;
    @Autowired
    TestExamMapper testExamMapper;

    @RequestMapping("/getAll")
    public ResponseResult getAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 @RequestParam(defaultValue = "" ) String search){
        LambdaQueryWrapper<Skill> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Skill::getName,search);
        }
        Page<Skill> skillPage = skillMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new ResponseResult<>(200,"okk",skillPage);
//        Page<Skill> skillPage = new Page<>(pageNum, pageSize);
//        QueryWrapper<Skill> queryWrapper = new QueryWrapper<>();
//        Page<Skill> skills = skillMapper.selectPage(skillPage, queryWrapper);
//        return new ResponseResult<>(200,"okk",skills);
    }

    @RequestMapping("/decide")
    public ResponseResult decide(@RequestBody SkillDTO skillDTO, HttpServerRequest request){
        System.out.println(skillDTO);
        Integer eid=1001;
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        if (!skillDTO.getStudentId().equals(eid)){
            return new ResponseResult<>(409,"error","请本人进行报名");
        }else {
            SkillExamPayment skillExamPayment = new SkillExamPayment(null,null,eid,skillDTO.getName(),skillDTO.getAmount(),new Date(),null,"未缴费");
            //先去判断是否已经报过名，去skillExamPayment
            Integer counts = skillPaymentMapper.getCount(skillDTO.getStudentId(),skillDTO.getName());
            if (counts!=0){
                return new ResponseResult<>(407,"error","已参与过报名");
            }else{
                //如果是报名英语六级，要先验证是否已经通过英语四级
                if(skillDTO.getId()==2){
                    //拿学生id去cet4的表里面查，如果count(*)=0，则说明不能报英语六级，提示先考英语四级
                    Integer count=skillPaymentService.getMessage(skillDTO.getStudentId());
                    if (count==0){
                        return new ResponseResult<>(408,"error","请先通过四级考试");
                    }else{
                        skillPaymentService.add(skillExamPayment);
                        //减少对应的考试数量
                        skillPaymentService.reduce(skillDTO.getId());
                        return new ResponseResult<>(200,"ok","选择考试成功");
                    }
                }
                else {
                    skillPaymentService.add(skillExamPayment);
                    //减少对应的考试数量
                    skillPaymentService.reduce(skillDTO.getId());
                    return new ResponseResult<>(200,"ok","选择考试成功");
                }
            }
        }
    }

    @RequestMapping("/test")
    public String test(){
        return "test successfully";
    }

    @RequestMapping("/getMoney")
    public ResponseResult getMoney(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                   HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        Page<SkillExamPayment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkillExamPayment> queryWrapper = new QueryWrapper<>();
        QueryWrapper<SkillExamPayment> studentId1 = queryWrapper.eq("student_id", studentId);
        Page<SkillExamPayment> getMoney = skillPaymentMapper.selectPage(page, studentId1);
//        List<SkillExamPayment> getMoney=skillPaymentService.getMoney(studentId);
        return new ResponseResult<>(200,"ok",getMoney);
    }

    @RequestMapping("/cancel")
    public ResponseResult cancel(@RequestBody SkillExamPayment skillExamPayment){
        skillPaymentMapper.delete(skillExamPayment.getId());
        skillMapper.update(skillExamPayment.getType());
        return new ResponseResult<>(200,"取消成功","okk");
    }


    @RequestMapping("/look")
    public ResponseResult look(@RequestBody SkillExamPayment skillExamPayment){
        //通过学生id和和类型去查TestExam准考证
        QueryWrapper<TestExam> queryWrapper = new QueryWrapper<>();
        QueryWrapper<TestExam> wrapper = queryWrapper.eq("student_id", skillExamPayment.getStudentId()).eq("skill_name", skillExamPayment.getType());
        TestExam testExam = testExamMapper.selectOne(wrapper);
        return new ResponseResult<>(200,"取消成功",testExam);
    }

    @RequestMapping("/export")
    public ResponseResult export(@RequestBody TestExam testExam) throws IOException {
        String gender = null;
        if (testExam.getGender()==1){
            gender = "男";
        }else {
            gender = "女";
        }
        //word模板
        String path="D:\\ideaProjects\\stage04-samrtcampus\\pay\\src\\main\\resources\\doc\\exam.docx";
        XWPFTemplate template = XWPFTemplate.compile(path);//读取模板内容
        Map<Object, Object> map = new HashMap<>();//map里的key一定要跟模板中的名称一致
        //读取图片
        PictureRenderData img = Pictures.of("D:\\ideaProjects\\stage04-samrtcampus\\pay\\src\\main\\resources\\doc\\cgx.jpg").create();
        //存入map
        map.put("id",testExam.getTestId());
        map.put("name",testExam.getStudentName());
        map.put("gender",gender);
        map.put("img",img);
        map.put("age",testExam.getAge());
        map.put("number",testExam.getPhoneNumber());
        map.put("examName",testExam.getSkillName());
        map.put("examTime",testExam.getSkillTime());
        map.put("examPlace",testExam.getSkillPlace());
        template.render(map);
        String imgPath="D:\\ideaProjects\\stage04-samrtcampus\\pay\\src\\main\\resources\\doc\\"+testExam.getStudentName()+ "的" +testExam.getSkillName()+"准考证.docx";
        template.writeAndClose(Files.newOutputStream(Paths.get(imgPath)));
        return new ResponseResult<>(200,"okk","导出成功");
    }
}
