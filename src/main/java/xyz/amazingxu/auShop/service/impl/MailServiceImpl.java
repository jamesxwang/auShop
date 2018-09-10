package xyz.amazingxu.auShop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xyz.amazingxu.auShop.dto.MailDTO;
import xyz.amazingxu.auShop.service.IMailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author xuwang < xuwang2@student.unimelb.edu.au >
 * @id 979895
 * @date 2018/9/10 20:04
 */
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMail(MailDTO mailDTO) throws MessagingException {
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(from);
        message.setTo(mailDTO.getTo());
        message.setCc(mailDTO.getCc());
        message.setSubject(mailDTO.getSubject());
        message.setText(mailDTO.getText());
        this.mailSender.send(mimeMessage);
    }
}
