package com.portal.dmtt.model.exceptionMonitoring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fn_ready_exception")
public class FN_Result_Set {

    @Id
    @Column(name = "id")
    private int Id;

    @Column(name = "Sp_Id")
    private String Sp_Id;

    @Column(name = "Loc_Id")
    private String Loc_Id;

    @Column(name = "Xfer_Xmit_Status")
    private String Xfer_Xmit_Status;

    @Column(name = "Xfer_File_Name")
    private String Xfer_File_Name;

    @Column(name = "Update_Ts")
    private String Update_Ts;

    @Column(name = "YYMM")
    private String YYMM;

    @Column(name = "Remarks")
    private String Remarks;

    //constructor


    public FN_Result_Set() {
    }

    public FN_Result_Set(String sp_Id, String loc_Id, String xfer_Xmit_Status, String xfer_File_Name, String update_Ts, String YYMM, String remarks) {
        Sp_Id = sp_Id;
        Loc_Id = loc_Id;
        Xfer_Xmit_Status = xfer_Xmit_Status;
        Xfer_File_Name = xfer_File_Name;
        Update_Ts = update_Ts;
        this.YYMM = YYMM;
        Remarks = remarks;
    }

    public FN_Result_Set(String sp_Id, String loc_Id, String remarks) {
        Sp_Id = sp_Id;
        Loc_Id = loc_Id;
        Remarks = remarks;
    }

    //setter and getter


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSp_Id() {
        return Sp_Id;
    }

    public void setSp_Id(String sp_Id) {
        Sp_Id = sp_Id;
    }

    public String getLoc_Id() {
        return Loc_Id;
    }

    public void setLoc_Id(String loc_Id) {
        Loc_Id = loc_Id;
    }

    public String getXfer_Xmit_Status() {
        return Xfer_Xmit_Status;
    }

    public void setXfer_Xmit_Status(String xfer_Xmit_Status) {
        Xfer_Xmit_Status = xfer_Xmit_Status;
    }

    public String getXfer_File_Name() {
        return Xfer_File_Name;
    }

    public void setXfer_File_Name(String xfer_File_Name) {
        Xfer_File_Name = xfer_File_Name;
    }

    public String getUpdate_Ts() {
        return Update_Ts;
    }

    public void setUpdate_Ts(String update_Ts) {
        Update_Ts = update_Ts;
    }

    public String getYYMM() {
        return YYMM;
    }

    public void setYYMM(String YYMM) {
        this.YYMM = YYMM;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    @Override
    public String toString() {
        return "FN_Result_Set{" +
                "Id=" + Id +
                ", Sp_Id='" + Sp_Id + '\'' +
                ", Loc_Id='" + Loc_Id + '\'' +
                ", Xfer_Xmit_Status='" + Xfer_Xmit_Status + '\'' +
                ", Xfer_File_Name='" + Xfer_File_Name + '\'' +
                ", Update_Ts='" + Update_Ts + '\'' +
                ", YYMM='" + YYMM + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }
}



