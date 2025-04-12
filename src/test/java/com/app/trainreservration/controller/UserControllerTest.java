package com.app.trainreservration.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.trainreservration.dto.UserRequest;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;

	@Test
	void givenAValidUser_WhenIcallCreateUser_thenUserIsCreated() {
		UserRequest userRequest = mock(UserRequest.class);
		TicketUser user = mock(TicketUser.class);
		when(userService.createUser(any(UserRequest.class))).thenReturn(user);
		userController.createUser(userRequest);
		verify(userService, times(1)).createUser(any(UserRequest.class));
	}
	
	@Test
	void givenAValidUserId_WhenIcallRemoveUser_thenUserIsRemoved() {
		userController.removeUser(1L);
		verify(userService, times(1)).removeUser(any());
	}
	

}
