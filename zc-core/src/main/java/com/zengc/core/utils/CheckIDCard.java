package com.zengc.core.utils;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author dingzd
 * @title: CheckIDCard
 * @projectName healthcare_server
 * @description:
 * @date 2019/5/2019:30
 */
public class CheckIDCard {

    public static ResultInfo verifyIDCard(String idCard){

        ResultInfo resultInfo = new ResultInfo();
        if (idCard!=null&&idCard.trim().length() == 18) {
            String isIDCard ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";
            //验证身份证
            if (idCard.matches(isIDCard)) {
                if(verifyIDCardCode(idCard)){
                    resultInfo.setCodeMsg(0,"身份证验证通过");
                    int sex = Integer.parseInt(idCard.substring(16,17));
                    if(sex%2==0){
                        resultInfo.setData("sex","女");
                    }else{
                        resultInfo.setData("sex","男");
                    }
                    String birthday = idCard.substring(6,14);
                    LocalDate localDate = LocalDate.parse(birthday, DateTimeFormatter.BASIC_ISO_DATE);
                    birthday = localDate.toString();
                    resultInfo.setData("birthday",DateUtils.stringToDate(birthday));
                }else{
                    resultInfo.setCodeMsg(2,"身份证校验码异常："+idCard);
                }
            }else {
                resultInfo.setCodeMsg(2,"身份证正则校验异常："+idCard);
            }
        }else{
            resultInfo.setCodeMsg(2,"身份证长度异常："+idCard);
        }
        return resultInfo;
    }
    private static boolean verifyIDCardCode(String idCard){

            int i = 0;
            String r = "error";
            String lastnumber = "";
            i += Integer.parseInt(idCard.substring(0, 1)) * 7;
            i += Integer.parseInt(idCard.substring(1, 2)) * 9;
            i += Integer.parseInt(idCard.substring(2, 3)) * 10;
            i += Integer.parseInt(idCard.substring(3, 4)) * 5;
            i += Integer.parseInt(idCard.substring(4, 5)) * 8;
            i += Integer.parseInt(idCard.substring(5, 6)) * 4;
            i += Integer.parseInt(idCard.substring(6, 7)) * 2;
            i += Integer.parseInt(idCard.substring(7, 8)) * 1;
            i += Integer.parseInt(idCard.substring(8, 9)) * 6;
            i += Integer.parseInt(idCard.substring(9, 10)) * 3;
            i += Integer.parseInt(idCard.substring(10,11)) * 7;
            i += Integer.parseInt(idCard.substring(11,12)) * 9;
            i += Integer.parseInt(idCard.substring(12,13)) * 10;
            i += Integer.parseInt(idCard.substring(13,14)) * 5;
            i += Integer.parseInt(idCard.substring(14,15)) * 8;
            i += Integer.parseInt(idCard.substring(15,16)) * 4;
            i += Integer.parseInt(idCard.substring(16,17)) * 2;
            i = i % 11;
            lastnumber =idCard.substring(17,18);
            if (i == 0) {
                r = "1";
            }
            if (i == 1) {
                r = "0";
            }
            if (i == 2) {
                r = "x";
            }
            if (i == 3) {
                r = "9";
            }
            if (i == 4) {
                r = "8";
            }
            if (i == 5) {
                r = "7";
            }
            if (i == 6) {
                r = "6";
            }
            if (i == 7) {
                r = "5";
            }
            if (i == 8) {
                r = "4";
            }
            if (i == 9) {
                r = "3";
            }
            if (i == 10) {
                r = "2";
            }
            if (r.equals(lastnumber.toLowerCase())) {
                return true;
            }
            return false;

    }
}
