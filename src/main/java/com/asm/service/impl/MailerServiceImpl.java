package com.asm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asm.controller.client.MailInfo;
import com.asm.service.MailerService;



@Service
public class MailerServiceImpl implements MailerService{
	List<MailInfo> list = new ArrayList<>();
	
	
	@Autowired
	JavaMailSender sender;
	
	
	@Override
	public void send(MailInfo mail) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		
		helper.setFrom(mail.getForm());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		helper.setReplyTo(mail.getForm());
		
		String[] cc = mail.getCc();

		if(cc !=null && cc.length >0) {
			helper.setCc(cc);
		}
		
		String[] bcc = mail.getBcc();
		
		if(bcc !=null && bcc.length>0) {
			helper.setBcc(bcc);
		}
		
		String[] attachments = mail.getAttachments();
		if(attachments !=null && attachments.length>0) {
			
			for(String path : attachments) {
				File file = new File(path);
				
				helper.addAttachment(file.getName(), file);
			}
		}
		
		//gui message den smtp server
		sender.send(message);
	}


	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		this.send(new MailInfo(to,subject,body));
		
	}
	
	@Override
	public void queue(MailInfo mail) {
		// TODO Auto-generated method stub
		list.add(mail);
		
	}
	@Override
	public void queue(String to, String subject, String body) {
		// TODO Auto-generated method stub
		queue(new MailInfo(to,subject,body));
		
	}
	
	@Scheduled(fixedDelay = 5000)
	public void run() {
		
		while(!list.isEmpty()) {
			MailInfo mail =list.remove(0);
			try {
				this.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}	
	
	@Override
	public String layma(){
        /*
        :: la double colon, d??ng cho vi???c ??i t???t v?? gi??p cho bi???n kia g???i l??n ph????ng th???c tham chi???u ????;  nh?? StringBuilder::new th?? s??? t???o m???t new StringBuilder() 
       tr??nh vi???t new StringBuilder()v?? r???ng m???t void kh??ng th??? th??nh m???t bi???n ??? trong m???t h??m ???????c, n??n s??? d???ng :: ????? t???o ???????ng t???t ????? c?? th??? s??? d???ng nhanh ph????ng th???c
        tr??nh vi???c g???i l??n r?????m r??
        */
         int leftLimit = 48; // letter '0'
        int rightLimit = 122; // letter 'z'
        int len = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        return generatedString;
    }
}
