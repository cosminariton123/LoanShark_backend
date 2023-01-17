package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.EventCard;
import com.loansharkmss.LoanShark.v1.dtos.EventCreate;
import com.loansharkmss.LoanShark.v1.dtos.SubEventCard;
import com.loansharkmss.LoanShark.v1.model.Event;

public interface EventMapper {

    Event EventCreateToEvent(EventCreate eventCreate);

    EventCard EventToEventCard(Event event);

    SubEventCard EventToSubEventCard (Event event);

}
