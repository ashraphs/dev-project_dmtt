package com.portal.dmtt.scriptJobManager;

import com.portal.dmtt.model.ProdConfig;
import com.portal.dmtt.model.TaskSchJob;
import com.portal.dmtt.scriptJobManager.dao.taskSchJobDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

public class ScriptJobManager {


    private int id;
    private String theScript;

    private TaskSchJob TaskSchJob;
    private Connection Connection;


    /**
     * @param id -  find the script data column from db dmtt-portal based on id
     */
    public ScriptJobManager(int id) {
        this.id = id;

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        taskSchJobDao schJobDao = (taskSchJobDao) context.getBean("taskSchJobDAO");

        //get the specific id for the value id => Script
        TaskSchJob = schJobDao.findTaskSchJob(id);
    }

    /**
     * default constructor
     *
     * @throws ClassNotFoundException
     */
    public ScriptJobManager(String theScript) throws ClassNotFoundException, SQLException {

        System.out.println("----------------------");
        System.out.println("My script is ########>>>" + theScript);
        System.out.println("----------------------");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/prodDB.xml");
        ProdConfig ProdConfig = (ProdConfig) applicationContext.getBean("dataSource");

        Class.forName(ProdConfig.getDriver());

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(ProdConfig.getTheConnection(),
                    ProdConfig.getUsername(),
                    ProdConfig.getPassword());

            Statement stmt = connection.createStatement();
            ResultSet rs;

//            rs = stmt.executeQuery(String.valueOf(TaskSchJob.getScript()));
            rs = stmt.executeQuery(theScript);
            while (rs.next()) {
                String PLAZA_ID = rs.getString("id");
                String PLAZA_NAME = rs.getString("NAME");

                System.out.println("id: " + PLAZA_ID + "\tNAME: " + PLAZA_NAME);
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

        }finally {

            connection.close();

        }

    }
}
