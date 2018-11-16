package com.portal.dmtt.repo;

import com.portal.dmtt.model.exceptionMonitoring.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface uploadFileREPO extends JpaRepository<UploadFile,Long> {
}
