package com.debug.steadyjack.server.Impl;


import com.debug.steadyjack.model.entity.Appendix;
import com.debug.steadyjack.server.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger log= LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * 发送简单文本邮件
     * @param subject
     * @param content
     * @param tos
     */
    @Override
    public void sendSimpleMail(String subject, String content, String[] tos) throws Exception {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(env.getProperty("mail.send.from"));
        message.setTo(tos);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);

        log.info("发送简单文本邮件成功--->");

    }
    /**
     * 发送带附件文本邮件
     * @param subject
     * @param content
     * @param tos
     */
    @Override
    public void sendAttachmentMail(String subject, String content, String[] tos) throws Exception {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
        messageHelper.setFrom(env.getProperty("mail.send.from"));
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(content);

        //TODO：加入附件
        //TODO：跟进里面的源码会发现-当编码后的文件名长度如果大于60并且splitLongParameters的值为true - 可以实战测试!
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.one.name"),new File(env.getProperty("mail.send.attachment.one.location")));
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.two.name"),new File(env.getProperty("mail.send.attachment.two.location")));
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.three.name"),new File(env.getProperty("mail.send.attachment.three.location")));

        mailSender.send(mimeMessage);
        log.info("发送带附件文本邮件成功--->");
    }
    /**
     * 发送带HTML邮件
     * @param subject
     * @param content
     * @param tos
     */
    @Override
    public void sendHTMLMail(String subject, String content, String[] tos) throws Exception {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
        messageHelper.setFrom(env.getProperty("mail.send.from"));
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);

        //TODO：加入附件
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.one.name"),new File(env.getProperty("mail.send.attachment.one.location")));

        mailSender.send(mimeMessage);
        log.info("发送带HTML邮件成功--->");
    }
    /**
     * 渲染模板（采用freemarker模板发送邮件）
     * @param templateFile
     * @param paramMap
     */
//    @Override
//    public String renderTemplate(String templateFile, Map<String, Object> paramMap) throws Exception {
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
//        //TODO：设置去哪里读freemarker文件-目录
//        cfg.setClassForTemplateLoading(this.getClass(),"/ftl");
//        //TODO：根据你提供模板文件构建模板渲染实例
//        Template template = cfg.getTemplate(templateFile);
//        String html= FreeMarkerTemplateUtils.processTemplateIntoString(template,paramMap);
//        return html;
//    }

    /**
     * 渲染模板（采用thymeleaf模板发送邮件）
     * @param templateFile
     * @param paramMap
     */

    @Override
    public String renderTemplate(final String templateFile, Map<String,Object> paramMap){
        Context context=new Context(LocaleContextHolder.getLocale());
        context.setVariables(paramMap);
        return templateEngine.process(templateFile,context);
    }

    /**
     * 改造后的发送带附件的邮件
     * @param subject
     * @param content
     * @param tos
     * @param appendixList
     * @throws Exception
     */
    @Override
    public void sendAttachmentMail(final String subject, final String content, final String[] tos, final List<Appendix> appendixList) throws Exception{
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
        messageHelper.setFrom(env.getProperty("mail.send.from"));
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(content);

        for (Appendix a:appendixList){
            try {
                messageHelper.addAttachment(a.getName(),new File(env.getProperty("file.upload.root.url")+a.getLocation()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        mailSender.send(mimeMessage);
        log.info("发送带附件文本邮件V2成功--->");
    }

//    @PostConstruct
//    public void init(){
//        System.setProperty("mail.mime.splitlongparameters","false");
//    }

}
