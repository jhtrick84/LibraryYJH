package yjhtest.basic.util.image;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * GlideModeul
 * @author 윤준혁
 */
@com.bumptech.glide.annotation.GlideModule
public class GlideModuleYJH extends AppGlideModule {

//    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // private final int cacheSize = maxMemory / 8;
        int DISK_CACHE_SIZE = 1024 * 1024 * 20;
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "cache", DISK_CACHE_SIZE))
                .setMemoryCache(new LruResourceCache(DISK_CACHE_SIZE));
//                .setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }
}
