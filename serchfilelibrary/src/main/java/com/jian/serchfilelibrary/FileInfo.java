package com.jian.serchfilelibrary;

/**
 * Created by jian on 2017/3/7.
 * 题目文件信息，当被打开过的就保存到数据库，下次打开的时候直接从数据库中读取
 */
//

public class FileInfo {


    private String  fileName;
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileInfo(String fileName, String filePath) {

        this.fileName = fileName;
        this.filePath = filePath;
    }
}
