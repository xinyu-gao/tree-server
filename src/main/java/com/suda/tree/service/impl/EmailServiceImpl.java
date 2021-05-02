package com.suda.tree.service.impl;

import cn.hutool.core.date.DateUtil;
import com.suda.tree.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendValidateCode(String emailAddress, String code) {
        MimeMessage message;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 发送人
            helper.setFrom(from);
            // 接收邮箱
            helper.setTo(emailAddress);
            // 标题
            helper.setSubject("系统验证码");
            // 带HTML格式的内容
            String text = "<p align=\"center\">尊敬的用户</p>" +
                    "<p align=\"center\">您正在尝试登录系统/注册账号，您的验证码为：</p>" +
                    "<h3 style=\"color: #0099CB;\" align=\"center\">" +
                    code +
                    "</h3>\n" +
                    "<p align=\"center\">如果您没有尝试此操作，请忽略此邮件。</p>" +
                    "<p align=\"center\">这封电子邮件由系统自动生成，请勿回复。如果您遇到问题，请咨询系统管理员。</p>";
            helper.setText(text, true);
            jms.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String sendWarnMessage(String emailAddress, String treeId, String data) {
        MimeMessage message;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 发送人
            helper.setFrom(from);
            // 接收邮箱
            helper.setTo(emailAddress);
            // 标题
            helper.setSubject("古树名木监测系统警报通知");
            // 带HTML格式的内容
            String text = "<p align=\"center\">尊敬的用户</p>" +
                    "<p align=\"center\">当前树木编号为“" + treeId + "”的监测信息异常，具体如下：</p>" +
                    "<h3 style=\"color: red;\" align=\"center\">" +
                    data +
                    "</h3>\n" +
                    "<p align=\"center\">请您尽快检查并处理异常。</p>" +
                    "<p align=\"center\">这封电子邮件由系统自动生成，请勿回复。如果您遇到问题，请咨询系统管理员。</p>";
            helper.setText(text, true);
            jms.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
