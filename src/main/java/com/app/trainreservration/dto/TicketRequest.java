package com.app.trainreservration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	
	@NotBlank(message = "fromLocation must not be blank")
	@Pattern(
	        regexp = "^[A-Za-z]{2,50}$",
	        message = "fromlocation must contain only letters without spaces (2 to 50 characters)")
	private String fromLocation;
	
	@NotBlank(message = "toLocation must not be blank")
	@Pattern(
	        regexp = "^[A-Za-z]{2,50}$",
	        message = "toLocation must contain only letters without spaces (2 to 50 characters)")
	private String toLocation;
	
	@DecimalMin(value = "0.01", inclusive = true, message = "pricePaid must be greater than zero")
	private double pricePaid;
	
	/*
	 * @Enumerated(EnumType.STRING) private Section section; private String
	 * seatNumber;
	 */
	
}
