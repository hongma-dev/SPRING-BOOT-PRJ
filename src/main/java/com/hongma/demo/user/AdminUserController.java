package com.hongma.demo.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@RestController
@RequestMapping("/admin")
public class AdminUserController {

	private UserDAOService service;
	
	public AdminUserController(UserDAOService service) {
		this.service = service;
	}
	
	
	@GetMapping(path="/getUsers")
	public MappingJacksonValue getUsers(){
		
		List<User> users = service.getUsers();

		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","joinDate","password","ssn");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(users);
		
		jacksonValue.setFilters(filterProvider);
		
		return jacksonValue;
	}
	
	
//	@GetMapping(path="/v1/getUser/{id}/")
//	@GetMapping(path="/getUser/{id}/", params="version=1")
//	@GetMapping(value="/getUser/{id}", headers="X-API-VERSION=1")
	@GetMapping(value="/getUser/{id}", produces="application/vnd.company.appv1+json")
	public MappingJacksonValue getUserV1(@PathVariable int id) {
		User user = service.getUser(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found",id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","joinDate","password","ssn");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(user);
		
		jacksonValue.setFilters(filterProvider);
		
		return jacksonValue;
	}
	
//	@GetMapping(path="/v2/getUser/{id}/")
//	@GetMapping(path="/getUser/{id}/", params="version=2")
//	@GetMapping(path="/getUser/{id}", headers="X-API-VERSION=2")
	@GetMapping(value="/getUser/{id}", produces="application/vnd.company.appv2+json")
	public MappingJacksonValue getUserV2(@PathVariable int id) {
		User user = service.getUser(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found",id));
		}
		
		UserV2 userV2  = new UserV2();
		BeanUtils.copyProperties(user, userV2);
		userV2.setGrade("VIP");
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id","name","password","ssn","grade");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(userV2);
		
		jacksonValue.setFilters(filterProvider);
		
		return jacksonValue;
	}
	
	
}
