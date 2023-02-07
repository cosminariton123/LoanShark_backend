package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.dtos.*;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.EventMapper;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.Debt;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.EventRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.DebtService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanSharkEventMapper implements EventMapper {

    private final EventRepository eventRepository;

    private final UserService userService;

    private final DebtService debtService;

    private final UserMapper userMapper;

    public LoanSharkEventMapper(EventRepository eventRepository, UserService userService, DebtService debtService, UserMapper userMapper) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.debtService = debtService;
        this.userMapper = userMapper;
    }

    public Event EventCreateToEvent(EventCreate eventCreate) {
        Event event = new Event();

        Event parentEvent = eventRepository.findEventById(eventCreate.getParentEventId());

        if (parentEvent != null)
            event.setParentEvent(parentEvent);
        event.setName(eventCreate.getName());
        event.setDescription(eventCreate.getDescription());
        event.setAdmin(userService.findUserById(eventCreate.getAdminId()));

        for (Long memberId : eventCreate.getMembersIds()) {
            User member = userService.findUserById(memberId);
            event.getMembers().add(member);
        }

        for (Long debtId : eventCreate.getDebtsIds()) {
            Debt debt = debtService.findDebtById(debtId);
            event.getDebts().add(debt);
        }

        return event;
    }

    public EventCreated EventToEventCreated(Event event) {
        Long parentEventId = null;
        Long adminId = null;

        if (event.getParentEvent() != null)
            parentEventId = event.getParentEvent().getId();

        if (event.getAdmin() != null)
            adminId = event.getAdmin().getId();

        EventCreated eventCreated = new EventCreated(
                event.getId(),
                event.getName(),
                event.getDescription(),
                parentEventId,
                adminId
        );

        eventCreated.getMembersIds().addAll(
                event.getMembers()
                        .stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
        );

        eventCreated.getDebtIds().addAll(
                event.getDebts()
                        .stream()
                        .map(Debt::getId)
                        .collect(Collectors.toList())
        );

        eventCreated.getSubEventsIds().addAll(
                event.getSubEvents()
                        .stream()
                        .map(Event::getId)
                        .collect(Collectors.toList())
        );

        return eventCreated;
    }

    public EventCard EventToEventCard(Event event) {
        Long parentEventId = null;
        String parentEventName = null;
        if (event.getParentEvent() != null) {
            parentEventId = event.getParentEvent().getId();
            parentEventName = event.getParentEvent().getName();
        }
        Long adminId = null;
        String adminUsername = null;
        if (event.getAdmin() != null) {
            adminId = event.getAdmin().getId();
            adminUsername = event.getAdmin().getUsername();
        }

        EventCard eventCard = new EventCard(
                event.getId(),
                event.getName(),
                event.getDescription(),
                parentEventId,
                parentEventName,
                adminId,
                adminUsername
        );

        eventCard.getMembersUsernames()
                .addAll(
                        event.getMembers()
                                .stream()
                                .map(User::getUsername)
                                .collect(Collectors.toList())
                );

        eventCard.getSubEventCards()
                .addAll(
                        event.getSubEvents()
                                .stream()
                                .map(this::EventToSubEventCard)
                                .collect(Collectors.toList())
                );

        return eventCard;
    }

    public SubEventCard EventToSubEventCard(Event event) {
        return new SubEventCard(
                event.getId(),
                event.getName(),
                event.getMembers().size());
    }

    public EventCardListResponse EventCardListToEventCardListResponse(List<EventCard> eventCardList) {
        return new EventCardListResponse(eventCardList);
    }

    public MushSubEventCard EventToMushSubEventCard(Event event) {
        return  new MushSubEventCard(
                event.getName(),
                event.getDescription(),
                userMapper.UserToUserMinimalCardNoImage(event.getAdmin()),
                event.getMembers().stream().map(userMapper::UserToUserMinimalCardNoImage).collect(Collectors.toList())
        );
    }
}
