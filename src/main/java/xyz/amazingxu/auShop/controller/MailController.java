package xyz.amazingxu.auShop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.amazingxu.auShop.dto.MailDTO;
import xyz.amazingxu.auShop.service.IMailService;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;

import javax.mail.MessagingException;

/**
 * @author xuwang < xuwang2@student.unimelb.edu.au >
 * @id 979895
 * @date 2018/9/10 19:59
 */
@Api(value = "mail", description = "邮件")
@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private IMailService mailService;

    @ApiOperation(value = "发送一封简易邮件")
    @PostMapping("sendMail")
    public WebResults sendMail(@RequestBody MailDTO mailDTO) throws MessagingException {
        mailService.sendMail(mailDTO);
        return WebUtils.success();
    }
}
