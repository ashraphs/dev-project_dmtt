package com.portal.dmtt.model;


import javax.persistence.*;

@Entity
@Table(name = "taskreq")
public class TaskReq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "jira")
    private String jira;

    @Column(name = "requestdesc")
    private String requestdesc;

    @Column(name = "requestdate")
    private String requestdate;

    @Column(name = "requestor")
    private String requestor;

    @Column(name = "department")
    private String department;

    @Column(name = "completedate")
    private String completedate;

    @Column(name = "pic")
    private String pic;

    @Column(name = "status")
    private String status;


    //setter and getter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJira() {
        return jira;
    }

    public void setJira(String jira) {
        this.jira = jira;
    }

    public String getRequestdesc() {
        return requestdesc;
    }

    public void setRequestdesc(String requestdesc) {
        this.requestdesc = requestdesc;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompletedate() {
        return completedate;
    }

    public void setCompletedate(String completedate) {
        this.completedate = completedate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

