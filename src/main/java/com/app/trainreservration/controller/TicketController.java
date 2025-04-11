package com.app.trainreservration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.trainreservration.entity.Ticket;
import com.app.trainreservration.service.TicketService;
import com.app.trainreservration.service.UserService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/purchase")
	public ResponseEntity<Ticket> purchaseTicket(@RequestBody Ticket ticket, @RequestParam Long userId) {
		var user = userService.findById(userId);
		ticket.setUser(user);
		return ResponseEntity.ok(ticketService.purchaseTicket(ticket));
	}
	 
	@GetMapping("/receipt/{id}")
	public ResponseEntity<Ticket> getReceipt(@PathVariable Long id) {
		return ResponseEntity.ok(ticketService.getReceipt(id));
	}
	
	@GetMapping("/section/{section}")
	public ResponseEntity<List<Ticket>> getUsersBySection(@PathVariable String section) {
		return ResponseEntity.ok(ticketService.getUserBySection(section));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> removeUser(@PathVariable Long id) {
		ticketService.removeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Ticket> modifyUserSeat(@PathVariable Long id, @RequestParam String newSeat) {
		return ResponseEntity.ok(ticketService.modifyUserSeat(id, newSeat));
	}

}
