package com.app.trainreservration.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fromLocation;
	private String toLocation;
	private double pricePaid;
	
	@Enumerated(EnumType.STRING)
	private Section section;
	private String seatNumber;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private TicketUser user;
	
}
