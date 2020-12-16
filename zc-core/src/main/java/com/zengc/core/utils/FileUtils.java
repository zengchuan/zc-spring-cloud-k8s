package com.zengc.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Description("文件处理工具")
public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static final String ROOT_PATH = FileUtils.class.getResource("/").getPath();

    /**
     * @param fileName   重定义的下载文件名
     * @param targetFile 源下载文件的全路径地址
     * @param response
     * @description: 文件下载方法
     * @author dingzd
     * @date 2019/5/22 8:46
     */
    public static void fileDownLoad(String targetFile, String fileName, HttpServletResponse response) {

        File file = new File(targetFile);
        FileInputStream fis = null; //文件输入流
        OutputStream fos = null; //输出流
        if (file.exists()) { //判断文件是否存在
            try {

                fileName = new String(fileName.getBytes(), "ISO8859-1");
                response.setContentType("application/octet-stream;charset=ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] b = new byte[1024];//1M
                int read = 0;
                fis = new FileInputStream(targetFile);
                fos = response.getOutputStream();
                while ((read = fis.read(b)) != -1) {
                    fos.write(b, 0, read);//每次写1M
                }
            } catch (Exception e) {
                log.error("文件下载出错");
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    log.error("关闭流出错");
                }
            }

        } else {
            log.error("文件不存在！");
        }
    }

    /**
     * @param filePath //文件保存地址
     * @param fileUp   文件
     *                 map.put("code",0);
     *                 map.put("msg","文件存储成功");
     *                 map.put("fileName",fileName);
     *                 map.put("filePath",sfp);
     * @description: 单文件保存文件
     * @author dingzd
     * @date 2019/5/24 16:32
     */
    public static Map<String, String> saveFile(MultipartFile fileUp, String filePath, int k) {
        Map<String, String> map = new HashMap<>();
        if (fileUp.isEmpty()) {
            map.put("code", "0001");
            map.put("msg", "文件为空");
            return map;
        }
        String suffix = fileUp.getOriginalFilename();
        String[] sf = suffix.split("\\.");
        if (sf.length > 1) {
            suffix = "." + sf[sf.length - 1];
        } else {
            suffix = "";
        }
        createDirectory(filePath);
        String fileName = IDGenerator.idCreated(4, k);
        fileName += suffix;
        String sfp = transferFile(fileUp, filePath, fileName);
        if (null == sfp) {
            map.put("code", "0002");
            map.put("msg", "文件存储异常");
            return map;
        }
        map.put("code", "0000");
        map.put("msg", "文件存储成功");
        map.put("fileName", fileName);
        map.put("filePath", sfp);
        return map;
    }

    /**
     * @param files    文件列表
     * @param filePath 保存位置
     * @return names 保存后文件名称列表
     * @description 多文件保存
     * @author dingzd
     * @date 2019/8/5
     * @remark
     * @version V1.0
     */
    public static Map<String, Object> saveFile(MultipartFile[] files, String filePath) {
        Map<String, Object> map = new HashMap<>();
        List<String> saveFilePathName = new ArrayList<>();
        List<String> saveFileName = new ArrayList<>();
        //创建目录
        createDirectory(filePath);
        if (files == null || files.length == 0) {
            map.put("code", 1);
            map.put("msg", "文件列表为空");
            return map;
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile fileUp = files[i];
            Map<String, String> saveMap = saveFile(fileUp, filePath, i);
            saveFilePathName.add(saveMap.get("filePath"));
            saveFileName.add(saveMap.get("fileName"));
        }
        map.put("code", 0);
        map.put("msg", "文件存储成功");
        map.put("paths", saveFilePathName);
        map.put("names", saveFileName);
        return map;
    }

    /**
     * @param filePath 文件地址
     * @description: 获取文件夹中所有文件名称
     * @author dingzd
     * @date 2019/5/28 9:13
     */
    public static String[] getFileList(String filePath) {

        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                return file.list();
            }
        } catch (Exception e) {
            log.error("readfile()   Exception:{}", e.getMessage());
        }
        return null;
    }

    /**
     * @param fileUp   文件
     * @param filePath 文件保存地址
     * @param suffix   文件后缀名
     * @description: 单文件保存，有后缀
     * @author dingzd
     * @date 2019/5/24 16:35
     */
    public static Map<String, String> saveFile(MultipartFile fileUp, String filePath, String suffix) {
        Map<String, String> map = new HashMap<>();
        if (fileUp.isEmpty()) {
            map.put("code", "0001");
            map.put("msg", "文件为空");
            return map;
        }
        String fileName = fileUp.getOriginalFilename();
        if (fileName.endsWith(suffix)) {
            String saveFilePathName = transferFile(fileUp, filePath);
            if (null == saveFilePathName) {
                map.put("code", "0002");
                map.put("msg", "文件存储异常");
            } else {
                map.put("code", "0000");
                map.put("msg", "文件存储成功");
                map.put("path", saveFilePathName);
            }
        } else {
            map.put("code", "0003");
            map.put("msg", "文件类型不符");
        }
        return map;
    }

    public static String unzip(String filePath, String descDir, String folderName) {
        String path = "";
        try {
            path = CompressUtils.unZipFiles(new File(filePath), descDir, folderName);
        } catch (Exception e) {
            log.error("文件解压失败");
        }
        return path;
    }

    /**
     * @param filePath 文件保存地址
     * @param fileUp   上传文件
     * @param fileName 文件保存名
     * @description: 文件写入保存地址
     * @author dingzd
     * @date 2019/5/24 17:00
     */
    private static String transferFile(MultipartFile fileUp, String filePath, String fileName) {
        try {
            File file = new File(filePath + fileName);
            fileUp.transferTo(file);
            log.info(filePath + fileName + "上传成功");
            return filePath + fileName;
        } catch (IOException e) {
            log.error("上传失败", e);
        }
        return null;
    }

    /**
     * @description: 生成目录
     * @author dingzd
     * @date 2019/5/25 16:48
     */
    public static void createDirectory(String targetPath) {

        File file = new File(targetPath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static String createFile(String targetPath, String fileName, String content) {

        String filePath = targetPath + fileName;
        File file = new File(targetPath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath, true);
            fos.write(content.getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
            filePath = null;
        }
        return filePath;
    }

    /**
     * @param filePath 文件保存地址
     * @param fileUp   上传文件
     * @description: 文件写入保存地址
     * @author dingzd
     * @date 2019/5/24 17:00
     */
    private static String transferFile(MultipartFile fileUp, String filePath) {
        String fileName = fileUp.getOriginalFilename();
        return transferFile(fileUp, filePath, fileName);
    }

    public static void main(String[] args) {
        String fileName = "456546.36.pdf";
        String[] fs = fileName.split("\\.");
        log.info("fs--------------{}", fs.length);
        for (int i = 0; i < fs.length; i++) {
            log.info("fs[i]--------------{}", fs[i]);
        }
    }

    /**
     * @param fileNameList
     * @param targetPath
     * @description 删除目下制定文件
     * @author dingzd
     * @date 2019/8/7
     * @remark
     * @version V1.0
     */
    public static void deleteDirByFileName(List<String> fileNameList, String targetPath) {
        //过滤空值
        if (null == fileNameList || fileNameList.isEmpty()) {
            DeleteFileUtils.deleteDirectory(targetPath);
            return;
        }
        // 1.比对上传文件和实际存储目录文件，删除不在上传文件列表中的数据
        String[] originalFileNames = FileUtils.getFileList(targetPath);

        if (originalFileNames != null) {
            List<String> localFileList = new ArrayList<>();
            Collections.addAll(localFileList, originalFileNames);
            //过滤目录下文件
            for (int i = 0; i < fileNameList.size(); i++) {
                String fileNameUp = fileNameList.get(i);

                if (localFileList.contains(fileNameUp)) {
                    int index = localFileList.indexOf(fileNameUp);
                    localFileList.remove(index);
                } else {
                    fileNameList.remove(i);
                    --i;
                }
            }
            //删除目录下文件
            for (String fileName : localFileList) {
                DeleteFileUtils.deleteFile(targetPath + fileName);
            }
        } else {
            fileNameList = null;
        }
    }

    public static void deleteDir(String targetPath) {
        //过滤空值
        File file = new File(targetPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
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
     *
     * @param src
     * @param zos
     * @param baseDir
     */
    private static void compressbyType(File src, ZipOutputStream zos, String baseDir) {

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
    private static void compressFile(File file, ZipOutputStream zos, String baseDir) {
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
            // TODO: handle exception

        }
    }

    /**
     * 压缩文件夹
     */
    private static void compressDir(File dir, ZipOutputStream zos, String baseDir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        if (files.length == 0) {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName() + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }


}
