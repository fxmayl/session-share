package com.my.socket;

import java.io.File;
import java.io.FileInputStream;
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
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            String h = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            byte[] hBytes = h.getBytes();
            if (file.exists()) {
                bytes = new byte[Integer.parseInt(String.valueOf(file.length())) + hBytes.length];
                for (int i = 0; i < hBytes.length; i++) {
                    bytes[i] = hBytes[i];
                }
                fis = new FileInputStream(file);
                int len = fis.read(bytes, hBytes.length, Integer.parseInt(String.valueOf(file.length())));
                while (len != -1) {
                    output.write(bytes, 0, len);
                    len = fis.read(bytes, 0, Integer.parseInt(String.valueOf(file.length())));
                }
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
            if (fis != null) {
                fis.close();
            }
        }
    }
}
