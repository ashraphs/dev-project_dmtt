package com.portal.dmtt.repo;

import com.portal.dmtt.model.exceptionMonitoring.config.EXCP_File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface excpFileREPO extends JpaRepository<EXCP_File,Long> {
}
