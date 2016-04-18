package com.kokushiseiya.sns_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 各項目の内容を格納するArrayListのインスタンスを生成
        ArrayList<Contain> lists = new ArrayList<Contain>();

        // 各項目の内容を初期化して、上で生成したlistに格納
        Contain contain1 = new Contain();
        contain1.setUserName("ろん");
        contain1.setUserId("ronnnnn");
        contain1.setTime("2016/04/18");
        contain1.setText("Hello World!");
        lists.add(contain1);

        Contain contain2 = new Contain();
        contain2.setUserName("ろん");
        contain2.setUserId("ronnnnn");
        contain2.setTime("2016/04/18");
        contain2.setText("Hello Droiders!");
        lists.add(contain2);

        Contain contain3 = new Contain();
        contain3.setUserName("ろん");
        contain3.setUserId("ronnnnn");
        contain3.setTime("2016/04/18");
        contain3.setText("test\n" +
                "test\n" +
                "test\n" +
                "test");
        lists.add(contain3);

        ListAdapter listAdapter = new ListAdapter(this, R.layout.contain_layout, lists);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }


}
