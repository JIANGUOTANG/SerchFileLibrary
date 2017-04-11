package com.jian.serchfilelibrary;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2017/4/11.
 */

public abstract   class SerchFileManeger {
    private static boolean isInterrupted = false;//判断是否被中断了查询
    public void setInterrupted(boolean interrupted) {
        isInterrupted = interrupted;
    }
    private Context context;
    private static List<FileInfo> fileInfos = new ArrayList<>();
    public SerchFileManeger(String mSuffix, final File file) {
        this.mSuffix = mSuffix;
        new Thread(new Runnable() {
            @Override
            public void run() {
                filePath(file);
                finish();
            }
        }).start();
    }
    public static List<FileInfo> getFileInfos() {
        return fileInfos;
    }
    /**
     * 查询全部文件，查找适合的文件
     *
     * @param file
     */
    private  String mSuffix;
    public  void filePath(File file) {
        if (file != null && file.exists() && file.isDirectory() && file.canRead() && !isInterrupted) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.listFiles() == null) {
                    if (file2.getName().contains(mSuffix)) {
                        String fileName = file2.getName();//获得文件名字
                        String filePath = file2.getAbsolutePath();//获得文件路径
                        FileInfo localFileInfo = new FileInfo(fileName, filePath);
                        fileInfos.add(localFileInfo);
                            upDataList(localFileInfo);//每加载一个就更新一次，避免全部加载才更新
                    }
                } else {
                    filePath(file2);
                }
            }
        } else {

        }
    }
      abstract void upDataList(FileInfo localFileInfo);//更新文件列表
      abstract void finish();//查找完成
}
