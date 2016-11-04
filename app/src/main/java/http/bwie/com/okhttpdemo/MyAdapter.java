package http.bwie.com.okhttpdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import http.bwie.com.okhttpdemo.com.bwie.okhttpdemo.bean.MyBean;

/**
 * Created by admina on 2016/11/4.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<MyBean.MyTngou> list;
    public MyAdapter(Context context, List<MyBean.MyTngou> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.item,null);
            holder=new ViewHolder();
            holder.description= (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.description.setText(list.get(position).getDescription());
        return convertView;
    }

    class ViewHolder{
        TextView description;
    }
}
