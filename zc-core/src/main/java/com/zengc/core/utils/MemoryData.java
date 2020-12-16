package com.zengc.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dingzd
 * @title: MemoryData
 * @projectName healthcare_server
 * @description:
 * @date 2019/5/2214:55
 */
public class MemoryData {
    protected static Logger log = LoggerFactory.getLogger(MemoryData.class);
    //线程安全
    //sessionID 与用户ID保存
    private static Map<Integer,String> sessionIDMap = new ConcurrentHashMap<>();
    //sessionID 与Session 保存
    //private static Map<String,HttpSession> sessionMap = new ConcurrentHashMap<>();

    private MemoryData() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * @description: 添加用户信息进入map和session
     * @author dingzd
     * @date 2019/5/22 15:02 
     */
    public static void addUserInfo(HttpServletRequest request,Integer id){
        request.getSession().setAttribute("loginUser", id);
        String sessionID = request.getSession().getId();
        sessionIDMap.put(id,sessionID);
    }
    public static String getSessionId(Integer id){
        return sessionIDMap.get(id);
    }
    private static Integer getKey(String sessionID){
        String key = null;
        //Map,HashMap并没有实现Iteratable接口.不能用于增强for循环.
        for(int getKey: sessionIDMap.keySet()){
            if(sessionIDMap.get(getKey).equals(sessionID)){
                return getKey;
            }
        }
        return null;
        //这个key肯定是最后一个满足该条件的key.
    }
    /**
     * @description: 删除用户信息
     * @author dingzd
     * @date 2019/5/22 15:58
     */
    public static void deleteUserInfo(String id){
        if(sessionIDMap.containsKey(id)){
            int key = getKey(id);
            sessionIDMap.remove(key);
        }
    }
}
