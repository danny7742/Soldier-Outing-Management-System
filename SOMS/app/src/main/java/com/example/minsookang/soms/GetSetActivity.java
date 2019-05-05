package com.example.minsookang.soms;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class GetSetActivity implements Serializable{

    private String UserName = "";
    private String Password = "";

    private int UserClass = 0;     // 0은 병사, 1은 간부 2는 지휘관
    private int TroopCode = 0;
    private int SerialNum = 0;     //10자리는 병사 8자리는 간부, 지휘관
    private int OutingType = 0;    //0이면 정기휴가 ,1이면 포상, 2면 위로
    private int OutingState = 0;   //0은 잔류, 1은 휴가 , 2는 외출, 3은 외박

    private int OutingRemainRegular = 21;
    private int OutingRemainReward = 0;
    private int OutingRemainGrant = 0;

    private boolean Authority = false;     //지휘관은 true 나머지는 false
    private boolean AccessAuthrity = false;//회원가입하면 처음에 false, 지휘관이 승인하면 true

    private SimpleDateFormat OutingStartDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private SimpleDateFormat OutingArriveDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public GetSetActivity(String UserName){
        this.UserName = UserName;
    }

    String getUserName(){ return UserName; }

    String getPassword(){ return Password; }

    int getUserClass(){ return UserClass; }

    int getTroopCode(){ return TroopCode; }

    int getSerialNum(){ return SerialNum; }

    int getOutingType(){ return OutingType; }

    int getOutingState(){ return OutingState; }

    boolean getAuthority(){ return Authority; }

    boolean getAccessAuthority(){ return AccessAuthrity; }

    int getOutingRemainRegular(){ return OutingRemainRegular; }

    int getOutingRemainReward(){ return OutingRemainReward; }

    int getOutingRemainGrant(){ return OutingRemainGrant; }

    SimpleDateFormat getOutingStartDate(){
        return OutingStartDate;
    }

    SimpleDateFormat getOutingArriveDate(){
        return OutingArriveDate;
    }
}