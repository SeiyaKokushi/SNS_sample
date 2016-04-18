package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button mUpdateButton;
    Button mPostButton;
    Button mLoginButton;

    Firebase mRootRef;

    public ArrayList<Post> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewとの紐付け
        mTextView = (TextView) findViewById(R.id.main_userId);
        mUpdateButton = (Button) findViewById(R.id.main_updateButton);
        mPostButton = (Button) findViewById(R.id.main_postButton);
        mLoginButton = (Button) findViewById(R.id.main_loginButton);

        lists = new ArrayList<>();

        setUpButtonListener();

        //Viewの可視性の初期化
        setViewInvisivle();

        //Firebaseのsetup
        Firebase.setAndroidContext(this);

        //ルートパスの指定
        mRootRef = new Firebase(BuildConfig.FIREBASE_URL);

        mRootRef.child("post").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();

                String text = String.valueOf(newPost.get("text"));

                HashMap<String, String> user = (HashMap<String, String>) newPost.get("user");
                String userId = user.get("userId");
                String userName = user.get("userName");

                int likeNum = Integer.parseInt(String.valueOf(newPost.get("likeNum")));

                Log.d("MAIN", text);
                Log.d("MAIN", userId);
                Log.d("MAIN", userName);
                Log.d("MAIN", String.valueOf(likeNum));

                Post post = new Post(new User(userId, userName), text, likeNum);


                lists.add(post);

                ListAdapter listAdapter = new ListAdapter(getApplicationContext(), R.layout.contain_layout, lists);

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        /*

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

        */

    }

    @Override
    protected void onResume(){
        super.onResume();

        //User情報の取得
        SharedPreferences data = getSharedPreferences("DataSave",Context.MODE_PRIVATE);
        String userIdDefault = data.getString("USERID",null);

        //ログイン済みかどうか
        if(userIdDefault != null){
            //ログイン済みならPOSTとUPDATEを可能に
            mTextView.setText(userIdDefault);
            mUpdateButton.setVisibility(View.VISIBLE);
            mPostButton.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        }
    }

    //ログインしていないとき
    private void setViewInvisivle(){
        mUpdateButton.setVisibility(View.GONE);
        mPostButton.setVisibility(View.GONE);
        mLoginButton.setVisibility(View.VISIBLE);
    }

    private void setUpButtonListener() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.createIntent(getApplicationContext()));
            }
        });

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PostActivity.createIntent(getApplicationContext()));
            }
        });
    }


}
