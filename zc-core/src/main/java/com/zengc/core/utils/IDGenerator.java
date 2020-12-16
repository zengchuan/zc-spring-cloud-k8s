package com.zengc.core.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author dingzd
 * @title: IDGenerator
 * @projectName healthcare_server
 * @description:编号生成器
 * @date 2019/5/2111:15
 */
public class IDGenerator {

    /**
     * @description: 编号生成器
     * @param id 客户id
     * @param type 0:客户编号 1：档案编号2：体检编号 3：病例编号 4：文件编号 5：量表编号 6:通用主键(uuid 去除‘-’)
     * @author dingzd
     * @apiNote  2019/5/25 11:05
     */
    public static String idCreated(int type,int id){

        StringBuilder stringBuilder = new StringBuilder();
        switch (type){
            case 0://客户ID
                stringBuilder.append("06640801.").append(DateUtils.getNowDate(4)).append(".").append(String.format("%05d", id));
                break;
            case 1://档案编号
                stringBuilder.append(DateUtils.getNowDate(2)).append(String.format("%06d", id));
                break;
            case 2://体检编号
                stringBuilder.append("HC"+DateUtils.getNowDate(4)).append(String.format("%05d", id));
                break;
            case 3://病例编号
                stringBuilder.append("CA"+DateUtils.getNowDate(4)).append(String.format("%05d", id));
                break;
            case 4://文件编号
                stringBuilder.append("FN"+DateUtils.getNowDate(2)).append(String.format("%03d", id));
                break;
            case 5://量表编号
                stringBuilder.append("QN"+DateUtils.getNowDate(4)).append(String.format("%05d", id));
                break;
            case 6://通用主键(uuid 去除‘-’)
                stringBuilder.append(UUID.randomUUID().toString().replaceAll("-",""));
                break;
            case 7://imgID
                stringBuilder.append("AI").append(String.format("%08d", id));
                break;
            default:
                break;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(IDGenerator.class);
        StringBuilder stringBuilder = new StringBuilder();
        log.info("----------{}",stringBuilder.append(DateUtils.getNowDate(2)).append(String.format("%06d", 1234567)));
    }
}
