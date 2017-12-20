package com.thinkhr.external.api.learn.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thinkhr.external.api.db.learn.entities.LearnRole;


/**
 * LearnRole repository for LearnRole entity.
 *  
 * @author Ajay Jain
 * @since   2017-12-20
 *
 */

public interface LearnRoleRepository extends CrudRepository<LearnRole, Integer> {
    LearnRole findFirstByShortName(String shortName);
}