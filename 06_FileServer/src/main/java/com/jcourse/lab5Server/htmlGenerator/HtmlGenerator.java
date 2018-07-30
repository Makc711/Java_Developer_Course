package com.jcourse.lab5Server.htmlGenerator;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

public class HtmlGenerator {

    public static String generateHtmlCode(String rootDirectory, String uri){
        File directory = new File(rootDirectory + uri);
        File[] files = directory.listFiles();
        String htmlFileName = rootDirectory + uri + "/index.html";
        String htmlFileCode;
        if(! new File(htmlFileName).exists()){
            StringBuilder htmlCode = new StringBuilder();
            htmlCode
                    .append("<!DOCTYPE HTML>\n")
                    .append("<head>\n")
                    .append("   <title>").append(directory.getName()).append("</title>\n")
                    .append("</head>\n")
                    .append("<body>\n")
                    .append("<p><small>Folder: ").append(rootDirectory).append(uri).append("</small></p>\n")
                    .append("   <table border=\"2\">\n")
                    .append("       <tr>\n")
                    .append("           <td>Name</td>\n")
                    .append("           <td>Last Modified</td>\n")
                    .append("           <td>Size, bytes</td>\n")
                    .append("       </tr>\n")
                    .append("       <tr>\n")
                    .append("           <td><a href=\"").append("../").append("\">..</a></td>\n")
                    .append("           <td></td>\n")
                    .append("           <td></td>\n")
                    .append("       </tr>\n")
            ;
            SimpleDateFormat sdf =  new SimpleDateFormat("dd.MM.yyyy HH:mm");

            TreeSet<FileSorting> sortedFiles = new TreeSet<>();
            if (files != null) {
                for (File f: files) {
                    sortedFiles.add(new FileSorting(f));
                }
            }

            for(FileSorting f : sortedFiles){
                try {
                    htmlCode.append("       <tr>\n")
                            .append("           <td><a href=\"").append(uri).append("/").append(URLEncoder.encode(f.getFileName(), "UTF-8")).append(f.isFile() ? "" : "/").append("\">").append(f.getFileName()).append("</a></td>\n")
                            .append("           <td>").append(sdf.format(new Date(f.getLastModifiedTime()))).append("</td>\n")
                            .append("           <td>").append(f.isFile() ? f.getFileSize() : "").append("</td>\n")
                            .append("       </tr>\n")
                    ;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            htmlCode.append("   </table>\n")
                    .append("</body>\n")
            ;
            htmlFileCode = htmlCode.toString();

            try {
                createHtmlFile(htmlFileCode, htmlFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            htmlFileCode = getStringFromFile(htmlFileName);
        }
        return htmlFileCode;
    }

    private static String getStringFromFile(String htmlFileName) {
        String htmlFileCode = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(htmlFileName))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            htmlFileCode = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlFileCode;
    }

    private static void createHtmlFile(String htmlCode, String htmlFileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(htmlFileName)));
        out.println(htmlCode);
        out.close();
    }
}
