package com.my.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : fangxiaoming
 * @program : session-share
 * @description : TODO
 * @date : 2019-09-16 21:15
 **/
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
//        FileInputStream fis = null;
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            String h = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            byte[] hBytes = h.getBytes();
            if (file.exists()) {

//                fis = new FileInputStream(file);

                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                String line = null;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line).append("\r\n");
                }
                byte[] bytes1 = builder.toString().getBytes();

                byte[] bytes = new byte[bytes1.length + hBytes.length];
                for (int i = 0; i < hBytes.length; i++) {
                    bytes[i] = hBytes[i];
                }
                for (int i = 0; i < bytes1.length; i++) {
                    bytes[hBytes.length + i] = bytes1[i];
                }

//                int len = fis.read(bytes, hBytes.length, Integer.parseInt(String.valueOf(file.length())));
//                while (len != -1) {
//                    output.write(bytes, 0, len);
//                    len = fis.read(bytes, 0, Integer.parseInt(String.valueOf(file.length())));
//                }
                output.write(bytes);
                System.out.println(new String(bytes));
            } else {
                String errorMessage =
                    "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
