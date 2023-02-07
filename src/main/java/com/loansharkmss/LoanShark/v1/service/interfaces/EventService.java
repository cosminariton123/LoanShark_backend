package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;

import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    Event findSubEventById(Long id);

    Event saveNewEvent(Event event);

    void deleteEventById(Long id);

    List<Event> findEventsWhereUserIsAdminOrMemberAndEventIsRoot(User user);

}
