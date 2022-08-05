package com.crud.task.service;


import com.crud.task.entity.Organization;
import com.crud.task.entity.OrganizationDetails;
import com.crud.task.entity.Project;
import com.crud.task.entity.Team;
import com.crud.task.repository.OrganizationDetailsRepository;
import com.crud.task.repository.OrganizationRepository;
import com.crud.task.repository.ProjectRepository;
import com.crud.task.repository.TeamRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class OrganizationDBService {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    OrganizationDetailsRepository organizationDetailsRepository;

    public void createOrganization(int id, String name){
        Organization org = new Organization();
        org.setId(id);
        org.setName(name);
        org.setTeams(new ArrayList<Team>());
        org.setProjects(new ArrayList<Project>());
        organizationRepository.save(org);
    }

    public void createTeam(int id, String name, int orgId){
        Organization org = organizationRepository.findById(orgId).get();
        Team newTeam = new Team();
        newTeam.setId(id);
        newTeam.setName(name);
        org.addTeam(newTeam);
        newTeam.setOrganization(org);
        teamRepository.save(newTeam);
    }

    public void createProject(int id, String name, int orgId, int teamId) {
        Organization org = organizationRepository.findById(orgId).get();
        Team team = teamRepository.findById(teamId).get();
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setTeams(new ArrayList<Team>());
        project.addTeam(team);
        project.setOrganization(org);
        org.addProject(project);
        projectRepository.save(project);
    }

    public int createOrganizationDetails(String email, int orgId){
        Organization org = organizationRepository.findById(orgId).get();
        OrganizationDetails orgDetails = new OrganizationDetails();
        orgDetails.setEmail(email);
        orgDetails.setOrganization(org);
        org.setOrganizationDetails(orgDetails);
        organizationDetailsRepository.save(orgDetails);
        return orgDetails.getId();
    }

    public void deleteOrganization(int id){
        Organization org = organizationRepository.findById(id).get();
        for(Project p : org.getProjects()){
            projectRepository.deleteById(p.getId());
        }
        for(Team t : org.getTeams()){
            teamRepository.deleteById(t.getId());
        }
        if(org.getOrganizationDetails()!=null)  organizationDetailsRepository.deleteById(org.getOrganizationDetails().getId());
        organizationRepository.deleteById(id);
    }

    public void deleteProject(int id){
        Project project = projectRepository.findById(id).get();
        project.getOrganization().getProjects().remove(project);
        for(Team t : project.getTeams()){
            t.getProjects().remove(project);
        }
        projectRepository.deleteById(id);
    }

    public void deleteTeam(int id){
        Team team = teamRepository.findById(id).get();
        team.getOrganization().getTeams().remove(team);
        for(Project p : team.getProjects()){
            p.getTeams().remove(team);
        }
        teamRepository.deleteById(id);
    }

    public int deleteDetails(int id){
        Organization org = organizationRepository.findById(id).get();
        OrganizationDetails details = org.getOrganizationDetails();
        org.setOrganizationDetails(null);
        organizationDetailsRepository.deleteById(details.getId());
        return details.getId();
    }

    public Organization getOrganization(int id){
        return organizationRepository.findById(id).get();
    }

    public Team getTeam(int id){
        return teamRepository.findById(id).get();
    }

    public Project getProject(int id){
        return projectRepository.findById(id).get();
    }

    public OrganizationDetails getOrganizationDetails(int id){
        return organizationRepository.findById(id).get().getOrganizationDetails();
    }

    public void updateOrganization(int id, String name){
         organizationRepository.findById(id).get().setName(name);
    }

    public void updateTeam(int id, String name){
        teamRepository.findById(id).get().setName(name);
    }

    public void updateProject(int id, String name){
        projectRepository.findById(id).get().setName(name);
    }

}
