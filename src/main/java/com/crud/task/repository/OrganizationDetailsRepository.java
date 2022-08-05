package com.crud.task.repository;

import com.crud.task.entity.OrganizationDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDetailsRepository extends CrudRepository<OrganizationDetails,Integer> {
}
