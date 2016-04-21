package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {


    Button mButton;
    EditText mEditText;

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

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        final String userIdDefault = data.getString("USERID", null);
        final String userNameDefault = data.getString("USERNAME", null);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
