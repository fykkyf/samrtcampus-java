package com.woniu.pay.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.pay.common.AliPay;
import com.woniu.pay.common.AliPayConfig;
import com.woniu.pay.mapper.*;
import com.woniuxy.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// xjlugv6874@sandbox.com
// 9428521.24 - 30 = 9428491.24 + 30 = 9428521.24
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AliPayConfig aliPayConfig;
    @Autowired
    SkillPaymentMapper skillPaymentMapper;
    @Autowired
    TestExamMapper testExamMapper;
    @Autowired
    SkillMapper skillMapper;
    @Autowired
    LivingExpenseMapper livingExpenseMapper;
    @Autowired
    BedroomMoneyMapper bedroomMoneyMapper;
    @Autowired
    BedroomStudentMapper bedroomStudentMapper;
    @Autowired
    StudentCardMapper studentCardMapper;
    @Autowired
    TuitionFeeMapper tuitionFeeMapper;
    @Autowired
    AcademicPaymentMapper academicPaymentMapper;

//    @Resource
//    private OrdersMapper ordersMapper;

    @Value("${alipay.returnUrl}")
    private String returnUrl;
    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        System.out.println("进来了吗？");

        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
//        request.setReturnUrl(aliPayConfig.getReturnUrl());
        returnUrl=aliPay.getReturnUrl();
        aliPayConfig.setReturnUrl(returnUrl);
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("return_url",aliPay.getReturnUrl());//支付完成后，我们要回跳的界面
        System.out.println(aliPay.getReturnUrl());
        bizContent.set("out_trade_no", aliPay.getTraceNo());  // 我们自己生成的订单编号
        System.out.println(aliPay.getTraceNo());
        bizContent.set("total_amount", aliPay.getTotalAmount()); // 订单的总金额
        System.out.println(aliPay.getTotalAmount());
        bizContent.set("subject", aliPay.getSubject());   // 支付的名称
        System.out.println(aliPay.getSubject());
