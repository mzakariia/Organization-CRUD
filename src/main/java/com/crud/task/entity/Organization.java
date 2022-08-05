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
@Getter
@Setter
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    private int id;

    private String name;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "organizationDetails_id", referencedColumnName = "id")
    private OrganizationDetails organizationDetails;

    @OneToMany(mappedBy = "organization")
    private List<Team> teams;

    @OneToMany(mappedBy = "organization")
    private List<Project> projects;

    public void addTeam(Team team){
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public void removeProject(Project project){
        projects.remove(project);
    }

}
