package com.example.mail_project.util;

import java.util.ArrayList;
import java.util.List;

public class ListKeyword{

    public static   List<String>  LIST_KEYWORD = new ArrayList<>();
    public static   List<String>  LIST_PROGRAN = new ArrayList<>();


    public static List<String> setDataKeyword() {
        LIST_KEYWORD.add("พัง");
        LIST_KEYWORD.add("เสีย");
        LIST_KEYWORD.add("แก้ไข");
        LIST_KEYWORD.add("ใช้ไม่ได้");
        LIST_KEYWORD.add("ล่ม");
        LIST_KEYWORD.add("มีปัญหา");
        LIST_KEYWORD.add("เจ๊ง");
        LIST_KEYWORD.add("ไม่แสดง");
        LIST_KEYWORD.add("ไม่พบ");
        LIST_KEYWORD.add("ไม่ครบ");
        LIST_KEYWORD.add("ห้าม");
        LIST_KEYWORD.add("ไม่มี");
        LIST_KEYWORD.add("หาย");
        LIST_KEYWORD.add("ปฎิเสธ");
        LIST_KEYWORD.add("ปฏิเสธ");
        LIST_KEYWORD.add("ไม่เจอ");
        LIST_KEYWORD.add("ไม่เห็น");
        LIST_KEYWORD.add("ไม่คบ");
        LIST_KEYWORD.add("ไม่พร้อม");
        LIST_KEYWORD.add("ผิด");
        LIST_KEYWORD.add("เปลี่ยน");
        LIST_KEYWORD.add("บัก");
        LIST_KEYWORD.add("บัค");
        LIST_KEYWORD.add("ไม่ขึ้น");
        LIST_KEYWORD.add("พลาด");
        LIST_KEYWORD.add("ไม่ออก");
        LIST_KEYWORD.add("ไม่ดี");
        LIST_KEYWORD.add("ไม่สมบูรณ์");
        LIST_KEYWORD.add("ไม่สมบูร");
        LIST_KEYWORD.add("แย่");
        return null;
    }


    public static List<String> setDataProgran() {
        LIST_PROGRAN.add("โปรแกรม");
        LIST_PROGRAN.add("ระบบ");
        LIST_PROGRAN.add("งาน");
        LIST_PROGRAN.add("เว็บ");
        LIST_PROGRAN.add("เว็ป");
        return null;
    }


    public static boolean checkTextMatches(String str){
        setDataKeyword();
        setDataProgran();

        int countKeyword = 0;
        int countProgram = 0;

        for(String keyword : LIST_KEYWORD){

            if(str.indexOf(keyword) >=0){
                countKeyword++;

            }
        }

        for(String keyword : LIST_PROGRAN){

            if(str.indexOf(keyword) >=0){
                countProgram++;
                break;
            }
        }

        if(countKeyword != 0 && countProgram !=0 ){
            return  true;

        }else{
            return false;
        }

    }

}
