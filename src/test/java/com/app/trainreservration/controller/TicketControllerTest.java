package com.app.trainreservration.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.trainreservration.dto.TicketRequest;
import com.app.trainreservration.entity.Section;
import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.repo.TicketRepository;
import com.app.trainreservration.service.TicketService;
import com.app.trainreservration.service.UserService;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {
	
	@InjectMocks
	private TicketController ticketController;
	
	@Mock
	private TicketService ticketService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private TicketRepository ticketRepository;

	@Test
	void givenAValidTicket_WhenIcallPurchaseTicket_thenPurchaseIsSuccessful() {
		Ticket ticket = mock(Ticket.class);
		TicketRequest ticketRequest = mock(TicketRequest.class);
		TicketUser user = mock(TicketUser.class);
		when(userService.findById(any())).thenReturn(user);
		when(ticketService.purchaseTicket(any(TicketRequest.class), any(TicketUser.class))).thenReturn(ticket);
		ticketController.purchaseTicket(ticketRequest, 1L);
		verify(ticketService, times(1)).purchaseTicket(any(TicketRequest.class), any(TicketUser.class));
		
	}
	
	@Test
	void givenAValidTicketId_WhenIcallGetReceipt_thenItReturnsReceipt() {
		Ticket ticket = mock(Ticket.class);
		when(ticketService.getReceipt(any())).thenReturn(ticket);
		ticketController.getReceipt(1L);
		verify(ticketService, times(1)).getReceipt(any());
		
	}
	
	@Test
	void givenAValidTicketId_WhenIcallGetSection_thenItReturnsListOfTicketsWithSection() {
		Ticket ticket = mock(Ticket.class);
		when(ticketService.getUserBySection(any())).thenReturn(List.of(ticket));
		ticketController.getUsersBySection(Section.A.name());
		verify(ticketService, times(1)).getUserBySection(any());
		
	}
	
	@Test
	void givenAValidUserIdAndNewSeat_WhenIcallModifyUserSeat_thenItModifiestheSeat() {
		ticketController.modifyUserSeat(1L, "32");
		verify(ticketService, times(1)).modifyUserSeat(1L, "32");
		
	}

}
