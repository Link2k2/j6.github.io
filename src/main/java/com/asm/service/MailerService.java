package com.asm.service;

import javax.mail.MessagingException;

import com.asm.controller.client.MailInfo;




public interface MailerService {
	// gui mail
	void send(MailInfo mail) throws MessagingException;
	
	//gui email don gian
	void send(String to, String subject, String body) throws MessagingException;
	
	//tao mail xep hang doi
	void queue(MailInfo mail);
	
	void queue(String to, String subject, String body);
	
	String layma();
}
