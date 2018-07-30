package com.jcourse.lab5Server.htmlGenerator;

import java.io.File;

class FileSorting implements Comparable<FileSorting> {
    private String fileName;
    private long lastModifiedTime;
    private long fileSize = -1;

    FileSorting(File f) {
        fileName = f.getName();
        lastModifiedTime = f.lastModified();
        if (f.isFile()){
            fileSize = f.length();
        }
    }

    @Override
    public int compareTo(FileSorting f2) {
        int compareResult;
        if (this.isDirectory() && f2.isFile()){
            compareResult = -1;
        }
        else if (f2.isDirectory() && this.isFile()){
            compareResult = 1;
        }
        else {
            compareResult = fileName.toLowerCase().compareTo(f2.getFileName().toLowerCase());
        }
        return compareResult;
    }

    String getFileName() {
        return fileName;
    }

    long getLastModifiedTime() {
        return lastModifiedTime;
    }

    long getFileSize() {
        return fileSize;
    }

    private boolean isDirectory(){
        return fileSize <= -1;
    }

    boolean isFile(){
        return fileSize > -1;
    }
}
