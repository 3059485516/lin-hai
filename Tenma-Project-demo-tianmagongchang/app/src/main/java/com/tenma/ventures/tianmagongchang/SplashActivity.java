package com.tenma.ventures.tianmagongchang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tenma.ventures.base.TMActivity;
import com.tenma.ventures.bean.TMBaseConfig;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMConstant;
import com.tenma.ventures.config.TMServerConfig;
import com.tenma.ventures.navigation.BottomNavigationActivity;
import com.tenma.ventures.tools.encrypt.TMEncryptBean;
import com.tenma.ventures.widget.CountDownProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 启动页
 * Created by bin on 2018/1/3.
 */

public class SplashActivity extends TMActivity {

    private CountDownProgressView mCountDownProgressView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCountDownProgressView = (CountDownProgressView) findViewById(R.id.countdownProgressView);
        TMEncryptBean.encrypt = true;
        TMBaseConfig tmBaseConfig = TMSharedPUtil.getTMBaseConfig(this);
        if (null != tmBaseConfig) {
            TMServerConfig.BASE_URL = tmBaseConfig.getDomain();
            getConfig();
        }
        showCountDownProgressView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showCountDownProgressView() {
        mCountDownProgressView.setProgressListener(new CountDownProgressView.OnProgressListener() {
            @Override
            public void onProgress(int progress) {
                if (progress == 0) {
                    startActivity(new Intent(SplashActivity.this, BottomNavigationActivity.class));
                    finish();
                }
            }
        });
        mCountDownProgressView.setTimeMillis(3000);
        mCountDownProgressView.start();

        mCountDownProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, BottomNavigationActivity.class));
                finish();
            }
        });
    }

    private void getConfig() {
        try {
            InputStream is = getAssets().open("TMContentConfig.json");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String baseConfig = new String(buffer, "utf-8");
            // Finally stick the string into the text view.

            File configFile = new File(getFilesDir(), TMConstant.Config.CONFIG_FILE_NAME);
            if (configFile.exists()) {
                configFile.deleteOnExit();
            }
            FileOutputStream outputStream;

            outputStream = openFileOutput(TMConstant.Config.CONFIG_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(baseConfig.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

