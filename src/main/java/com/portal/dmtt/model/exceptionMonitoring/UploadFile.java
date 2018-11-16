package com.portal.dmtt.model.exceptionMonitoring;


import javax.persistence.*;

@Entity
@Table(name = "files_upload")
public class UploadFile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_data")
    private byte[] data;


    //setter and getter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
