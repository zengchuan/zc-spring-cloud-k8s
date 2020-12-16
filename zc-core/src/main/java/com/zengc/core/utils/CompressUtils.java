package com.zengc.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.Expand;
import org.springframework.context.annotation.Description;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by EagleStrike on 2018/4/16.
 */
@Description("压缩包处理工具")
public class CompressUtils {

    /**
     * @param compressPath
     * @param targetPath
     * @return void
     * @throws
     * @title unzip
     * @description 解压 —— ZIP
     * @author EagleStrike
     * @date 2018-04-16 20:54
     * @modifier
     * @remark
     * @version V1.0
     */
    public static void unzip(String compressPath, String targetPath) throws Exception {

        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setSrc(new File(compressPath));
        expand.setDest(new File(targetPath));
        expand.setOverwrite(true);
        expand.setEncoding("UTF-8");
        expand.execute();
    }

    /**
     * @param zipFile
     * @param descDir
     * @param folderName
     * @return void
     * @throws
     * @title unZipFiles
     * @description  解压文件到指定文件夹 —— 多级目录
     * @author EagleStrike
     * @date 2018-04-17 14:37
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String unZipFiles(File zipFile, String descDir, String folderName) throws Exception {

        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            // 重定义文件夹名称
            if (null != folderName) {
                Integer index = zipEntryName.indexOf("/");
                if (-1 != index) {
                    String pathSuffix = zipEntryName.substring(index);
                    zipEntryName = folderName + pathSuffix;
                } else {
                    zipEntryName = folderName + "/" + zipEntryName;
                }
            }
            String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");

            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            if (new File(outPath).isDirectory()) {
                continue;
            }

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        String folderPath = StringUtils.removeEndIgnoreCase(descDir, "/") + "/" + folderName;
        File file = new File(folderPath);
        if (!(file.exists() && file.isDirectory() && file.listFiles().length > 0)) {
            zip.close();
            return null;
        }
        zip.close();
        return folderPath;
    }
    /**
     * 压缩文件夹
     * @title compressDir
     * @author dingzd
     * @date 2019-10-10 11:42
     * @param dir
     * @param zos
     * @param baseDir
     * @return
     */
    private static void compressDir(File dir, ZipOutputStream zos, String baseDir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        if(files.length == 0){
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }
    public static void compress(String srcFilePath, String destFilePath) {
        //
        File src = new File(srcFilePath);

        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     * @param src
     * @param zos
     * @param baseDir
     */
    private static void compressbyType(File src, ZipOutputStream zos,String baseDir) {

        if (!src.exists())
            return;
        System.out.println("压缩路径" + baseDir + src.getName());
        //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
        if (src.isFile()) {
            //src是文件，调用此方法
            compressFile(src, zos, baseDir);

        } else if (src.isDirectory()) {
            //src是文件夹，调用此方法
            compressDir(src, zos, baseDir);

        }

    }

    /**
     * 压缩文件
     */
    private static void compressFile(File file, ZipOutputStream zos,String baseDir) {
        if (!file.exists())
            return;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zos.putNextEntry(entry);
            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                zos.write(buf, 0, count);
            }
            bis.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
