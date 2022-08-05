package com.crud.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Project {
    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }
}
