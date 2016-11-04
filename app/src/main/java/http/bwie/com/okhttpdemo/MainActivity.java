package http.bwie.com.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import http.bwie.com.okhttpdemo.com.bwie.okhttpdemo.bean.MyBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

//    private TextView tv;
    private List<MyBean.MyTngou> list=new ArrayList<>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
//        tv = (TextView) findViewById(R.id.tv);
        lv= (ListView) findViewById(R.id.lv);
        //初始化数据
        initData();

    }

    private void initData() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        OkHttpUtils.get("http://www.tngou.net/api/cook/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyBean bean = gson.fromJson(res, MyBean.class);
                        list=bean.getTngou();
                        MyAdapter adapter=new MyAdapter(MainActivity.this,list);
                        lv.setAdapter(adapter);
                    }
                });
                }


        });

    }

}
