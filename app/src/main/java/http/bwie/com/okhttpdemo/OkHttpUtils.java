package http.bwie.com.okhttpdemo;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by admina on 2016/11/4.
 */
public class OkHttpUtils {
    private static OkHttpClient mClient;
    //设置标题头格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient getOkHttp(){
        if (mClient==null){
            //可以通过实现Logger接口更改日志保存位置
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel((HttpLoggingInterceptor.Level.BODY));
            mClient= new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor).addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    request=request.newBuilder().addHeader("key","value").build();
                    return chain.proceed(request);
                }
            }).build();
        }
        return  mClient;

    }
    //异步post  参数为对象
    public static void psot(String url, Object object, Callback callback){
        post(url,new Gson().toJson(object),callback);

    }
    //异步post  参数为json
    private static void post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        getOkHttp().newCall(request).enqueue(callback);
    }
    //异步post 参数为Map
    public static void post(String url, Map<String,String> map,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();

        for (String key:map.keySet()){
            builder.add(key,map.get(key));
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            getOkHttp().newCall(request).enqueue(callback);

        }
        
        
    }
    //异步get
    public static void get(String url,Callback callback){
        Request request = new Request.Builder().url(url).build();
        getOkHttp().newCall(request).enqueue(callback);
    }
    //同步get
    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = getOkHttp().newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
