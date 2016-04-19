package com.kokushiseiya.sns_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);


        User user1 = new User("subway", "さぶうぇい");
        User user2 = new User("ronn", "ロン");
        User user3 = new User("harusame", "はるさめ");
        User user4 = new User("zume", "づめ");


        Post post1 = new Post(user1, "東工大");
        Post post2 = new Post(user2, "東工大M1");
        Post post3 = new Post(user1, "俺はB4");
        Post post4 = new Post(user3, "ど田舎のSFC");
        Post post5 = new Post(user4, "素数大好き");
        Post post6 = new Post(user3, "カロリー潰す");

        // 各項目の内容を格納するArrayListのインスタンスを生成
        ArrayList<Post> lists = new ArrayList<>();

        lists.add(post1);
        lists.add(post2);
        lists.add(post3);
        lists.add(post4);
        lists.add(post5);
        lists.add(post6);

        ListAdapter listAdapter = new ListAdapter(this, R.layout.contain_layout, lists);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }


}
