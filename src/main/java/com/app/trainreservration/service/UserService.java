package com.app.trainreservration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.trainreservration.dto.UserRequest;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.exception.NotFoundException;
import com.app.trainreservration.mapper.UserMapper;
import com.app.trainreservration.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public TicketUser createUser(UserRequest user) {
		return userRepository.save(UserMapper.INSTANCE.mapToTicketUser(user));
	}
	
	public TicketUser findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "User with id : " +id + " not found"));
	}
	
	public void removeUser(Long id) {
		var user = findById(id);
		userRepository.deleteById(user.getId());
	}
	
}