package com.app.trainreservration.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.trainreservration.dto.TicketRequest;
import com.app.trainreservration.entity.Section;
import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.exception.NotFoundException;
import com.app.trainreservration.repo.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
	
	@InjectMocks
	private TicketService ticketService;
	
	@Mock
	private TicketRepository ticketRepository;

	@Test
	void givenAValidUserIdAndRequestPayload_whenICallPurchaseTicket_thenPurchaseIsSuccessful() {
		var mockTicket = mock(Ticket.class);
		var ticket = TicketRequest.builder().build();
		var ticketUser = TicketUser.builder().build();
		when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);
		ticketService.purchaseTicket(ticket, ticketUser);
		verify(ticketRepository, times(1)).save(any(Ticket.class));
	}
	
	@Test
	void givenAValidUserId_whenICallGetReceipt_thenIGetTicketDetails() {
		var ticket = mock(Ticket.class);
		when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
		ticketService.getReceipt(1L);
		verify(ticketRepository, times(1)).findById(1L);
	}
	
	@Test
	void givenAnInvalidUserId_whenICallGetReceipt_thenItThrowsNotFoundException() {
		when(ticketRepository.findById(1L)).thenThrow(new NotFoundException());
		assertThrows(NotFoundException.class, () -> ticketService.getReceipt(1L));
	}
	
	@Test
	void givenAValidSection_whenICallGetSection_thenIGetTicketDetails() {
		var ticket = mock(Ticket.class);
		when(ticketRepository.findBySection(Section.A)).thenReturn(List.of(ticket));
		ticketService.getUserBySection("A");
		verify(ticketRepository, times(1)).findBySection(Section.A);
	}
	
	@Test
	void givenAValidSectionWithNoUsers_whenICallGetSection_thenItThrowsNotFoundException() {
		when(ticketRepository.findBySection(Section.A)).thenReturn(null);
		assertThrows(NotFoundException.class, () -> ticketService.getUserBySection("A"));
	}


	@Test
	void givenAValidUserIdAndNewSeat_whenICallModifyUserSeat_thenSeatIsModified() {
		var mockTicket = mock(Ticket.class);
		when(ticketRepository.findById(1L)).thenReturn(Optional.of(mockTicket));
		when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);
		ticketService.modifyUserSeat(1L, "32");
		verify(ticketRepository, times(1)).save(any(Ticket.class));
	}
	
	@Test
	void givenAInvalidUserId_whenICallModifyUserSeat_thenItThrowsNotFoundException() {
		when(ticketRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		assertThrows(NotFoundException.class, () -> ticketService.modifyUserSeat(1L, "32"));
	
	}
	
	
}
