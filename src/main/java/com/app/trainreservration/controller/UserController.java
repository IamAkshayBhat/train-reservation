package com.app.trainreservration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.trainreservration.dto.UserRequest;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public TicketUser createUser(@RequestBody @Valid UserRequest user) {
		return userService.createUser(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeUser(@PathVariable Long id) {
		userService.removeUser(id);
		return ResponseEntity.ok().build();
	}
}
