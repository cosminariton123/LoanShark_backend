package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.*;
import com.loansharkmss.LoanShark.v1.model.Event;

import java.util.List;

public interface EventMapper {

    Event EventCreateToEvent(EventCreate eventCreate);

    EventCreated EventToEventCreated(Event event);

    EventCard EventToEventCard(Event event);

    SubEventCard EventToSubEventCard (Event event);

    EventCardListResponse EventCardListToEventCardListResponse(List<EventCard> eventCardList);

    MushSubEventCard EventToMushSubEventCard(Event event);
}
