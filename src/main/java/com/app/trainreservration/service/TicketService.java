package com.app.trainreservration.service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.trainreservration.dto.TicketRequest;
import com.app.trainreservration.entity.Section;
import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.entity.TicketUser;
import com.app.trainreservration.exception.NotFoundException;
import com.app.trainreservration.mapper.TicketMapper;
import com.app.trainreservration.repo.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	private final AtomicInteger sectionACounter = new AtomicInteger(1);
    private final AtomicInteger sectionBCounter = new AtomicInteger(1);
    private final int maxSeatsPerSection = 5;
	
	public Ticket purchaseTicket(TicketRequest ticketRequest, TicketUser user) {
		var ticket = TicketMapper.INSTANCE.mapToTicket(ticketRequest);
		Section section;
        String seatNumber;

        if (sectionACounter.get() <= maxSeatsPerSection) {
            section = Section.A;
            seatNumber = section.name() + sectionACounter.getAndIncrement();
        } else if (sectionBCounter.get() <= maxSeatsPerSection) {
            section = Section.B;
            seatNumber = section.name() + sectionBCounter.getAndIncrement();
        } else {
            throw new IllegalStateException("All seats are booked.");
        }
        ticket.setSection(section);
        ticket.setSeatNumber(seatNumber);
		ticket.setUser(user);
		return ticketRepository.save(ticket);
	}
	
	public Ticket getReceipt(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Ticket Not Found"));
	}
	
	public List<Ticket> getUserBySection(String section) {
		var tickets = ticketRepository.findBySection(Section.valueOf(section.toUpperCase()));
		if(Objects.isNull(tickets) || tickets.isEmpty()) {
			throw new NotFoundException(HttpStatus.NOT_FOUND, "No User present in Section: " + section);
		}
		return tickets;
	}

	public Ticket modifyUserSeat(Long id, String newSeat) {
		Ticket ticket = getReceipt(id);
		if(Objects.nonNull(ticket)) {
			ticket.setSeatNumber(newSeat);
		} else {
			throw new NotFoundException(HttpStatus.NOT_FOUND, "Ticket Not Found");
		}
		
		return ticketRepository.save(ticket);
	}
}
	
