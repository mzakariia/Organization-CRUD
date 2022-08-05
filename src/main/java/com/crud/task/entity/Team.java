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
@Table(name = "teams")
public class Team {

    @Id
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization organization;

    @ManyToMany
    @JoinTable(
            name = "TeamWorksOnProject",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public void addProject(Project project){
        projects.add(project);
    }

    public void removeProject(Project project){
        projects.remove(project);
    }
}
