package com.loansharkmss.LoanShark.v1.controller;

import com.loansharkmss.LoanShark.v1.config.RestControllerV1;
import com.loansharkmss.LoanShark.v1.dtos.EventCard;
import com.loansharkmss.LoanShark.v1.dtos.EventCreate;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.EventMapper;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.service.interfaces.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestControllerV1
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventCard> findEventById(@PathVariable Long id) {
        Event event = eventService.findEventById(id);
        EventCard eventCard = eventMapper.EventToEventCard(event);
        return ResponseEntity.ok().body(eventCard);
    }

    @PostMapping("/new")
    public ResponseEntity<Event> saveNewEvent(@RequestBody @Valid EventCreate eventCreate) {
        Event event = eventMapper.EventCreateToEvent(eventCreate);
        Event savedEvent = eventService.saveNewEvent(event);
        return ResponseEntity.created(URI.create("/event/" + savedEvent.getId())).body(savedEvent);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok().build();
    }

}
