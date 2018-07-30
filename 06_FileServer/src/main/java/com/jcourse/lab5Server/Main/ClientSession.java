package com.jcourse.lab5Server.Main;

import com.jcourse.lab5Server.htmlGenerator.HtmlGenerator;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientSession implements Runnable {
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    ClientSession(Socket socket) throws IOException {
        this.socket = socket;
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    @Override
    public void run() {
        try {
            String header = readHeader();
            System.out.println(header);
            String uri = getURIFromHeader(header);
            System.out.println("Resource: " + uri);
            int code = sendResource(uri);
            System.out.println("Result code: " + code);
            System.out.println("--------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readHeader() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            builder.append(line).append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    private String getURIFromHeader(String header) {
        String[] headerArgs = header.split(" ");
        String uri = headerArgs[1];
        int paramIndex = uri.indexOf("?");
        if (paramIndex != -1) {
            uri = uri.substring(0, paramIndex);
        }
        if (uri.length() == 1){
            uri = "";
        }
        return uri;
    }

    private int sendResource(String uri) throws IOException {
        String rootDir = "D:/_JAVA_PROJECTS/lab5Server/src/main/resources/www";
        File directory = new File(rootDir + uri);
        int code = (directory.exists()) ? 200 : 404;

        String contentType = "";
        if(directory.isDirectory() || uri.endsWith("html")){
            contentType = "text/html";
        }
        String header = getHeader(code, contentType);
        PrintStream answer = new PrintStream(output, true, "UTF-8");
        answer.print(header);
        if (code == 200) {
            if (directory.isDirectory()){
                String htmlCode = HtmlGenerator.generateHtmlCode(rootDir, uri);
                byte[] buffer = htmlCode.getBytes();
                output.write(buffer, 0, buffer.length);
            } else {
                try(FileInputStream fin = new FileInputStream(directory))
                {
                    int count;
                    byte[] buffer = new byte[1024];
                    while ((count = fin.read(buffer)) != -1) {
                        output.write(buffer, 0, count);
                    }
                }
            }
        }
        return code;
    }

    private String getHeader(int code, String contentType) {
        return "HTTP/1.1 " + code + " " + getAnswer(code) + "\r\n" +
                "Date: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + "\r\n" +
                "Accept-Ranges: none\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "\r\n";
    }

    private String getAnswer(int code) {
        switch (code) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            default:
                return "Internal Server Error";
        }
    }
}
