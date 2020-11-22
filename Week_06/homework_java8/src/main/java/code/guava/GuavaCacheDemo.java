package code.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.security.Key;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * cache
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class GuavaCacheDemo {

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        String value = "[" + key + "]";
                        return value;
                    }
                });
//        cache.put("name","张麻子");
        String name = cache.get("name");
        System.out.println(name);
        cache.invalidateAll();
        String name1 = cache.get("name");
        System.out.println(name1);
    }
}
