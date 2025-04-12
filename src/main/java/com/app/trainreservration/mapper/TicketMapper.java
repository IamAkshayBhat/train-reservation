package com.app.trainreservration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.app.trainreservration.dto.TicketRequest;
import com.app.trainreservration.entity.Ticket;

@Mapper
public interface TicketMapper {

	TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
	
	@Mapping(target = "id", ignore = true)
	Ticket mapToTicket(TicketRequest ticketRequest);
}
