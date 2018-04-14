package com.wangwei.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/1.
 */

public class PropUtils {
    public static final String FILEPATH="/drivinglicence_endTime_cache";
    public static final String logout_recovery_endtime = "endTime_logoutRecovery.properties";//注销可恢复
    public static final String overage_endtime = "endTime_overage.properties";//超龄降级
    public static final String super_score_endtime = "endTime_superScore.properties";//超分
    public static final String ENDTIME_NAME = "endTime.properties";

    public static void writeProp(long endTime){
        PrintWriter writer = null;
        File filePath = new File(FILEPATH);
        if(!filePath.exists() && !filePath.isDirectory()){
            filePath.mkdirs();
        }
        File file = new File(FILEPATH + File.separator + ENDTIME_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("createNewFile，出现异常:");
                e.printStackTrace();
            }
        }

        try {

            writer = new PrintWriter(new FileWriter(file));
            Properties prop = new Properties();
            prop.put("endTimeStamp",String.valueOf(endTime));
            prop.list(writer);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeProp(long endTime,String fileName){
        PrintWriter writer = null;
        try {
            if(StringUtils.isEmpty(fileName)){
                throw new IllegalArgumentException("需要写入内容的文件的名称不能为空");
            }
            File filePath = new File(FILEPATH);
            if(!filePath.exists() && !filePath.isDirectory()){
                filePath.mkdirs();
            }
            File file = new File(FILEPATH + File.separator + fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    System.out.println("createNewFile，出现异常:");
                    e.printStackTrace();
                }
            }



                writer = new PrintWriter(new FileWriter(file));
                Properties prop = new Properties();
                prop.put("endTimeStamp",String.valueOf(endTime));
                prop.list(writer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Long readProp(){
        try{
            File filePath = new File(FILEPATH);
            if(!filePath.exists() && !filePath.isDirectory()){
                filePath.mkdirs();
            }
            File file = new File(FILEPATH + File.separator + ENDTIME_NAME);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    System.out.println("createNewFile，出现异常:");
                    e.printStackTrace();
                }
            }
            Properties prop = new Properties();
            prop.load(new FileInputStream(file));

            String timeStr = (String) prop.get("endTimeStamp");
            if(!StringUtils.isEmpty(timeStr)){
                return Long.parseLong(timeStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Long readProp(String fileName){
        try{
            if(StringUtils.isEmpty(fileName)){
                throw new IllegalArgumentException("读取的文件名不能为空");
            }
            File filePath = new File(FILEPATH);
            if(!filePath.exists() && !filePath.isDirectory()){
                filePath.mkdirs();
            }
            File file = new File(FILEPATH + File.separator + fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    System.out.println("createNewFile，出现异常:");
                    e.printStackTrace();
                }
            }
            Properties prop = new Properties();
            prop.load(new FileInputStream(file));

            String timeStr = (String) prop.get("endTimeStamp");
            if(!StringUtils.isEmpty(timeStr)){
                return Long.parseLong(timeStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        Date date = new Date();
        writeProp(date.getTime());
        System.out.println(readProp());
        System.out.println("OVER");
    }
}
