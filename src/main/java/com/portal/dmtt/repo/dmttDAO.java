package com.portal.dmtt.repo;

import com.portal.dmtt.model.StatusProject;
import com.portal.dmtt.model.exceptionMonitoring.FN_Result_Set;
import com.portal.dmtt.model.exceptionMonitoring.config.EXCP_File;
import com.portal.dmtt.model.exceptionMonitoring.config.FN_FR_new_Location;
import com.portal.dmtt.model.TaskReq;
import com.portal.dmtt.model.TaskSchJob;

import java.util.List;

public interface dmttDAO {

    /**
     * @return
     */
    List<TaskSchJob> getTaskSchJobs();

    /**
     * @return
     */
    List<TaskReq> getTaskReqs();

    /**
     * @param id
     * @return
     */
    int deleteTaskRequestById(Long id);

    /**
     * @param id
     * @param theJira
     * @param theReqDesc
     * @param theReqDate
     * @param theRequestor
     * @param theDepartment
     * @param theCompleteDate
     * @param thePic
     * @param theStatus
     * @return
     */
    int updateTaskRequestById(Long id,
                              String theJira,
                              String theReqDesc,
                              String theReqDate,
                              String theRequestor,
                              String theDepartment,
                              String theCompleteDate,
                              String thePic,
                              String theStatus);

    /**
     * @param id
     * @return
     */
    List<TaskReq> findTaskReqByID(Long id);

    /**
     * @return
     */
    List<StatusProject> getStatusProject();

    /**
     * @param id
     * @return
     */
    List<StatusProject> findStatusProjectById(Long id);

    /**
     * @param id
     * @param updateProjectName
     * @param updateProjectItoTask
     * @param updateProjectStatus
     * @return
     */
    int updateProjectById(Long id, String updateProjectName,
                          String updateProjectItoTask,
                          String updateProjectStatus);

    /**
     * @param id
     * @return
     */
    int deleteProjectById(Long id);

    List<FN_FR_new_Location> getLocation();

    int deleteNewLocation(Long id);

    int uploadListToDB(String FILE_PATH);

    int deleteDbException(String theTable);

    List <EXCP_File> getExcpFile();

    List <FN_Result_Set> getAllResultSet();



}
