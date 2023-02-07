package com.loansharkmss.LoanShark.v1.dtos;

import java.util.List;

public class MushSubEventCard {

    private final String name;

    private final String description;

    private final UserMinimalCardNoImage admin;

    private final List<UserMinimalCardNoImage> members;

    public MushSubEventCard(String name, String description, UserMinimalCardNoImage admin, List<UserMinimalCardNoImage> members) {
        this.name = name;
        this.description = description;
        this.admin = admin;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserMinimalCardNoImage getAdmin() {
        return admin;
    }

    public List<UserMinimalCardNoImage> getMembers() {
        return members;
    }
}
