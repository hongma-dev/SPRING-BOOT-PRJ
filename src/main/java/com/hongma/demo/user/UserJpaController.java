package com.hongma.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="/jpa")
public class UserJpaController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/getUsers")
	public List<User> getUsers(){
		
		
		return userRepository.findAll();
	}
	
	@GetMapping(path="/getUser/{id}")
	public User getUser(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id) );
		}
		
		return user.get();
	}
	
	@DeleteMapping(path="/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	@PostMapping(path="/insertUser")
	public ResponseEntity<User> insertUser(@Valid @RequestBody User vo) {
		User savedUser = userRepository.save(vo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(savedUser)
				.toUri();


		return ResponseEntity.created(location).build();

	}
	
	@GetMapping(path="/getUser/{id}/getPosts")
	public List<Post> getPosts (@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id) );
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping(path="/user/{id}/insertPost")
	public ResponseEntity<Post> insertPost(@PathVariable int id, @RequestBody Post vo) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id) );
		}

		vo.setUser(user.get());
		
		Post savedPost = postRepository.save(vo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();


		return ResponseEntity.created(location).build();

	}
}
