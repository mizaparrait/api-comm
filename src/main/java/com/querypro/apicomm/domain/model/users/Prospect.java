package com.querypro.apicomm.domain.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "_prospect")
public class Prospect {

    @Id
    @GeneratedValue
    private Integer id;
    private String names;
    private String email;
    private String lastNames;
    private String tel;

    public Prospect(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Prospect(Integer id, String names, String lastNames, String tel){
        this.id=id;
        this.names=names;
        this.lastNames=lastNames;
        this.tel=tel;
    }

    public Prospect(String names, String lastNamesm, String tel){
        this.names=names;
        this.lastNames=names;
        this.tel=tel;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
