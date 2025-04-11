package com.app.trainreservration.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.repo.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;

	@Test
	void givenAValidUser_WhenIcallCreateUser_thenUserIsCreated() {
		TicketUser user = mock(TicketUser.class);
		when(userRepository.save(any(TicketUser.class))).thenReturn(user);
		userService.createUser(user);
		verify(userRepository, times(1)).save(any(TicketUser.class));
		
	}

}
