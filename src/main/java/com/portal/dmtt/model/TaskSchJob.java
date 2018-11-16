package com.portal.dmtt.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "task_Sch_Job")
public class TaskSchJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "username")
    private String username;

    @Column(name = "script")
    private String script;

    @Column(name = "date_create")
    private Date date_create;

    @Column(name = "cron_job")
    private String cron_job;

    @Column(name = "status")
    private String status;


    public TaskSchJob(String title, String script) {
        this.title = title;
        this.script = script;
    }

    public TaskSchJob() {

    }

    //------
    //setter and getter
    //------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public String getCron_job() {
        return cron_job;
    }

    public void setCron_job(String cron_job) {
        this.cron_job = cron_job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return script;
    }
}

