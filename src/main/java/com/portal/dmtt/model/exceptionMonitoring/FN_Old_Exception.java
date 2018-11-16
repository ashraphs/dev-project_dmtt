package com.portal.dmtt.model.exceptionMonitoring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fn_old_exception")
public class FN_Old_Exception {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "SP_ID")
    private String spId;

    @Column(name = "LOC_ID")
    private String locId;

    @Column(name = "XFER_XMIT_STATUS")
    private String xFerXmitStatus;

    @Column(name = "XFER_FILE_NAME")
    private String xferFileName;

    @Column(name = "UPDATE_TS")
    private String updateTs;

    @Column(name = "YYMM")
    private String YYMM;

    @Column(name = "REMARKS")
    private String remarks;

    //getter and setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getxFerXmitStatus() {
        return xFerXmitStatus;
    }

    public void setxFerXmitStatus(String xFerXmitStatus) {
        this.xFerXmitStatus = xFerXmitStatus;
    }

    public String getXferFileName() {
        return xferFileName;
    }

    public void setXferFileName(String xferFileName) {
        this.xferFileName = xferFileName;
    }

    public String getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(String updateTs) {
        this.updateTs = updateTs;
    }

    public String getYYMM() {
        return YYMM;
    }

    public void setYYMM(String YYMM) {
        this.YYMM = YYMM;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    //full to string
    @Override
    public String toString() {
        return "FN_Old_Exception{" +
                "id=" + id +
                ", spId='" + spId + '\'' +
                ", locId='" + locId + '\'' +
                ", xFerXmitStatus='" + xFerXmitStatus + '\'' +
                ", xferFileName='" + xferFileName + '\'' +
                ", updateTs='" + updateTs + '\'' +
                ", YYMM='" + YYMM + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

}

