package com.example.minsookang.soms;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONReadActivity extends AsyncTask<String,Void,String> {
    ArrayList<Userinfo> mArrayList = new ArrayList<Userinfo>() ;

    private String str, receiveMsg;

    private String convertStreamToString(InputStream is) {

        BufferedReader rd = new BufferedReader(new InputStreamReader(is), 4096);

        String line;

        StringBuilder sb =  new StringBuilder();

        try {

            while ((line = rd.readLine()) != null) {

                sb.append(line);

            }

            rd.close();



        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        String contentOfMyInputStream = sb.toString();

        return contentOfMyInputStream;

    }
    @Override
    public String doInBackground(String... params) {
        URL url = null;
        receiveMsg = "sdf";

        try {
            url = new URL("https://pt4k37io35.execute-api.us-east-2.amazonaws.com/alphatest/test");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == 200) {

                InputStream responseBody = conn.getInputStream();

                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                String temp = convertStreamToString(responseBody);
                //System.out.printf("%s",temp);

                JSONObject jsonob = new JSONObject(temp);
                int statuscode = jsonob.getInt("statusCode");

                switch(statuscode){
                    case 200:

                        JSONArray jsonar = jsonob.getJSONArray("dummytest"); // Fetch the next key
                        for (int i = 0; i < jsonar.length(); i++) {

                            JSONObject t_json = jsonar.getJSONObject(i);

                            Log.d("User_serial_num", ""+t_json.getInt("User_serial_num")); //군번 10자리
                            Log.d("Troop_code", ""+t_json.getInt("Troop_code"));          // 부대번호 4자리
                            Log.d("Access_authority", ""+t_json.getInt("Access_authority")); //회원가입 승인여부 0은 대기 1은 승인
                            Log.d("User_class", ""+t_json.getInt("User_class"));             //계급 0이면 병사,1이면 간부,2는 지휘관
                            Log.d("OutingState", ""+t_json.getInt("OutingState"));         //출타상태 0이면 잔류,1이면 휴가, 2는 외출, 3은 외박
                            Log.d("OutingType", ""+t_json.getInt("OutingType"));           //휴가종류 0이면 정기, 1이면 포상, 2는 위로
                            Log.d("OutingRemain_regular", ""+t_json.getInt("OutingRemain_regular")); // 정기휴가 남은일수 디폴트는 21
                            Log.d("OutingRemain_reward", ""+t_json.getInt("OutingRemain_reward"));  // 포상휴가 남은일수 디폴트는 10
                            Log.d("OutingRemain_grant", ""+t_json.getInt("OutingRemain_grant"));    // 위로휴가 남은 일수 디폴트는 14
                            //Log.d("OutingStart_date", ""+t_json.getInt("OutingStart_date"));     // 휴가 출발일 ex 20190321 앞에 4자리 년도, 다음2자리 월, 다음2자리 일
                            //Log.d("OutingArrive_date", ""+t_json.getInt("OutingArrive_date"));   // 휴가 도착일 위와 동일
                            Userinfo userinfo = new Userinfo();
                            userinfo.setUser_name(t_json.getString("User_name"));
                            userinfo.setUser_serialNum(t_json.getInt("User_serial_num"));
                            userinfo.setTroop_code(t_json.getInt("Troop_code"));
                            userinfo.setAccess_authority(t_json.getInt("Access_authority"));
                            userinfo.setUser_class(t_json.getInt("User_class"));
                            userinfo.setOuting_state(t_json.getInt("OutingState"));
                            userinfo.setVac_type(t_json.getInt("OutingType"));
                            userinfo.setCurrent_routine(t_json.getInt("OutingRemain_regular"));
                            userinfo.setCurrent_prize(t_json.getInt("OutingRemain_reward"));
                            userinfo.setCurrent_comfort(t_json.getInt("OutingRemain_grant"));
                            mArrayList.add(userinfo);


//                                    Log.d("Name" ,t_data);
//                                    results.add(t_data);
//                                    results.add(t_json.getInt("User_serial_num")+"");
//                                    results.add(t_json.getInt("Troop_code")+"");
//                                    results.add(t_json.getInt("Access_authority")+"");
//                                    results.add(t_json.getInt("User_class")+"");
//                                    results.add(t_json.getInt("OutingState")+"");
//                                    results.add(t_json.getInt("OutingType")+"");
//                                    results.add(t_json.getInt("OutingRemain_regular")+"");
//                                    results.add(t_json.getInt("OutingRemain_reward")+"");
//                                    results.add(t_json.getInt("OutingRemain_grant")+"");
                            //results.add(t_json10.getInt("OutingStart_date")+"");
                            //results.add(t_json11.getInt("OutingArrive_date")+"");
                        }

                        break;

                    case 2:


                        break;
                }



            }
        } catch (MalformedURLException e) {
            e.printStackTrace();

            Log.d("malformurl","err");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Io실패","err");
        }
        catch(Exception e ){
            e.printStackTrace();
        }

        return receiveMsg;
    }


    public ArrayList<Userinfo> returnUserinfo()
    {
        return mArrayList;
    }


}