package com.example.sj151_000.facebookshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by sj151_000 on 2017/3/8.
 */
public class FacebookLoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        callbackManager = CallbackManager.Factory.create();
        LoginButton btn = (LoginButton) findViewById(R.id.login_button);
        btn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken token = loginResult.getAccessToken();
                Log.d("mock", "token = " + token);
                Log.d("mock", "ApplicationId = " + token.getApplicationId());
                Log.d("mock", "getToken = " + token.getToken());
                Log.d("mock", "getUserId = " + token.getUserId());
                Log.d("mock", "getUserId = " + token.getExpires());
            }

            @Override
            public void onCancel() {
                Log.d("mock", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("mock", "onCancel");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}