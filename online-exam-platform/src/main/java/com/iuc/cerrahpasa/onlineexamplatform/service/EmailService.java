package com.iuc.cerrahpasa.onlineexamplatform.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
}
