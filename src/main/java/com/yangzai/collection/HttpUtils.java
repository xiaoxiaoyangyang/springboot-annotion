package com.yangzai.collection;

import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/12 14:49
 * @Version 1.0
 */
public class HttpUtils {
    /**
     * get
     * @throws Exception
     */
    public void getHttpGet() throws Exception {
        //将参数拼接在url上
        URL url = new URL("http://localhost:8080/senseguard-lb");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //设置http请求的通用性功能
        connection.setRequestProperty("accept","*/*");
        connection.setRequestProperty("connection","Keep-Alive");
        connection.setRequestProperty("Accept","application/json,text/plain,*/*");
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if(responseCode==HttpURLConnection.HTTP_OK){
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            while (true){
                int read = inputStream.read(bytes);
                if(read==-1){
                    break;
                }
                outputStream.write(bytes,0,read);
            }
            inputStream.close();
            String s = new String(outputStream.toByteArray());
        }
    }

    /**
     * post 表单
     * @throws IOException
     */
    public void postHttpUrl() throws IOException {
        URL url = new URL("http://localhost:8080/zhehsis");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept","*/*");
        con.setRequestProperty("connetion","Keep-Alive");
        //发送的数据类型
        con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        //设置数据的请求方式
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);

        con.connect();
        //传输数据
        String body="userName=zhangsan&password=123456";
        OutputStream outputStream = con.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(body);
        bufferedWriter.flush();
        bufferedWriter.close();

        int responseCode = con.getResponseCode();
        if(responseCode==HttpURLConnection.HTTP_OK){
            InputStream inputStream = con.getInputStream();
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream1.write(bytes,0,len);
            }
            inputStream.close();
            outputStream1.toByteArray();
        }
    }

    /**
     * post json
     * @throws Exception
     */
    public void postJosnUrl() throws Exception {
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept","*/*");
        connection.setRequestProperty("connection","Keep-Alive");
        connection.setRequestProperty("Content-Type","application/json;charset=utf-8");

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.connect();

        String body="{userName:zhangsan,password:123456}";
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(body);

        int responseCode = connection.getResponseCode();
        if(responseCode==HttpURLConnection.HTTP_OK){
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int ln;
            while ((ln=inputStream.read(bytes))!=-1){
                outputStream1.write(bytes,0,ln);
            }
            inputStream.close();
            byte[] bytes1 = outputStream1.toByteArray();
            outputStream1.close();
        }
    }




    private final static String BOUNDARY = "----WebKitFormBoundarygrBcuHVTeNQcBtqn";

    /**
     * 传递照片
     * @param strUrl
     * @param params
     * @param fileParams
     * @param name
     * @return
     * @throws Exception
     */
    public static byte[] doPost(String strUrl, Map<String, Object> params, Map<String, byte[]> fileParams, String name) throws Exception {
        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json, text/plain, */*"); // 设置接收数据的格式
        connection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8;boundary=" + BOUNDARY); // 设置发送数据的格式
        connection.setRequestProperty("version","SHLP-V1");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            out.writeBytes("--" + BOUNDARY + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"");
            out.writeBytes("\r\n\r\n");
            out.writeBytes(value + "\r\n");
        }
        if (fileParams != null && fileParams.size() > 0) {
            Iterator fileIt = fileParams.entrySet().iterator();
            while (fileIt.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIt.next();
                out.writeBytes("--" + BOUNDARY + "\r\n");
                out.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + name + "\"");
                out.writeBytes("\r\n");
                out.writeBytes("Content-Type: image/jpeg");//此处很关键
                out.writeBytes("\r\n\r\n");
                out.write(fileEntry.getValue());
                out.writeBytes("\r\n");
            }
        }
        out.writeBytes("--" + BOUNDARY + "--");
        out.flush();
        out.close();
        InputStream in = null;
        int code = connection.getResponseCode();
        try {
            if (code == 200) {
                in = connection.getInputStream();
            } else {
                in = connection.getErrorStream();
            }
        } catch (SSLException e) {
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = in.read(buff)) != -1) {
            baout.write(buff, 0, len);
        }
        byte[] bytes = baout.toByteArray();
        in.close();
        return bytes;
    }
}
