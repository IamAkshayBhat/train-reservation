package com.app.trainreservration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.app.trainreservration.dto.UserRequest;
import com.app.trainreservration.entity.TicketUser;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target = "id", ignore = true)
	TicketUser mapToTicketUser(UserRequest ticketUserRequest);
	
}
