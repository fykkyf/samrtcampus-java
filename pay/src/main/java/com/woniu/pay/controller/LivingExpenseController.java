package com.woniu.pay.controller;



import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.pay.mapper.*;
import com.woniuxy.common.entity.*;
import com.woniuxy.common.entity.DTO.LivingExpenseDTO;
import com.woniuxy.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("live")
public class LivingExpenseController {
    @Autowired
    LivingExpenseMapper livingExpenseMapper;
    @Autowired
    BedroomMapper bedroomMapper;
    @Autowired
    BedroomMoneyMapper bedroomMoneyMapper;
    @Autowired
    BedroomStudentMapper bedroomStudentMapper;
    @Autowired
    StudentCardMapper studentCardMapper;

    //查的是生活用费账单
    @RequestMapping("/getAll")
    public ResponseResult getAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 HttpServerRequest request){
        ////        String token = request.getHeader("token");
////        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        Page<LivingExpense> page = new Page<>(pageNum, pageSize);
        QueryWrapper<LivingExpense> queryWrapper = new QueryWrapper<>();
        QueryWrapper<LivingExpense> studentId1 = queryWrapper.eq("student_id", studentId);
        Page<LivingExpense> getAll = livingExpenseMapper.selectPage(page, studentId1);
        return new ResponseResult<>(200,"ok",getAll);
    }


    //查的是寝室现在的余额
    @RequestMapping("/lookToRoom")
    public ResponseResult lookToRoom(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        //先通过学生id，查到他所属的寝室id
        QueryWrapper<BedroomStudent> queryWrapper = new QueryWrapper<>();
        QueryWrapper<BedroomStudent> query = queryWrapper.eq("student_id", studentId);
        BedroomStudent bedroomStudent = bedroomStudentMapper.selectOne(query);
        Integer bedroomId = bedroomStudent.getBedroomId();
        //再通过寝室id查出水电费
        QueryWrapper<BedroomMoney> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<BedroomMoney> query1 = queryWrapper1.eq("bedroom_id", bedroomId);
        BedroomMoney bedroomMoney = bedroomMoneyMapper.selectOne(query1);
        List<BedroomMoney> bedList = new ArrayList<>();
        bedList.add(bedroomMoney);
        return new ResponseResult<>(200,"okk",bedList);
    }

    @RequestMapping("/getLive")
    public ResponseResult getLive(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        List<LivingExpense> livingExpenses = livingExpenseMapper.selectByStudentId(studentId);
        return new ResponseResult<>(200,"okk",livingExpenses);
    }

    @RequestMapping("/getCard")
    public ResponseResult getCard(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        List<LivingExpense> livingExpenses = livingExpenseMapper.selectByStudentIdAndStatus(studentId);
        return new ResponseResult<>(200,"okk",livingExpenses);
    }

    @RequestMapping("/insert")
    public ResponseResult insert(@RequestBody LivingExpenseDTO livingExpenseDTO, HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        UUID uuid = UUID.randomUUID();
        // 将UUID转换为无符号的长整数字符串
        String uuidStr = Long.toUnsignedString(uuid.getMostSignificantBits());
        uuidStr += Long.toUnsignedString(uuid.getLeastSignificantBits());
        String substring = uuidStr.substring(0, 9);
        Integer nineSub= Integer.valueOf(substring);
        System.out.println("转换后的字符串： " + uuidStr);

        // 将字符串转换为整数
//        Integer uuidInt = Integer.parseInt(uuidStr, 9);
//        System.out.println("转换后的整数： " + uuidInt);

        LivingExpense livingExpense = new LivingExpense(nineSub,null,studentId,livingExpenseDTO.getBedroomId(), livingExpenseDTO.getCardId(),livingExpenseDTO.getType(), livingExpenseDTO.getAmount(), null);
        System.out.println("对象是："+livingExpense);
        livingExpenseMapper.insert(livingExpense);
        return new ResponseResult<>(200,"添加成功","okk");
    }

    @RequestMapping("/getCardMoney")
    public ResponseResult getCardMoney(HttpServerRequest request){
//        String token = request.getHeader("token");
//        Integer eid = Integer.valueOf(JwtUtil.getEid(token));
        Integer eid=1001;
        Integer studentId=eid;
        QueryWrapper<StudentCard> queryWrapper = new QueryWrapper<>();
        QueryWrapper<StudentCard> query = queryWrapper.eq("student_id", studentId);
        List<StudentCard> studentCards = studentCardMapper.selectList(query);
        return new ResponseResult<>(200,"okk",studentCards);
    }

    @RequestMapping("/lost")
    public ResponseResult lost(@RequestBody StudentCard studentCard){
        studentCardMapper.updateStatus(studentCard);
        return new ResponseResult<>(200,"okk","okk");
    }

    @RequestMapping("/reissue")
    public ResponseResult reissue(@RequestBody StudentCard studentCard){
        UUID uuid = UUID.randomUUID();
        // 将UUID转换为无符号的长整数字符串
        String uuidStr = Long.toUnsignedString(uuid.getMostSignificantBits());
        uuidStr += Long.toUnsignedString(uuid.getLeastSignificantBits());
        String substring = uuidStr.substring(0, 8);
        Integer sevenSub= Integer.valueOf(substring);
        //创建新的饭卡
        Double cardBalance = studentCard.getCardBalance();
        Double last = cardBalance-20;
        Integer studentId = studentCard.getStudentId();
        StudentCard studentCard1 = new StudentCard(null,studentId,sevenSub,last,"启用");
        studentCardMapper.insert(studentCard1);
        //修改老饭卡的数据
        Integer cardId = studentCard.getCardId();
        studentCardMapper.updateCauseReissue(cardId);
        return new ResponseResult<>(200,"okk","补办成功");
    }

}
