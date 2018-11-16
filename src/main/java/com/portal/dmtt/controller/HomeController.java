package com.portal.dmtt.controller;

import com.portal.dmtt.model.StatusProject;
import com.portal.dmtt.model.TaskReq;
import com.portal.dmtt.repo.dmttDAO;
import com.portal.dmtt.repo.statusProjectREPO;
import com.portal.dmtt.repo.taskReqREPO;
import com.portal.dmtt.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    /**
     *
     */
    @Autowired
    private statusProjectREPO theStatusProjectREPO;

    /**
     *
     */
    @Autowired
    private taskReqREPO theTaskReqREPO;

    /**
     *
     */
    @Autowired
    private dmttDAO theDmttDAO;

    /**
     *
     */
    private TaskReq theTaskReq;

    /**
     *
     */
    private StatusProject theStatusProject;

    /**
     * VIEW FOR DATATABLE
     *
     * @return
     */
    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView Home(Map<String, Object> model) {

        ModelAndView mv = new ModelAndView("home");

        //---------
        //show the data as datatable- Home request form
        //---------
        List<TaskReq> taskReqList = theDmttDAO.getTaskReqs();
        mv.addObject("theTaskRequestList", taskReqList);
        //add data to model JPA - theTaskReq
        mv.addObject("addTaskReq", new TaskReq());

        //---------
        //show the data as datatable- status project
        //---------
        List<StatusProject> statusProjectList = theDmttDAO.getStatusProject();
        mv.addObject("theStatusProjectList", statusProjectList);
        //add data to model JPA - theStatusProject
        mv.addObject("addProject", new StatusProject());

        //---------
        //count = total project
        //---------

        //count -total project
        int countStatusProject = statusProjectList.size();
        mv.addObject("countStatusProject", countStatusProject);

        //count -total taskRequest
        int countTaskRequest = taskReqList.size();
        mv.addObject("countTaskRequest", countTaskRequest);


        return mv;
    }

    /**
     * @param theTaskReq - insert to the DAO object
     * @return = page-home
     */
    @RequestMapping(value = "/addTaskReq", method = RequestMethod.POST)
    public String taskReqAdd(@ModelAttribute TaskReq theTaskReq) {

        if (null != theTaskReq) {

            if (!AppUtil.isObjectEmpty(theTaskReq.getJira())
                    || !AppUtil.isObjectEmpty(theTaskReq.getRequestdesc())
                    || !AppUtil.isObjectEmpty(theTaskReq.getRequestdate())
                    || !AppUtil.isObjectEmpty(theTaskReq.getRequestor())
                    || !AppUtil.isObjectEmpty(theTaskReq.getDepartment())
                    || !AppUtil.isObjectEmpty(theTaskReq.getCompletedate())
                    || !AppUtil.isObjectEmpty(theTaskReq.getPic())
                    || !AppUtil.isObjectEmpty(theTaskReq.getStatus())) {

                this.theTaskReq = new TaskReq();
                this.theTaskReq.setJira(theTaskReq.getJira());
                this.theTaskReq.setRequestdesc(theTaskReq.getRequestdesc());
                this.theTaskReq.setRequestdate(theTaskReq.getRequestdate());
                this.theTaskReq.setRequestor(theTaskReq.getRequestor());
                this.theTaskReq.setDepartment(theTaskReq.getDepartment());
                this.theTaskReq.setCompletedate(theTaskReq.getCompletedate());
                this.theTaskReq.setPic(theTaskReq.getPic());
                this.theTaskReq.setStatus(theTaskReq.getStatus());

                theTaskReqREPO.save(this.theTaskReq);

            } else {

                System.out.println("-------->>>  failed to insert !!");
            }
        }
        return "redirect:/home";
    }

    /**
     * Delete job button - Home request form tracking
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskReq/{id}", method = RequestMethod.GET, params = "actionDelete")
    public String taskReqDelete(@PathVariable(required = true, name = "id") long id) {

        int deleteById = theDmttDAO.deleteTaskRequestById(id);

        return "redirect:/home";
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskReq/{id}", method = RequestMethod.GET, params = "actionEdit")
    public ModelAndView taskReqEdit(@PathVariable(required = true, name = "id") long id) {

        ModelAndView mv = new ModelAndView("editTaskRequest");

        List<TaskReq> taskReqList = theDmttDAO.findTaskReqByID(id);

        for (int i = 0; i < taskReqList.size(); i++) {

            //project id
            int taskReqId = taskReqList.get(i).getId();
            mv.addObject("taskReqId", taskReqId);

            //jira
            String jira = taskReqList.get(i).getJira();
            mv.addObject("jira", jira);

            //request description
            String requestDesc = taskReqList.get(i).getRequestdesc();
            mv.addObject("requestDesc", requestDesc);

            //request date
            String requestDate = taskReqList.get(i).getRequestdate();
            mv.addObject("requestDate", requestDate);

            //requestor
            String requestor = taskReqList.get(i).getRequestor();
            mv.addObject("requestor", requestor);

            //department
            String department = taskReqList.get(i).getDepartment();
            mv.addObject("department", department);

            //complete date
            String completeDate = taskReqList.get(i).getCompletedate();
            mv.addObject("completeDate", completeDate);

            //person in charge
            String pic = taskReqList.get(i).getPic();
            mv.addObject("pic", pic);

            //status
            String status = taskReqList.get(i).getStatus();
            mv.addObject("status", status);

            System.out.println("---------------------------------");
            System.out.println("id: " + taskReqId);
            System.out.println("jira: " + jira);
            System.out.println("request Desc: " + requestDesc);
            System.out.println("request Date: " + requestDate);
            System.out.println("requestor: " + requestor);
            System.out.println("department: " + department);
            System.out.println("complete Date: " + completeDate);
            System.out.println("pic: " + pic);
            System.out.println("status: " + status);
            System.out.println("---------------------------------");

        }

        return mv;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/taskReq/{id}", method = RequestMethod.GET, params = "actionView")
    public ModelAndView taskReqView(@PathVariable(required = true, name = "id") long id) {

        ModelAndView mv = new ModelAndView("viewTaskRequest");

        List<TaskReq> taskReqList = theDmttDAO.findTaskReqByID(id);

        for (int i = 0; i < taskReqList.size(); i++) {

            //project id
            int taskReqId = taskReqList.get(i).getId();
            mv.addObject("taskReqId", taskReqId);

            //jira
            String jira = taskReqList.get(i).getJira();
            mv.addObject("jira", jira);

            //request description
            String requestDesc = taskReqList.get(i).getRequestdesc();
            mv.addObject("requestDesc", requestDesc);

            //request date
            String requestDate = taskReqList.get(i).getRequestdate();
            mv.addObject("requestDate", requestDate);

            //requestor
            String requestor = taskReqList.get(i).getRequestor();
            mv.addObject("requestor", requestor);

            //department
            String department = taskReqList.get(i).getDepartment();
            mv.addObject("department", department);

            //complete date
            String completeDate = taskReqList.get(i).getCompletedate();
            mv.addObject("completeDate", completeDate);

            //person in charge
            String pic = taskReqList.get(i).getPic();
            mv.addObject("pic", pic);

            //status
            String status = taskReqList.get(i).getStatus();
            mv.addObject("status", status);

            System.out.println("---------------------------------");
            System.out.println("id: " + taskReqId);
            System.out.println("jira: " + jira);
            System.out.println("request Desc: " + requestDesc);
            System.out.println("request Date: " + requestDate);
            System.out.println("requestor: " + requestor);
            System.out.println("department: " + department);
            System.out.println("complete Date: " + completeDate);
            System.out.println("pic: " + pic);
            System.out.println("status: " + status);
            System.out.println("---------------------------------");

        }

        return mv;
    }

    /**
     * @param theTaskReq
     * @return
     */
    @RequestMapping(value = "/updateTask/{id}/**", method = RequestMethod.POST)
    public String taskReqUpdate(@ModelAttribute TaskReq theTaskReq) {

        System.out.println("-------------------------------------------------------");
        System.out.println("the task Request id>>>>" + theTaskReq.getId());
        System.out.println("the task Request jira>>>>" + theTaskReq.getJira());
        System.out.println("the task Request request Description>>>>" + theTaskReq.getRequestdesc());
        System.out.println("the task Request request Date>>>>" + theTaskReq.getRequestdate());
        System.out.println("the task Request requestor>>>>" + theTaskReq.getRequestor());
        System.out.println("the task Request department>>>>" + theTaskReq.getDepartment());
        System.out.println("the task Request complete Date>>>>" + theTaskReq.getCompletedate());
        System.out.println("the task Request pic>>>>" + theTaskReq.getPic());
        System.out.println("the task Request status>>>>" + theTaskReq.getStatus());
        System.out.println("-------------------------------------------------------");

        int updateTaskRequestById = theDmttDAO.updateTaskRequestById(Long.valueOf(theTaskReq.getId()),
                theTaskReq.getJira(),
                theTaskReq.getRequestdesc(),
                theTaskReq.getRequestdate(),
                theTaskReq.getRequestor(),
                theTaskReq.getDepartment(),
                theTaskReq.getCompletedate(),
                theTaskReq.getPic(),
                theTaskReq.getStatus());


        return "redirect:/home";
    }

    /**
     * @param theStatusProject
     * @return
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String statusProjectAdd(@ModelAttribute StatusProject theStatusProject) {

        if (null != theStatusProject) {

            if (!AppUtil.isObjectEmpty(theStatusProject.getProjectname())
                    || !AppUtil.isObjectEmpty(theStatusProject.getItotask())
                    || !AppUtil.isObjectEmpty(theStatusProject.getStatus())
                    ) {

                this.theStatusProject = new StatusProject();
                this.theStatusProject.setProjectname(theStatusProject.getProjectname());
                this.theStatusProject.setItotask(theStatusProject.getItotask());
                this.theStatusProject.setStatus(theStatusProject.getStatus());

                theStatusProjectREPO.save(this.theStatusProject);
            }
        }

        return "redirect:/home";
    }

    /**
     * Delete job button - status project form tracking
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/statusProject/{id}", method = RequestMethod.GET, params = "actionDelete")
    public String statusProjectDelete(@PathVariable(required = true, name = "id") long id) {

        int deleteRowById = theDmttDAO.deleteProjectById(id);

        return "redirect:/home";

    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/statusProject/{id}", method = RequestMethod.GET, params = "actionEdit")
    public ModelAndView statusProjectEdit(@PathVariable(required = true, name = "id") Long id) {


        ModelAndView mv = new ModelAndView("editproject");

        List<StatusProject> statusProjectList = theDmttDAO.findStatusProjectById(id);

        for (int i = 0; i < statusProjectList.size(); i++) {

            //project id
            int projectId = statusProjectList.get(i).getId();
            mv.addObject("projectId", projectId);

            //project name
            String projectName = statusProjectList.get(i).getProjectname();
            mv.addObject("projectName", projectName);

            //ito Task
            String projectTask = statusProjectList.get(i).getItotask();
            mv.addObject("projectTask", projectTask);

            //status
            String projectStatus = statusProjectList.get(i).getStatus();
            mv.addObject("projectStatus", projectStatus);

        }

        return mv;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/updateProject/{id}/{test}/{test}/{test}", method = RequestMethod.POST)
    public String statusProjectUpdate(@ModelAttribute StatusProject theStatusProject) {

        System.out.println("-------------------------------------------------------");
        System.out.println("the project ID>>>>>" + theStatusProject.getId());
        System.out.println("the project name>>>>>" + theStatusProject.getProjectname());
        System.out.println("the project name>>>>>" + theStatusProject.getItotask());
        System.out.println("the project name>>>>>" + theStatusProject.getStatus());
        System.out.println("-------------------------------------------------------");

        int updateByid = theDmttDAO.updateProjectById(Long.valueOf(theStatusProject.getId()),
                theStatusProject.getProjectname(),
                theStatusProject.getItotask(),
                theStatusProject.getStatus());


        return "redirect:/home";
    }




}
