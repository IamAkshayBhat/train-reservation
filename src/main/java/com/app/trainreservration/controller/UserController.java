package com.app.trainreservration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public TicketUser createUser(@RequestBody TicketUser user) {
		return userService.createUser(user);
	}
}
