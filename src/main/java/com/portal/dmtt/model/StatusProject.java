package com.portal.dmtt.model;

import javax.persistence.*;

@Entity
@Table(name = "statusproject")
public class StatusProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "itotask")
    private String itotask;

    @Column(name = "status")
    private String status;

    @Column(name = "action")
    private String action;


    //setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getItotask() {
        return itotask;
    }

    public void setItotask(String itotask) {
        this.itotask = itotask;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    //to string

    @Override
    public String toString() {
        return "StatusProject{" +
                "id=" + id +
                ", projectname='" + projectname + '\'' +
                ", itotask='" + itotask + '\'' +
                ", status='" + status + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
