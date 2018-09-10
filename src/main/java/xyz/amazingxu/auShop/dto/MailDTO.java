package xyz.amazingxu.auShop.dto;

import java.util.Arrays;

/**
 * @author xuwang < xuwang2@student.unimelb.edu.au >
 * @id 979895
 * @date 2018/9/10 20:01
 */
public class MailDTO {
    @Override
    public String toString() {
        return "MailDTO{" +
                "to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    /**
     * 接收人邮箱数组
     */
    private String[] to;
    /**
     * 抄送人邮箱数组
     */
    private String[] cc;
    /**
     * 邮件标题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String text;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
