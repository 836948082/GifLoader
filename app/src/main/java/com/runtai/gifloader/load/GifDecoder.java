package com.runtai.gifloader.load;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 加载gif
 */
public class GifDecoder {
    public static Context context;

    public static class Gif {
        public static GifDecoder instance = new GifDecoder();
    }

    public static GifDecoder with(Context c) {
        context = c;
        return Gif.instance;
    }


    /**
     * 从输入流加载图片
     */
    public GifDrawer load(InputStream is) {
        GifDrawer drawer = new GifDrawer();
        drawer.setIs(is);
        return drawer;
    }

    /**
     * 从uri加载图片
     */
    public GifDrawer load(Uri uri) {
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return load(is);
    }

    /**
     * 从sdcard加载图片
     */
    public GifDrawer load(File file) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return load(is);
    }

    /**
     * 从网络加载图片
     */
    public GifDrawer load(String url) {
        String name = Md5Utils.getMD5(url);
        System.out.println(name);
        FileInputStream is = null;
        String path = context.getExternalCacheDir() + File.separator + name;

        File file = new File(path);
        if (file.exists()) {
            try {
                is = new FileInputStream(file);
                return load(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // 文件不存在 先下载下来
            GifDrawer gifDrawer = new GifDrawer();
            GifLoaderTask loadGifTask = new GifLoaderTask(gifDrawer, context);
            loadGifTask.execute(url);
            return gifDrawer;
        }

        return load(is);
    }


}
