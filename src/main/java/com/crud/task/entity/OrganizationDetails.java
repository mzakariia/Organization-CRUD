package com.crud.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organizationDetails")
public class OrganizationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int employeesNum;

    private String email;

    @OneToOne(mappedBy = "organizationDetails")
    private Organization organization;
}
