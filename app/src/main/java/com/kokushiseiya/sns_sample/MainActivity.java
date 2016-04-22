package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Menu mMenu;
    String userIdDefault;

    Firebase mRootRef;

    //Postを格納するリスト
    public ArrayList<Post> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewとの紐付け
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        //ToolbarをActionbarとして設定
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Drotter");
        mToolbar.setTitleTextColor(0xffa4c439);
        mToolbar.setLogo(R.drawable.ic_logo);

        lists = new ArrayList<>();

        //Firebaseのsetup
        Firebase.setAndroidContext(this);

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

                //逆順にする破壊的メソッド
                Collections.reverse(lists);

                ListAdapter listAdapter = new ListAdapter(getApplicationContext(), R.layout.contain_layout, lists);

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(listAdapter);
                ViewCompat.setNestedScrollingEnabled(listView, true);
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
    }

    @Override
    protected void onResume(){
        super.onResume();

        //User情報の取得
        SharedPreferences data = getSharedPreferences("DataSave",Context.MODE_PRIVATE);
        userIdDefault = data.getString("USERID",null);

        if (mMenu != null) {
            setMenuItemInitialize();
        }
    }

    private void setMenuItemInitialize() {

        if (userIdDefault == null) {
            //ログインしてないなら、POSTできないように
            mToolbar.setTitle("Drotter");
            mMenu.getItem(0).setVisible(false);
            mMenu.getItem(1).setVisible(true);
        } else {
            //ログイン済みならPOSTとUPDATEを可能に
            mToolbar.setTitle(userIdDefault);
            mMenu.getItem(0).setVisible(true);
            mMenu.getItem(1).setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mMenu = mToolbar.getMenu();

        //Toolbarのボタンの初期化
        setMenuItemInitialize();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            startActivity(LoginActivity.createIntent(getApplicationContext()));

            return true;
        } else if (id == R.id.action_post){
            startActivity(PostActivity.createIntent(getApplicationContext()));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
