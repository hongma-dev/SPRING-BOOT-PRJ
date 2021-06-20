package com.hongma.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserController {

	private UserDAOService service;
	
	public UserController(UserDAOService service) {
		this.service = service;
	}
	
	
	@GetMapping(path="/getUsers")
	public List<User> getUsers(){
		return service.getUsers();
	}
	
	@GetMapping(path="/getUser/{id}")
	public User getUser(@PathVariable int id) {
		User user = service.getUser(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found",id));
		}
		
		return user;
	}
	
	@PostMapping(path="/insertUser")
	public ResponseEntity<User> insertUser(@Valid @RequestBody User vo) {
		User savedUser = service.insertUser(vo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/{id}")
						.buildAndExpand(savedUser)
						.toUri();
		
		
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteUser(id);
		
		if(user == null){
			throw new UserNotFoundException(String.format("ID[%s] not found",id));
		}
		
		
	}
	
}
