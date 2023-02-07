package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.BadRequest;
import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.EventRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.EventService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Event addMembersToEventWithId(Long Id, List<User> users) {
        Event event = findEventById(Id);

        event.getMembers().addAll(users);

        return event;
    }

    public Event saveNewEvent(Event event) {
        validateMembersList(event);
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

    private Boolean isAdminInMembersList(Event event) {
        if (event.getMembers().contains(event.getAdmin()))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private Boolean doesMembersListContainDuplicates(Event event) {
        if (new HashSet<>(event.getMembers()).size() != event.getMembers().size())
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private void validateMembersList(Event event) {
        if (isAdminInMembersList(event))
            throw new BadRequest("Member list contains the admin and it should not.");

        if (doesMembersListContainDuplicates(event))
            throw new BadRequest("Member list contains duplicates");
    }

}
