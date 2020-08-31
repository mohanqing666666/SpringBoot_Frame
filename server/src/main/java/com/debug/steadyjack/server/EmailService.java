package com.debug.steadyjack.server;

import com.debug.steadyjack.model.entity.Appendix;

import java.util.List;
import java.util.Map;

public interface EmailService {

     void sendSimpleMail( String subject,  String content,  String[] tos) throws Exception;

     void sendAttachmentMail(final String subject,final String content,final String[] tos) throws Exception;

     void sendHTMLMail(final String subject,final String content,final String[] tos) throws Exception;

     String renderTemplate(final String templateFile, Map<String,Object> paramMap) throws Exception;

     void sendAttachmentMail(final String subject, final String content, final String[] tos, final List<Appendix> appendixList) throws Exception;
}
