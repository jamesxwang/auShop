package xyz.amazingxu.auShop.service;

import xyz.amazingxu.auShop.dto.MailDTO;

/**
 * @author xuwang < xuwang2@student.unimelb.edu.au >
 * @id 979895
 * @date 2018/9/10 20:01
 */
public interface IMailService {
    void sendMail(MailDTO mailDTO) throws javax.mail.MessagingException;
}
