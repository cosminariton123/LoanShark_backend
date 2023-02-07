package com.loansharkmss.LoanShark.v1.repository;

import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventById(Long id);

    Event findEventByIdAndParentEventNotNull(Long id);

    List<Event> findEventsByAdminAndParentEventNull(User user);

    List<Event> findEventsByMembersContainsAndParentEventNull(User user);

    Integer deleteEventById(Long id);

}
