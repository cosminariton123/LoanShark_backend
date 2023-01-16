package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.repository.EventRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoanSharkEventService implements EventService {

    private final EventRepository eventRepository;

    public LoanSharkEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findEventById(Long id) {
        Event event = eventRepository.findEventById(id);

        if (event == null)
            throw new NotFoundException("Event with id " + id + " not found");

        return event;
    }

    public Event saveNewEvent(Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEventById(Long id) {
        findEventById(id);
        Integer deletedCount = eventRepository.deleteEventById(id);

        if (deletedCount > 0)
            return;

        throw new InternalServerError("Failed to delete event with id " + id);
    }

}
