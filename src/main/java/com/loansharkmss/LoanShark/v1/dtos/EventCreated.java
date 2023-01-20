package com.loansharkmss.LoanShark.v1.dtos;

import java.util.ArrayList;
import java.util.List;

public class EventCreated {

    private final Long id;

    private final String name;

    private final Long parentEventId;

    private final Long adminId;

    private final List<Long> membersIds = new ArrayList<>();

    private final List<Long> debtIds = new ArrayList<>();

    private final List<Long> subEventsIds = new ArrayList<>();

    public EventCreated(Long id, String name, Long parentEventId, Long adminId) {
        this.id = id;
        this.name = name;
        this.parentEventId = parentEventId;
        this.adminId = adminId;
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

    public Long getAdminId() {
        return adminId;
    }

    public List<Long> getMembersIds() {
        return membersIds;
    }

    public List<Long> getDebtIds() {
        return debtIds;
    }

    public List<Long> getSubEventsIds() {
        return subEventsIds;
    }
}
