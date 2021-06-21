package com.hongma.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDAOService {
	
	private static List<User> 	users = new ArrayList<User>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1,"hongma",new Date(),"pass1", "701010-1111111"));
		users.add(new User(2,"dev",new Date(),"pass2", "701010-2222222"));
		users.add(new User(3,"mac-book",new Date(),"pass3", "801010-1111111"));
	}
			
	
	public List<User> getUsers(){
		return users;
	}
	
	public User getUser(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		
		return null;
	}
	
	public User insertUser(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		
		users.add(user);
		return user;
	}
	
	public User deleteUser(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if ( user.getId() == id ) {
				it.remove();
				
				return user;
			}
		}
		
		
		return null;
	}
	
}
