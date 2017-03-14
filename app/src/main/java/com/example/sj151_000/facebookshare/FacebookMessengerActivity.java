package com.example.sj151_000.facebookshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;

/**
 * Created by sj151_000 on 2017/3/8.
 */

public class FacebookMessengerActivity extends AppCompatActivity implements View.OnClickListener {
    //    用户可以使用“发送”按钮，以私密方式向好友和使用 Facebook Messenger 的联系
    private MessageDialog messageDialog;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        messageDialog = new MessageDialog(this);
        // this part is optional
        messageDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.d("mock", "onSuccess = " + result.toString());
            }

            @Override
            public void onCancel() {
                Log.d("mock", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("mock", "onError = " + error.getMessage());
            }
        });

        Button btn_link = (Button) findViewById(R.id.btn_link);
        btn_link.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_link:
//                if (AppUtil.packageInstalled(this, "sdfsdf.sdf.sdf")) {
                    if (MessageDialog.canShow(ShareLinkContent.class)) {
                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle("Hello Facebook")
                                .setContentDescription(
                                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                                .build();

                        messageDialog.show(linkContent);
                    }
//                } else {
//                    Log.d("mock", "没有安装Facebook Messenger.");
//                }

                break;
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 一定要在这个方法中调用callbackManager.onActivityResult方法，否则无法发送
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}