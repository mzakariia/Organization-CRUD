package com.crud.task.repository;

import com.crud.task.entity.Project;
import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Integer> {
}
