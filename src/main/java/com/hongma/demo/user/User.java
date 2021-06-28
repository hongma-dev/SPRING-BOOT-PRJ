package com.hongma.demo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description="사용자 상세 정보를 위한 객체")
@Entity
public class User {

	public User(int id, String name, Date joinDate, String password, String ssn) {
		this.id = id;
		this.name=name;
		this.joinDate = joinDate;
		this.password = password;
		this.ssn = ssn;
	}


	@Id
	@GeneratedValue
	private Integer id;
	
	
	@Size(min=2, message="Name은 2글자 이상 입력해주세요.")
	@ApiModelProperty(notes="사용자 이름을 입력해주세요.")
	private String name;
	
	@Past
	@ApiModelProperty(notes="등록일 입력해주세요.")
	private Date joinDate;
	
//	@JsonIgnore
	@ApiModelProperty(notes="사용자 패스워드를 입력해주세요.")
	private String password;
	
//	@JsonIgnore
	@ApiModelProperty(notes="사용자 주민번호를 입력해주세요.")
	private String ssn;
	
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
}
