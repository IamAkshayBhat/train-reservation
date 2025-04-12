package com.app.trainreservration.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.trainreservration.dto.UserRequest;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.exception.NotFoundException;
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
		UserRequest userRequest = mock(UserRequest.class);
		when(userRepository.save(any(TicketUser.class))).thenReturn(user);
		userService.createUser(userRequest);
		verify(userRepository, times(1)).save(any(TicketUser.class));
		
	}
	
	@Test
	void givenAValidUserId_WhenIcallFindById_thenItReturnAUserWithThatId() {
		TicketUser user = mock(TicketUser.class);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		userService.findById(1L);
		verify(userRepository, times(1)).findById(anyLong());
		
	}
	
	@Test
	void givenAnInvalidUserId_WhenIcallFindById_thenItThrowsNotFoundException() {
		when(userRepository.findById(3L)).thenThrow(new NotFoundException());
		assertThrows(NotFoundException.class, ()-> userService.findById(3L));
		
	}

	@Test
	void givenAValidUserId_WhenIcallRemoveUser_thenItUserIsRemoved() {
		TicketUser user = mock(TicketUser.class);
		when(user.getId()).thenReturn(3L);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		doNothing().when(userRepository).deleteById(3L);
		userService.removeUser(3L);
		verify(userRepository, times(1)).deleteById(anyLong());
		
	}
}
