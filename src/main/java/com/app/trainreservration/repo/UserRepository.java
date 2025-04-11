package com.app.trainreservration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.trainreservration.entity.TicketUser;

@Repository
public interface UserRepository extends JpaRepository<TicketUser, Long> {

}
