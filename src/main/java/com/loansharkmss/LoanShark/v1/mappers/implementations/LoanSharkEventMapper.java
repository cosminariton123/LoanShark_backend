package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.dtos.EventCreate;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.EventMapper;
import com.loansharkmss.LoanShark.v1.model.Debt;
import com.loansharkmss.LoanShark.v1.model.Event;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.repository.EventRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.DebtService;
import com.loansharkmss.LoanShark.v1.service.interfaces.EventService;
import com.loansharkmss.LoanShark.v1.service.interfaces.UserService;
import org.springframework.stereotype.Component;

@Component
public class LoanSharkEventMapper implements EventMapper {

    private final EventRepository eventRepository;

    private final UserService userService;

    private final DebtService debtService;

    public LoanSharkEventMapper (EventRepository eventRepository, UserService userService, DebtService debtService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.debtService = debtService;
    }

    public Event EventCreateToEvent(EventCreate eventCreate) {
        Event event = new Event();

        Event parentEvent = eventRepository.findEventById(eventCreate.getParentEventId());

        if (parentEvent != null)
            event.setParentEvent(parentEvent);
        event.setName(eventCreate.getName());
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

}
