package com.runtai.gifloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.runtai.gifloader.load.GifDecoder;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView imageView2;

    public static String urls = "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif";
    public static String url = "http://pic.uuhy.com/uploads/2011/02/11/005.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView2 = (ImageView) findViewById(R.id.iv2);

        try {

            /** 加载网络图片 */
            GifDecoder.with(this).load(url).into(imageView);

            /** 加载本地资源图片，(资源文件要放到raw文件夹中) */
            InputStream in = getResources().openRawResource(R.raw.test);
            GifDecoder.with(this).load(in).into(imageView2);

            /** 这里图片能加载出来，但是动画效果变慢了，不知道是手机的问题还是代码的问题(待研究) */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}