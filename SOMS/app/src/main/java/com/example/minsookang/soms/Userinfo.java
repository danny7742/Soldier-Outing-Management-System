package com.example.minsookang.soms;

import java.io.Serializable;

public class Userinfo implements Serializable{

    private static final long serialVersionUID = 1L;
    private String user_name;
    private int user_serialNum;
    private int troop_code;
    private int access_authority;
    private int user_class;
    private int outing_state;
    private int vac_type;
    private int current_routine;
    private int current_prize;
    private int current_comfort;

    public String getUser_Name(){
        return user_name;
    }

    public int getUser_serialNum(){
        return user_serialNum;
    }

    public int getTroop_code(){
        return troop_code;
    }

    public int getAccess_authority(){
        return access_authority;
    }

    public int getUser_class(){
        return user_class;
    }

    public int getOuting_state(){
        return outing_state;
    }

    public int getVac_type(){
        return vac_type;
    }

    public int getCurrent_routine(){
        return current_routine;
    }

    public int getCurrent_prize(){
        return current_prize;
    }

    public int getCurrent_comfort(){
        return current_comfort;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public void setUser_serialNum(int user_serialNum){
        this.user_serialNum = user_serialNum;
    }

    public void setTroop_code(int troop_code){
        this.troop_code = troop_code;
    }

    public void setAccess_authority(int access_authority){
        this.access_authority = access_authority;
    }

    public void setUser_class(int user_class){
        this.user_class = user_class;
    }

    public void setOuting_state(int outing_state){
        this.outing_state = outing_state;
    }

    public void setVac_type(int vac_type){
        this.vac_type = vac_type;
    }

    public void setCurrent_routine(int current_routine){
        this.current_routine = current_routine;
    }

    public void setCurrent_prize(int current_prize){
        this.current_prize = current_prize;
    }

    public void setCurrent_comfort(int current_comfort){
        this.current_comfort = current_comfort;
    }
}


