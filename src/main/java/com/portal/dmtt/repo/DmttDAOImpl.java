package com.portal.dmtt.repo;


import com.portal.dmtt.model.StatusProject;
import com.portal.dmtt.model.TaskSchJob;
import com.portal.dmtt.model.exceptionMonitoring.FN_Result_Set;
import com.portal.dmtt.model.exceptionMonitoring.config.EXCP_File;
import com.portal.dmtt.model.exceptionMonitoring.config.FN_FR_new_Location;
import com.portal.dmtt.model.TaskReq;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DmttDAOImpl implements dmttDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return
     */
    @Override
    public List<TaskSchJob> getTaskSchJobs() {


        String qry = "SELECT id,title,username,script,date_create,cron_job,status FROM task_Sch_Job";

        Query query = entityManager.createNativeQuery(qry, TaskSchJob.class);

        List<TaskSchJob> taskSchJobList = query.getResultList();


        return taskSchJobList;
    }

    /**
     * @return
     */
    @Override
    public List<TaskReq> getTaskReqs() {

        String qry = "SELECT id, jira, requestor, requestdesc, department, pic, status, requestdate, completedate FROM taskreq ";

        Query query = entityManager.createNativeQuery(qry, TaskReq.class);

        List<TaskReq> taskReqList = query.getResultList();

        return taskReqList;
    }

    /**
     * @return
     */
    @Override
    public List<StatusProject> getStatusProject() {

        String qry = "SELECT id, projectname, itotask, status, action FROM statusProject;";

        Query query = entityManager.createNativeQuery(qry, StatusProject.class);

        List<StatusProject> statusProjectList = query.getResultList();

        return statusProjectList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<StatusProject> findStatusProjectById(Long id) {

        String qry = "SELECT  id, projectname, itotask, status,action FROM statusProject where id=" + id;

        Query query = entityManager.createNativeQuery(qry, StatusProject.class);

        List<StatusProject> findId = query.getResultList();

        for (int i = 0; i < findId.size(); i++) {
            System.out.println("find name by id : query>>>" + findId.get(i).getProjectname());
        }

        return findId;
    }

    /**
     * @param id
     * @param updateProjectName
     * @param updateProjectItoTask
     * @param updateProjectStatus
     * @return
     */
    @Transactional
    @Override
    public int updateProjectById(Long id, String updateProjectName, String updateProjectItoTask, String updateProjectStatus) {

        String qry = "UPDATE statusproject set projectName ='" + updateProjectName + "', itotask='" + updateProjectItoTask + "', status='" + updateProjectStatus + "' where id=" + id;

        Query query = entityManager.createNativeQuery(qry, StatusProject.class);

        int updateProject = query.executeUpdate();

        return updateProject;
    }

    /**
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteProjectById(Long id) {

        String qry = "DELETE From statusproject where id=" + id;

        Query query = entityManager.createNativeQuery(qry, StatusProject.class);

        int deleteProject = query.executeUpdate();

        return 0;
    }

    /**
     * Get location on database
     *
     * @return
     */
    @Override
    public List<FN_FR_new_Location> getLocation() {

        String qry = "SELECT id, match_sp_id, match_loc_id, remarks FROM configuration_fn_exception;";

        Query query = entityManager.createNativeQuery(qry, FN_FR_new_Location.class);

        List<FN_FR_new_Location> fnFrNewLocationList = query.getResultList();

        return fnFrNewLocationList;
    }

    /**
     * Delete the data on database
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteNewLocation(Long id) {

        String qry = "DELETE From configuration_fn_exception where id=" + id;

        Query query = entityManager.createNativeQuery(qry, FN_FR_new_Location.class);

        int deleteProject = query.executeUpdate();

        return 0;
    }

    @Transactional
    @Override
    public int uploadListToDB(String FILE_PATH) {

        System.out.println("Start upload");

        String qry = "LOAD DATA LOCAL INFILE '" + FILE_PATH + "' INTO TABLE fn_old_exception FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (SP_ID, LOC_ID, XFER_XMIT_STATUS, XFER_FILE_NAME, UPDATE_TS, YYMM, REMARKS) ";

        System.out.println(qry);

        Query query = entityManager.createNativeQuery(qry);

        int uploadListToDB = query.executeUpdate();


        return uploadListToDB;
    }

    @Transactional
    @Override
    public int deleteDbException(String tableName) {

        String qry = "truncate " + tableName;

        Query query = entityManager.createNativeQuery(qry);

        int deleteDb = query.executeUpdate();

        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EXCP_File> getExcpFile() {

        String qry = "SELECT id, file_name, remarks, file_path, time_stamp FROM EXCP_File";

        Query query = entityManager.createNativeQuery(qry, EXCP_File.class);

        List<EXCP_File> excpFileList = query.getResultList();

//        int size= excpFileList.size();
//
//        for (int i = 0; i <size ; i++) {
//            System.out.println(excpFileList.iterator().next().toString());
//
//        }


        return excpFileList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<FN_Result_Set> getAllResultSet() {

        String qry = "SELECT * FROM fn_ready_exception";

        Query query = entityManager.createNativeQuery(qry, FN_Result_Set.class);

        List<FN_Result_Set> fnResultSetList = query.getResultList();

        return fnResultSetList;
    }

    /**
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteTaskRequestById(Long id) {

        String qry = "DELETE FROM taskreq where id=" + id;

        Query query = entityManager.createNativeQuery(qry, TaskReq.class);

        int deleteTaskRequest = query.executeUpdate();

        return 0;
    }

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
    @Transactional
    @Override
    public int updateTaskRequestById(Long id, String theJira, String theReqDesc, String theReqDate, String theRequestor, String theDepartment, String theCompleteDate, String thePic, String theStatus) {

        String qry = "UPDATE taskreq set jira='" + theJira + "', requestor='" + theRequestor + "', requestdesc='" + theReqDesc + "', department='" + theDepartment + "', pic='" + thePic + "', status='" + theStatus + "', requestdate='" + theReqDate + "', completedate='" + theCompleteDate + "' where id = " + id;

        Query query = entityManager.createNativeQuery(qry, TaskReq.class);

        int updateTaskRequest = query.executeUpdate();

        return updateTaskRequest;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TaskReq> findTaskReqByID(Long id) {

        String qry = "SELECT * FROM taskreq where id=" + id;

        Query query = entityManager.createNativeQuery(qry, TaskReq.class);

        List<TaskReq> findId = query.getResultList();
//
//        System.out.println("size:"+findId.size());
//
//        for (int i = 0; i < findId.size(); i++) {
//            System.out.println("find jira by id : query>>>" + findId.get(i).getJira());
//        }
        return findId;
    }


}
