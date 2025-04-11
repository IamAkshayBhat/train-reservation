package com.app.trainreservration.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.trainreservration.entity.Section;
import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.exception.NotFoundException;
import com.app.trainreservration.repo.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	public Ticket purchaseTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	public Ticket getReceipt(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.name(), "Ticket Not Found"));
	}
	
	public List<Ticket> getUserBySection(String section) {
		var tickets = ticketRepository.findBySection(Section.valueOf(section.toUpperCase()));
		if(Objects.isNull(tickets)) {
			throw new NotFoundException(HttpStatus.NOT_FOUND.name(), "Section Not Found");
		}
		return tickets;
	}
	
	public void removeUser(Long id) {
		ticketRepository.deleteById(id);
	}
	
	public Ticket modifyUserSeat(Long id, String newSeat) {
		Ticket ticket = getReceipt(id);
		if(Objects.nonNull(ticket)) {
			ticket.setSeatNumber(newSeat);
		} else {
			throw new NotFoundException(HttpStatus.NOT_FOUND.name(), "Ticket Not Found");
		}
		
		return ticketRepository.save(ticket);
	}
}
	
