package com.crud.task;

import com.crud.task.entity.Organization;
import com.crud.task.entity.OrganizationDetails;
import com.crud.task.entity.Project;
import com.crud.task.entity.Team;
import com.crud.task.repository.OrganizationDetailsRepository;
import com.crud.task.repository.OrganizationRepository;
import com.crud.task.repository.ProjectRepository;
import com.crud.task.repository.TeamRepository;
import com.crud.task.service.OrganizationDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TaskApplicationTests {

	@Autowired
	OrganizationDBService organizationDBService;
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	OrganizationDetailsRepository organizationDetailsRepository;

	@Test
	void createOrganization() {
		organizationDBService.createOrganization(1,"org");
		Organization retrievedOrg = organizationRepository.findById(1).get();
		Assert.notNull(retrievedOrg,"is null");
	}
	@Test
	void createTeam(){
		organizationDBService.createTeam(1,"bestTeam",1);
		Team retrievedTeam = teamRepository.findById(1).get();
		Assert.notNull(retrievedTeam,"is Null");
	}

	@Test
	void createOrganizationDetails(){
		int detailsId = organizationDBService.createOrganizationDetails("hello@gmail.com",1);
		OrganizationDetails retrievedDetails = organizationDetailsRepository.findById(detailsId).get();
		Organization org = organizationRepository.findById(1).get();
		Assert.notNull(retrievedDetails, "is Null");
		Assert.notNull(org.getOrganizationDetails(),"org details is null");
	}

	@Test
	void createProject(){
		organizationDBService.createProject(1,"prj1",1,1);
		Project retrievedProj = projectRepository.findById(1).get();
		Organization org = organizationRepository.findById(1).get();
		Team team = teamRepository.findById(1).get();
		Assert.notNull(retrievedProj, "project is null");
	}

	@Test
	void getOrganization(){
		Organization org = organizationDBService.getOrganization(1);
		Assert.isInstanceOf(Organization.class,org);
	}

	@Test
	void getTeam(){
		Team team = organizationDBService.getTeam(1);
		Assert.isInstanceOf(Team.class,team);
	}

	@Test
	void getProject(){
		Project project = organizationDBService.getProject(1);
		Assert.isInstanceOf(Project.class,project);
	}

	@Test
	void updateOrganization(){
		organizationDBService.updateOrganization(1,"bestOrg");
		Assert.isTrue(organizationRepository.findById(1).get().getName().equals("bestOrg"),"not equal");
	}

	@Test
	void updateProject(){
		organizationDBService.updateProject(1,"bestProj");
		Assert.isTrue(projectRepository.findById(1).get().getName().equals("bestProj"),"not equal");
	}

	@Test
	void updateTeam(){
		organizationDBService.updateTeam(1, "team1");
		Assert.isTrue(teamRepository.findById(1).get().getName().equals("team1"),"not equal");
	}

	@Test
	void deleteProject(){
		organizationDBService.deleteProject(1);
		Assert.isTrue(!projectRepository.findById(1).isPresent(),"present");
	}

	@Test
	void deleteTeam(){
		organizationDBService.deleteTeam(1);
		Assert.isTrue(!teamRepository.findById(1).isPresent(),"present");
	}

	@Test
	void deleteDetails(){
		int id = organizationDBService.deleteDetails(1);
		Assert.isTrue(!organizationDetailsRepository.findById(id).isPresent(),"present");
	}

	@Test
	void deleteOrganization(){
		organizationDBService.deleteOrganization(1);
		Assert.isTrue(!organizationRepository.findById(1).isPresent(),"not null");
	}


}
