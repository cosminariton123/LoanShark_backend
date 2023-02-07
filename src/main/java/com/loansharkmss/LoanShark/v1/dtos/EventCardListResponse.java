package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class EventCardListResponse {

    private final List<EventCard> events = new ArrayList<>();

    public EventCardListResponse(List<EventCard> eventCardList) {
        events.addAll(eventCardList);
    }

    public List<EventCard> getEvents() {
        return events;
    }
}
