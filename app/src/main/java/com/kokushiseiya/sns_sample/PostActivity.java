package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {


    Button mButton;
    EditText mEditText;

    Firebase mRootRef;

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, PostActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mButton = (Button)findViewById(R.id.post_postButton);
        mEditText = (EditText)findViewById(R.id.post_post);

        //パスの指定(https://<APP-NAME>.firebaseio.com/)
        mRootRef = new Firebase(BuildConfig.FIREBASE_URL);

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        final String userIdDefault = data.getString("USERID", null);
        final String userNameDefault = data.getString("USERNAME", null);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(userIdDefault, userNameDefault);
                String text = mEditText.getText().toString();

                Post post = new Post(user, text);

                mRootRef.child("post").push().setValue(post);

                finish();
            }
        });
    }
}
