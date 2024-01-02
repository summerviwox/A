package com.summer.a.video;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class YourLibraryLoader {

    public static void loadLibrary(Context context) {
        try {
            // 将本地库文件从自定义目录复制到应用私有目录
            copyLibraryFromCustomDirectory(context);

            // 获取应用私有目录下的本地库文件路径
            //String libraryPath = context.getFilesDir() + "/lib/" + libraryName;

            // 使用 ReLinker 加载本地库
            //ReLinker.loadLibrary(context, libraryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyLibraryFromCustomDirectory(Context context) throws IOException {
        File cacheDir = new File(context.getCacheDir(),"arm64-v8a");

        // 创建应用私有目录下的 lib 目录
        File targetDirectory = new File(context.getFilesDir() + "/lib/");
        if(!targetDirectory.exists()){
            targetDirectory.mkdirs();
        }
        for(int x=0;x<cacheDir.listFiles().length;x++){
            File child = cacheDir.listFiles()[x];
            // 创建目标文件
            File targetFile = new File(targetDirectory, cacheDir.listFiles()[x].getName());
            // 复制本地库文件
            copyFile(child, targetFile);
        }
    }

    private static void copyFile(File source, File destination) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}