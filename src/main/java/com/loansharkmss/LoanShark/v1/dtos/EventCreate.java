package com.loansharkmss.LoanShark.v1.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EventCreate {

    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotNull(message = "description must not be null(can be empty)")
    private String description;

    private Long parentEventId;

    @NotNull(message = "adminId must not be null")
    private Long adminId;


    private final List<Long> membersIds = new ArrayList<>();

    private final List<Long> debtsIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
