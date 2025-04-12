package com.app.trainreservration.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	
}