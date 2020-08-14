package com.tony.church.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class Mail {

    private String from;
    private List<String> mailTo;
    private String[] mailToArray;
    private String mailToListAsString;
    private String subject;
    //private List<Object> attachments;
    private Map<String, Object> props;
    private String message;
    private Boolean sendToAll;
    private int option;
    private MultipartFile[] attachments;

    public Mail() {}

    public MultipartFile[] getAttachments() {
        return attachments;
    }

    public void setAttachments(MultipartFile[] attachements) {
        this.attachments = attachements;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public Boolean getSendToAll() {
        return sendToAll;
    }

    public void setSendToAll(Boolean sendToAll) {
        this.sendToAll = sendToAll;
    }

    public String[] getMailToArray() {
        return mailToArray;
    }

    public void setMailToArray(String[] mailToArray) {
        this.mailToArray = mailToArray;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMailToListAsString() {
        return mailToListAsString;
    }

    public void setMailToListAsString(String mailToListAsString) {
        this.mailToListAsString = mailToListAsString;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getMailTo() {
        return mailTo;
    }

    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

//    public List<Object> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<Object> attachments) {
//        this.attachments = attachments;
//    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mailToListAsString='" + mailToListAsString + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
