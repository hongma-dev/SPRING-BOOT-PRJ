package com.hongma.demo.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo")
public class User {

	private Integer id;
	
	
	@Size(min=2, message="Name은 2글자 이상 입력해주세요.")
	private String name;
	
	@Past
	private Date joinDate;
	
//	@JsonIgnore
	private String password;
	
//	@JsonIgnore
	private String ssn;
}
