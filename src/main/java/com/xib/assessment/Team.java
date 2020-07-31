package com.xib.assessment;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    @OneToMany//(fetch = FetchType.LAZY, mappedBy = "name")
    private List<Agent> agent = new ArrayList<Agent>();
    
    @ManyToMany//(fetch = FetchType.LAZY, mappedBy = "name")
    private List<Manager> manager = new ArrayList<Manager>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Agent> getAgent() {
        return agent;
    }

    public void setAgent(List<Agent> agent) {
        this.agent = agent;
    }
    
    public List<Manager> getManager() {
        return manager;
    }

    public void setManager(List<Manager> manager) {
        this.manager = manager;
    }
}
