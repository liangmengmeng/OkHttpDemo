package http.bwie.com.okhttpdemo.com.bwie.okhttpdemo.bean;

import java.util.ArrayList;

/**
 * Created by admina on 2016/11/4.
 */
public class MyBean {
    public ArrayList<MyTngou> tngou;

    public ArrayList<MyTngou> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<MyTngou> tngou) {
        this.tngou = tngou;
    }


    public MyBean(ArrayList<MyTngou> tngou) {
        this.tngou = tngou;
    }

    public MyBean() {
    }

    public class MyTngou{
        public String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MyTngou(String description) {
            this.description = description;
        }

        public MyTngou() {
        }
    }
}
