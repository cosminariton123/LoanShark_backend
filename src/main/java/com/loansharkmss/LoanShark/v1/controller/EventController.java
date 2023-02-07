package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.EventCard;
import com.loansharkmss.LoanShark.v1.dtos.EventCardListResponse;
import com.loansharkmss.LoanShark.v1.dtos.EventCreate;
import com.loansharkmss.LoanShark.v1.dtos.EventCreated;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.EventMapper;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.EventService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestControllerV1
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    private final UserService userService;

    public EventController(EventService eventService, EventMapper eventMapper, UserService userService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventCard> findEventById(@PathVariable Long id) {
        Event event = eventService.findEventById(id);
        EventCard eventCard = eventMapper.EventToEventCard(event);
        return ResponseEntity.ok().body(eventCard);
    }

    @PostMapping("/new")
    public ResponseEntity<EventCreated> saveNewEvent(@RequestBody @Valid EventCreate eventCreate) {
        Event event = eventMapper.EventCreateToEvent(eventCreate);
        Event savedEvent = eventService.saveNewEvent(event);
        EventCreated eventCreated = eventMapper.EventToEventCreated(savedEvent);
        return ResponseEntity.created(URI.create("/event/" + savedEvent.getId())).body(eventCreated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/root/contains/user/{id}")
    public ResponseEntity<EventCardListResponse> findEventsWhereUserIsAdminOrMemberByIdAndEventIsRoot(@PathVariable Long id) {
        User user = userService.findUserById(id);
        List<Event> events = eventService.findEventsWhereUserIsAdminOrMemberAndEventIsRoot(user);
        List<EventCard> eventCardList = events.stream().map(eventMapper::EventToEventCard).collect(Collectors.toList());
        EventCardListResponse eventCardListResponse = eventMapper.EventCardListToEventCardListResponse(eventCardList);
        return ResponseEntity.ok(eventCardListResponse);
    }

    public ResponseEntity<EventCard>
    //TODO("Termina SubEvent card")

}
