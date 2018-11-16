package com.portal.dmtt.model.exceptionMonitoring.config;

import javax.persistence.*;

@Entity
@Table(name = "EXCP_File")
public class EXCP_File {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "time_stamp")
    private String timeStamp;

    //constructor
    public EXCP_File() {
    }

    //setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "EXCP_File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", filePath='" + filePath + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
