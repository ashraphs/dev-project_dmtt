package com.portal.dmtt.controller;


import com.portal.dmtt.model.TaskSchJob;
import com.portal.dmtt.scriptJobManager.dao.taskSchJobDao;
import com.portal.dmtt.repo.dmttDAO;
import com.portal.dmtt.repo.taskSchJobREPO;
import com.portal.dmtt.scriptJobManager.ScriptJobManager;
import com.portal.dmtt.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private taskSchJobREPO taskSchJobREPO;

    @Autowired
    private dmttDAO dmttDAO;

    private TaskSchJob TaskSchJob;

    /**
     * @param name
     * @return
     */
    @RequestMapping(value = {"/cronJob", "/job"}, method = RequestMethod.GET)
    public ModelAndView task(@RequestParam(value = "name", defaultValue = "World") String name) {

        //Data view table
        ModelAndView mv = new ModelAndView("task");
        //add data object from the model
        mv.addObject("taskSchJob", new TaskSchJob());
        //fetch data from the model
        List<TaskSchJob> taskSchJobList = dmttDAO.getTaskSchJobs();
        //show the data as datatable
        mv.addObject("theTaskSchJobList", taskSchJobList);
//
        return mv;
    }

    /**
     * @param theSchJob
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "/addtaskScheduler", method = RequestMethod.POST)
    public String addCronJob(@ModelAttribute TaskSchJob theSchJob, Model model, HttpServletRequest req) {
        if (null != theSchJob) {

            if (!AppUtil.isObjectEmpty(theSchJob.getTitle())
//                    && !AppUtil.isObjectEmpty(theSchJob.getUsername())
                    && !AppUtil.isObjectEmpty(theSchJob.getScript())
//                    && !AppUtil.isObjectEmpty(theSchJob.getDate_create())
//                    && !AppUtil.isObjectEmpty(theSchJob.getCron_job())
//                    && !AppUtil.isObjectEmpty(theSchJob.getStatus())
                    ) {

                TaskSchJob TaskSchJob = new TaskSchJob();
                TaskSchJob.setTitle(theSchJob.getTitle());
                TaskSchJob.setUsername(theSchJob.getUsername());
                TaskSchJob.setScript(theSchJob.getScript());
                TaskSchJob.setDate_create(theSchJob.getDate_create());
                TaskSchJob.setCron_job(theSchJob.getCron_job());
                TaskSchJob.setStatus(theSchJob.getStatus());

                taskSchJobREPO.save(TaskSchJob);
            }
        }

        //System.out.println("GET#############>>>>" + theSchJob.getScript());
        return "redirect:/cronJob";
    }

    /**
     * Run job button
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskRun/{id}", method = RequestMethod.GET, params = "actionRun")
    public String taskRun(Model model, @PathVariable(required = true, name = "id") Long id) throws ClassNotFoundException, SQLException {

        System.out.println("The id is: " + id);

        //------
        //Fetch the script by using the id
        //------

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        taskSchJobDao schJobDao = (taskSchJobDao) context.getBean("taskSchJobDAO");

        TaskSchJob = schJobDao.findTaskSchJob(Math.toIntExact(id));

        System.out.println("----------------------");
        System.out.println("My script is ########>>>" + TaskSchJob.getScript());
        System.out.println("----------------------");

        //fetch script from the db based on = id
        ScriptJobManager jobManager = new ScriptJobManager(Math.toIntExact(id));
        //run script
        ScriptJobManager executeScript = new ScriptJobManager(TaskSchJob.getScript());


        return "redirect:/cronJob";
    }

    /**
     * Pause job button
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = {"/taskRun", "/taskEdit/{id}"}, method = RequestMethod.GET, params = "actionPause")
    public String taskPause(Model model, @PathVariable(required = true, name = "id") Long id) {

        System.out.println("Start post request: the id is: " + id);

        return "redirect:/cronJob";
    }

    /**
     * Edit job button
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskRun/{id}", method = RequestMethod.GET, params = "actionEdit")
    public String taskEdit(Model model, @PathVariable(required = true, name = "id") Long id) {

        System.out.println("Start post request: the id is: " + id);


        return "redirect:/cronJob";
    }

    /**
     * Delete job button
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskRun/{id}", method = RequestMethod.GET, params = "actionDelete")
    public String taskDelete(Model model, @PathVariable(required = true, name = "id") Long id) {

        System.out.println("Start post request: the id is: " + id);


        return "redirect:/cronJob";
    }

}
