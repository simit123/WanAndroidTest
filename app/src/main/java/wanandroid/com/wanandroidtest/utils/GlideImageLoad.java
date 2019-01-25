package wanandroid.com.wanandroidtest.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/23 ADD
 */

public class GlideImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
