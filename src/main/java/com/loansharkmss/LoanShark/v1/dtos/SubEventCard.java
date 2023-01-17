package com.loansharkmss.LoanShark.v1.dtos;

public class SubEventCard {

    private final Long id;

    private final String name;

    private final Integer nrOfMembers;

    public SubEventCard(Long id, String name, Integer nrOfMembers) {
        this.id = id;
        this.name = name;
        this.nrOfMembers = nrOfMembers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNrOfMembers() {
        return nrOfMembers;
    }
}
