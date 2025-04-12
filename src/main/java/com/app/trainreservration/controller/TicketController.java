package com.app.trainreservration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.trainreservration.dto.TicketRequest;
import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.service.TicketService;
import com.app.trainreservration.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/purchase", consumes = "application/json")
	public ResponseEntity<Ticket> purchaseTicket(@RequestBody @Valid TicketRequest ticket, 
			@RequestParam(required = true) @Min(1) Long userId) {
		var user = userService.findById(userId);
		return ResponseEntity.ok(ticketService.purchaseTicket(ticket, user));
	}
	 
	@GetMapping("/receipt/{id}")
	public ResponseEntity<Ticket> getReceipt(@PathVariable Long id) {
		return ResponseEntity.ok(ticketService.getReceipt(id));
	}
	
	@GetMapping("/section/{section}")
	public ResponseEntity<List<Ticket>> getUsersBySection(@PathVariable String section) {
		return ResponseEntity.ok(ticketService.getUserBySection(section));
	}
	
	public ResponseEntity<Ticket> modifyUserSeat(@PathVariable Long id, @RequestParam String newSeat) {
		return ResponseEntity.ok(ticketService.modifyUserSeat(id, newSeat));
	}

}
