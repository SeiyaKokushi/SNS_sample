package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button mPostButton;
    Button mLoginButton;

    Firebase mRootRef;

    //Postを格納するリスト
    public ArrayList<Post> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewとの紐付け
        mTextView = (TextView) findViewById(R.id.main_userId);
        mPostButton = (Button) findViewById(R.id.main_postButton);
        mLoginButton = (Button) findViewById(R.id.main_loginButton);

        setUpButtonListener();

        //Viewの可視性の初期化
        setViewInvisivle();

        //Firebaseのsetup
        Firebase.setAndroidContext(this);

        lists = new ArrayList<>();

        //ルートパスの指定
        mRootRef = new Firebase(BuildConfig.FIREBASE_URL);

        //コールバックの設定
        mRootRef.child("post").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();

                String text = String.valueOf(newPost.get("text"));

                HashMap<String, String> user = (HashMap<String, String>) newPost.get("user");
                String userId = user.get("userId");
                String userName = user.get("userName");

                int likeNum = Integer.parseInt(String.valueOf(newPost.get("likeNum")));

                String timeStampKey = String.valueOf(dataSnapshot.getKey());

                Post post = new Post(new User(userId, userName), text, likeNum, timeStampKey);

                lists.add(post);

                setList(lists);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String timeStampKey = String.valueOf(dataSnapshot.getKey());

                //要素を削除する
                for(int i = 0; i < lists.size(); i++){
                    if(lists.get(i).getTimeStampKey().equals(timeStampKey)){
                        lists.remove(i);
                    }
                }

                setList(lists);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

            private void setList(ArrayList<Post> lists){
                ArrayList<Post> setList = lists;

                //逆順にする破壊的メソッド
                Collections.reverse(setList);

                ListAdapter listAdapter = new ListAdapter(getApplicationContext(), R.layout.contain_layout, setList);

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(listAdapter);
            }
        });
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
            mPostButton.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        }
    }

    //ログインしていないとき
    private void setViewInvisivle(){
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
