# GifLoader

没有使用第三方jar包

/** 加载网络图片 */
GifDecoder.with(this).load(url).into(imageView);

/** 加载本地资源图片，(资源文件要放到raw文件夹中) */
InputStream in = getResources().openRawResource(R.raw.test);
GifDecoder.with(this).load(in).into(imageView2);