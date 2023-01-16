package com.loansharkmss.LoanShark.v1.dtos;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EventCreate {

    private String name;

    private Long parentEventId;

    @NotNull
    private Long adminId;


    private final List<Long> membersIds = new ArrayList<>();

    private final List<Long> debtsIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(Long parentEventId) {
        this.parentEventId = parentEventId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public List<Long> getMembersIds() {
        return membersIds;
    }

    public List<Long> getDebtsIds() {
        return debtsIds;
    }
}
