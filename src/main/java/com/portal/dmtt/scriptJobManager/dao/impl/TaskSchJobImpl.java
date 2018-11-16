package com.portal.dmtt.scriptJobManager.dao.impl;

import com.portal.dmtt.model.TaskSchJob;
import com.portal.dmtt.scriptJobManager.dao.taskSchJobDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskSchJobImpl implements taskSchJobDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param taskId
     * @return
     */
    @Override
    public TaskSchJob findTaskSchJob(int taskId) {

            String sql = "SELECT * FROM task_Sch_Job WHERE id = ?";

        Connection conn = null;


        try {
            //establish connection
            conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, taskId);

            TaskSchJob theTaskSchJob = null;

            ResultSet rs = ps.executeQuery();

            // query the data
            if (rs.next()) {
                theTaskSchJob = new TaskSchJob(
                        rs.getString ( "title" ),
                        rs.getString ("script")
                );
            }

            rs.close();
            ps.close();

            return theTaskSchJob;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

   }


}
