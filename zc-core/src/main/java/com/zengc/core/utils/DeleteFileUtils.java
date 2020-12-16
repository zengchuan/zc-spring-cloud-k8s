package com.zengc.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author dingzd
 * @title: DeleteFileUtils
 * @projectName healthcare_server
 * @description:
 * @date 2019/5/27 20:09
 */
public class DeleteFileUtils {

    protected static  Logger log = LoggerFactory.getLogger(DeleteFileUtils.class);

    /**
     * @description: 删除单个文件或文件夹
     * @author dingzd
     * @date 2019/5/27 20:10
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.info("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            }
            else{
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)){
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            log.info("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = DeleteFileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag){
                    break;
                }

            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = DeleteFileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag){
                    break;
                }
            }
        }
        if (!flag) {
            log.info("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            log.info("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
    public static void removeDir(String dir) {
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            log.info("删除目录失败：" + dir + "不存在！");
            return ;
        }
        File[] files=dirFile.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                removeDir(file.getAbsolutePath());
            }else{
                System.out.println(file+":"+file.delete());
            }
        }
        System.out.println(dir+":"+dirFile.delete());
    }

    public static void main(String[] args) {


    }
}
