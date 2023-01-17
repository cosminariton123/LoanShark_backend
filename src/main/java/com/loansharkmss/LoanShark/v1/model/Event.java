package com.loansharkmss.LoanShark.v1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Event parentEvent;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany
    @JoinTable
    private final List<User> members = new ArrayList<>();

    @OneToMany
    @JoinTable
    private final List<Debt> debts = new ArrayList<>();

    @OneToMany(mappedBy = "parentEvent")
    private final List<Event> subEvents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getParentEvent() {
        return parentEvent;
    }

    public void setParentEvent(Event parentEvent) {
        this.parentEvent = parentEvent;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<Event> getSubEvents() {
        return subEvents;
    }
}
