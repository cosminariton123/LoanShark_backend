package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Event;

public interface EventService {

    Event findEventById(Long id);

    Event saveNewEvent(Event event);

    void deleteEventById(Long id);

}
