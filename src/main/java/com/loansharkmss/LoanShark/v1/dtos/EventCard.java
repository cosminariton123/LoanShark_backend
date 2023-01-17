package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class EventCard {

    private final Long id;

    private final String name;

    private final Long parentEventId;

    private final String parentEventName;

    private final Long adminId;

    private final String adminUsername;

    private final List<String> membersUsernames = new ArrayList<>();

    private final List<SubEventCard> subEventCards = new ArrayList<>();

    public EventCard(Long id, String name, Long parentEventId, String parentEventName, Long adminId, String adminUsername) {
        this.id = id;
        this.name = name;
        this.parentEventId = parentEventId;
        this.parentEventName = parentEventName;
        this.adminId = adminId;
        this.adminUsername = adminUsername;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentEventId() {
        return parentEventId;
    }

    public String getParentEventName() {
        return parentEventName;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public List<String> getMembersUsernames() {
        return membersUsernames;
    }

    public List<SubEventCard> getSubEventCards() {
        return subEventCards;
    }
}
