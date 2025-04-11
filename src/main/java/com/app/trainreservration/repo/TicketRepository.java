package com.app.trainreservration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.trainreservration.entity.Section;
import com.app.trainreservration.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	List<Ticket> findBySection(Section section);
	
	Ticket findByIdAndSection(Long id, String section);
	

}
