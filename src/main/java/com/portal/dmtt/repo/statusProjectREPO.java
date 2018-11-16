package com.portal.dmtt.repo;

import com.portal.dmtt.model.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface statusProjectREPO extends JpaRepository<StatusProject,Long> {

    Long countByProjectname(String projectname);


}
