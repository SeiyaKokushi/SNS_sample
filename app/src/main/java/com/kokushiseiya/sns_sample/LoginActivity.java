package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText userId;
    Button mButton;

    Firebase mRootRef;

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Viewとの紐付け
        userName = (EditText)findViewById(R.id.login_userName);
        userId = (EditText)findViewById(R.id.login_userId);
        mButton = (Button)findViewById(R.id.login_loginButton);

        //ルートパスの指定
        mRootRef = new Firebase(BuildConfig.FIREBASE_URL);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userId.getText().toString().equals("") && !userName.getText().toString().equals("")) {

                    User user = new User(userId.getText().toString(), userName.getText().toString());

                    mRootRef.child("users/" + user.getUserId()).setValue(user);

                    //端末にログイン情報保存
                    SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = data.edit();
                    editor.putString("USERID", userId.getText().toString());
                    editor.putString("USERNAME", userName.getText().toString());
                    editor.apply();

                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
