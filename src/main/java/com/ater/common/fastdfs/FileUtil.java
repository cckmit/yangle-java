package com.ater.common.fastdfs;

/*
 * 文件名：FileUtils.java 版权：Copyright by www.huawei.com 描述： 修改人：kokJuis 修改时间：2017年7月27日 跟踪单号： 修改单号：
 * 修改内容：
 */


import java.io.*;

public class FileUtil
{

    /**
     * Description: 获取文件后缀名
     *
     * @param fileName
     * @return
     * @see
     */
    public static String getExtensionName(String fileName)
    {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    /**
     * 根据path获取文件名
     *
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     * @param filename
     * @return
     */
    public static String getOriginalFilename(String filename)
    {
        if (filename == null) return "";
        int pos = filename.lastIndexOf("/");
        if (pos == -1) pos = filename.lastIndexOf("\\");
        if (pos != -1)
            return filename.substring(pos + 1);
        else
            return filename;
    }

    /**
     * 根据文件名称转化程byte[]
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
    public static String getFileType(String filename){
        if(filename.endsWith(".jpg") || filename.endsWith(".jepg")){
            return ".jpg";
        }else if(filename.endsWith(".png") || filename.endsWith(".PNG")){
            return ".png";
        } else{
            return "application/octet-stream";
        }
    }
    public static String readFile(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

}