//        request.setReturnUrl(aliPay.getReturnUrl());
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        System.out.println("这里进来了吗？？？？");
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            Integer outTradeNo = Integer.valueOf(params.get("out_trade_no"));
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));




                //判断是哪个地方发过来的付款
                if(params.get("subject").equals("水费")||params.get("subject").equals("电费")){
//                    String token = request.getHeader("token");
//                    Integer eid = Integer.valueOf(JwtUtil.getEid(token));
                    System.out.println("我是水电费，进来了吗");
                    Integer eid=1001;
                    Integer studentId=eid;
                    //查到寝室id
                    QueryWrapper<BedroomStudent> queryWrapper = new QueryWrapper<>();
                    QueryWrapper<BedroomStudent> query = queryWrapper.eq("student_id", studentId);
                    BedroomStudent bedroomStudent = bedroomStudentMapper.selectOne(query);
                    Integer bedroomId = bedroomStudent.getBedroomId();
                    //修改living_expense中的数据
                    //通过订单号查找出
                    LivingExpense livingExpense = livingExpenseMapper.get(outTradeNo);
                    livingExpense.setAlipayNo(alipayTradeNo);
                    livingExpense.setPayTime(params.get("gmt_payment"));
                    livingExpenseMapper.update(livingExpense);
                    if (params.get("subject").equals("水费")){
                        System.out.println("我是水费，快点来");
                        bedroomMoneyMapper.updateWaterBalance(Double.parseDouble(params.get("total_amount")),bedroomId);
                    } else if (params.get("subject").equals("电费")) {
                        System.out.println("我是电费费，快点来");
                        bedroomMoneyMapper.updateElectricityBalance(Double.parseDouble(params.get("total_amount")),bedroomId);
                    }
                } else if (params.get("subject").equals("英语四级")||params.get("subject").equals("英语六级")
                        ||params.get("subject").equals("普通话")||params.get("subject").equals("计算机")
                        ||params.get("subject").equals("教室资格证")||params.get("subject").equals("导游证")
                        ||params.get("subject").equals("会计师证")||params.get("subject").equals("心理咨询师证")) {
                    //支付之后的一系列操作
                    //通过订单号查找出来
                    SkillExamPayment skillExamPayment = skillPaymentMapper.get(outTradeNo);
                    System.out.println("对象数据:"+skillExamPayment);
                    skillExamPayment.setAlipayNo(alipayTradeNo);
                    skillExamPayment.setPayTime(params.get("gmt_payment"));
                    System.out.println("付款时间111:"+skillExamPayment.getPayTime());
                    System.out.println("付款单号111:"+skillExamPayment.getAlipayNo());
                    skillPaymentMapper.pay(skillExamPayment);
                    // 构建查询条件
                    QueryWrapper<Skill> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("name", skillExamPayment.getType());
                    Skill skill = skillMapper.selectOne(queryWrapper);
                    System.out.println(skill);
                    //获得skill属性中的值
                    Integer id = skill.getId();
                    String name = skill.getName();
                    Date time = skill.getTime();
                    String place = skill.getPlace();
                    //获取学生的信息
                    Student student = skillPaymentMapper.findBySid(skillExamPayment.getStudentId());
                    System.out.println(student.getStudentName());
                    Integer studentId=skillExamPayment.getStudentId();
                    String studentName = student.getStudentName();
                    Integer age = student.getAge();
                    Integer gender = student.getGender();
                    String phoneNumber = student.getPhoneNumber();
                    testExamMapper.insert(new TestExam(null,studentId,studentName,age,gender,phoneNumber,id,name,time,place));
                } else if (params.get("subject").equals("饭卡")) {
                    //修改living_expense中的数据
                    //通过订单号查找出
                    LivingExpense livingExpense = livingExpenseMapper.get(outTradeNo);
                    livingExpense.setAlipayNo(alipayTradeNo);
                    livingExpense.setPayTime(params.get("gmt_payment"));
                    livingExpenseMapper.update(livingExpense);
                    QueryWrapper<LivingExpense> wrapper = new QueryWrapper<>();
                    QueryWrapper<LivingExpense> aaa = wrapper.eq("id",outTradeNo);
                    LivingExpense livingExpense1 = livingExpenseMapper.selectOne(aaa);
                    Integer cardId = livingExpense1.getCardId();
                    studentCardMapper.updateBalance(Double.parseDouble(params.get("total_amount")),cardId);
                } else if (params.get("subject").equals("学费")) {
                    Tuition tuition = tuitionFeeMapper.get(outTradeNo);
                    tuition.setAlipayNo(alipayTradeNo);
                    tuition.setPayTime(params.get("gmt_payment"));
                    tuitionFeeMapper.payStudyMoney(tuition);
                } else if (params.get("subject").equals("重修")||params.get("subject").equals("选修")) {
                    AcademicPayment academicPayment = academicPaymentMapper.get(outTradeNo);
                    academicPayment.setAlipayNo(alipayTradeNo);
                    academicPayment.setPayTime(params.get("gmt_payment"));
                    academicPaymentMapper.payMoney(academicPayment);
                }


                //支付之后的一系列操作
//                SkillExamPayment skillExamPayment = skillPaymentMapper.get(outTradeNo);
//                System.out.println("对象数据:"+skillExamPayment);
//                skillExamPayment.setAlipayNo(alipayTradeNo);
//                skillExamPayment.setPayTime(params.get("gmt_payment"));
//                System.out.println("付款时间111:"+skillExamPayment.getPayTime());
//                System.out.println("付款单号111:"+skillExamPayment.getAlipayNo());
//                skillPaymentMapper.pay(skillExamPayment);
//
//                // 构建查询条件
//                QueryWrapper<Skill> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("name", skillExamPayment.getType());
//                Skill skill = skillMapper.selectOne(queryWrapper);
//
//                System.out.println(skill);
//
//                //获得skill属性中的值
//                Integer id = skill.getId();
//                String name = skill.getName();
//                Date time = skill.getTime();
//                String place = skill.getPlace();
//                //获取学生的信息
//                Student student = skillPaymentMapper.findBySid(skillExamPayment.getStudentId());
//                System.out.println(student.getStudentName());
//                Integer studentId=skillExamPayment.getStudentId();
//                String studentName = student.getStudentName();
//                Integer age = student.getAge();
//                Integer gender = student.getGender();
//                Integer phoneNumber = student.getPhoneNumber();
//
//                testExamMapper.insert(new TestExam(null,studentId,studentName,age,gender,phoneNumber,id,name,time,place));
            }
        }
        return "success";
    }
}
