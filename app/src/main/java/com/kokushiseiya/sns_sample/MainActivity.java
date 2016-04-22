package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.SharedPreferences;
<<<<<<< HEAD
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    Toolbar mToolbar;
    Menu mMenu;
    String userIdDefault;
=======
    TextView mTextView;
    Button mPostButton;
    Button mLoginButton;
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331

    //Postを格納するリスト
    public ArrayList<Post> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewとの紐付け
<<<<<<< HEAD
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        //ToolbarをActionbarとして設定
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Drotter");
        mToolbar.setTitleTextColor(0xffa4c439);
        mToolbar.setLogo(R.drawable.ic_logo);

        lists = new ArrayList<>();

=======
        mTextView = (TextView) findViewById(R.id.main_userId);
        mPostButton = (Button) findViewById(R.id.main_postButton);
        mLoginButton = (Button) findViewById(R.id.main_loginButton);

        lists = new ArrayList<>();

        setUpButtonListener();

        //Viewの可視性の初期化
        setViewInvisivle();

>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331
        User user1 = new User("subway", "さぶうぇい");
        User user2 = new User("ronn", "ロン");
        User user3 = new User("harusame", "はるさめ");
        User user4 = new User("zume", "づめ");
<<<<<<< HEAD
        User user5 = new User("kucchan", "くっちゃん");
=======

>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331

        Post post1 = new Post(user1, "東工大");
        Post post2 = new Post(user2, "東工大M1");
        Post post3 = new Post(user1, "俺はB4");
        Post post4 = new Post(user3, "ど田舎のSFC");
        Post post5 = new Post(user4, "素数大好き");
        Post post6 = new Post(user3, "カロリー潰す");
<<<<<<< HEAD
        Post post7 = new Post(user5, "android芸人登場!!");
=======
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331

        // 各項目の内容を格納するArrayListのインスタンスを生成
        ArrayList<Post> lists = new ArrayList<>();

        lists.add(post1);
        lists.add(post2);
        lists.add(post3);
        lists.add(post4);
        lists.add(post5);
        lists.add(post6);
<<<<<<< HEAD
        lists.add(post7);
=======
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331

        ListAdapter listAdapter = new ListAdapter(this, R.layout.contain_layout, lists);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);

    }

    @Override
<<<<<<< HEAD
    public void onResume() {
=======
    protected void onResume(){
>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331
        super.onResume();

        //User情報の取得
        SharedPreferences data = getSharedPreferences("DataSave",Context.MODE_PRIVATE);
<<<<<<< HEAD
        userIdDefault = data.getString("USERID", null);


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

=======
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


>>>>>>> 5f5b63737a320f71c060e4e19270d29295642331
}
