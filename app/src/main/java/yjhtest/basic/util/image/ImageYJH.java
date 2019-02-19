package yjhtest.basic.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import yjhtest.basic.util.UtilYJH;

/**
 * Glide를 이용한 이미지 로딩
 * @author 윤준혁
 */
public class ImageYJH {

    /**
     * Glide ImageLoader를 이용하여 ImageView에 로딩된 이미지를 세팅
     * @param url
     * @param view
     */
    public static void load(Context mContext, String url, final ImageView view){
        load(mContext, url, view, 0);
    }

    public static void load(Context mContext, String url, final ImageView view, int resource){
        GlideApp.with(mContext.getApplicationContext()).load(url).transition(GenericTransitionOptions.with(android.R.anim.fade_in)).error(resource).into(view);
    }

    public static void load(Context mContext, Uri mUri, final ImageView view){
        load(mContext, mUri, view, 0);
    }

    public static void load(Context mContext, Uri mUri, final ImageView view, int resource){
        GlideApp.with(mContext.getApplicationContext()).load(mUri).transition(GenericTransitionOptions.with(android.R.anim.fade_in)).error(resource).into(view);
    }

    public static void load(Context mContext, File mFile, final ImageView view){
        if (mFile != null && mFile.exists()){
            GlideApp.with(mContext.getApplicationContext()).load(mFile).transition(GenericTransitionOptions.with(android.R.anim.fade_in)).into(view);
        }
    }

    public static void load(Context mContext, File mFile, final ImageView view, int resource){
        if (mFile != null && mFile.exists()){
            GlideApp.with(mContext.getApplicationContext()).load(mFile).transition(GenericTransitionOptions.with(android.R.anim.fade_in)).error(resource).into(view);
        }else{
            view.setImageResource(resource);
        }
    }

    public static void loadBitmap(Context mContext, String url, final IImageReady mInterface){
        if (UtilYJH.isStrNotNull(url)){
            GlideApp.with(mContext.getApplicationContext())
                    .asBitmap()
                    .load(url)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            mInterface.onResourceReady(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            mInterface.onResourceFail();
                        }
                    });
        }else{
            mInterface.onResourceFail();
        }
    }

    public interface IImageReady {
        void onResourceReady(Bitmap bm);
        void onResourceFail();
    }
}
