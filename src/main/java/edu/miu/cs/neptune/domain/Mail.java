package edu.miu.cs.neptune.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String contentType;

//    private List <Object> attachments;
    public Mail() {
        contentType = "text/plain";
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public Mail setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
        return this;
    }

    public String getMailTo() {
        return mailTo;
    }

    public Mail setMailTo(String mailTo) {
        this.mailTo = mailTo;
        return this;
    }

    public String getMailCc() {
        return mailCc;
    }

    public Mail setMailCc(String mailCc) {
        this.mailCc = mailCc;
        return this;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public Mail setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
        return this;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public Mail setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
        return this;
    }

    public String getMailContent() {
        return mailContent;
    }

    public Mail setMailContent(String mailContent) {
        this.mailContent = mailContent;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public Mail setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

//    public List<Object> getAttachments() {
//        return attachments;
//    }
//
//    public Mail setAttachments(List<Object> attachments) {
//        this.attachments = attachments;
//        return this;
//    }
}
