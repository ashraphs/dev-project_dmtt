package com.portal.dmtt.model.exceptionMonitoring.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuration_fn_exception")
public class FN_FR_new_Location {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "match_sp_id")
    private String match_SP_Id;

    @Column(name = "match_loc_id")
    private String match_Loc_Id;

    @Column(name = "remarks")
    private String remarks;

    //setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatch_SP_Id() {
        return match_SP_Id;
    }

    public void setMatch_SP_Id(String match_SP_Id) {
        this.match_SP_Id = match_SP_Id;
    }

    public String getMatch_Loc_Id() {
        return match_Loc_Id;
    }

    public void setMatch_Loc_Id(String match_Loc_Id) {
        this.match_Loc_Id = match_Loc_Id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    //to string


    @Override
    public String toString() {
        return "FN_FR_new_Location{" +
                "id=" + id +
                ", match_SP_Id='" + match_SP_Id + '\'' +
                ", match_Loc_Id='" + match_Loc_Id + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
