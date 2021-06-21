package com.hongma.demo.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWolrdController {

	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/hello-wolrd-internationalized")
	public String helloWolrdInternationalized ( @RequestHeader(name="Accept-Language", required = false) Locale locale) {
		
		return messageSource.getMessage("greeting.message", null, locale);
	}
}
