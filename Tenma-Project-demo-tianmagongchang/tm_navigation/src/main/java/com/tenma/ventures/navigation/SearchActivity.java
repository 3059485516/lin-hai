package com.tenma.ventures.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tenma.ventures.base.TMActivity;
import com.tenma.ventures.config.TMConstant;
import com.tenma.ventures.navigation.bean.TopNavigationBean;
import com.tenma.ventures.usercenter.UserCenterFragment;
import com.tenma.ventures.usercenter.base.UCBaseActivity;
import com.tenma.ventures.usercenter.config.TMUCConstant;

import java.io.FileInputStream;

/**
 * 搜索
 * Created by bin on 2018/3/8.
 */

public class SearchActivity extends UCBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        try {
            FileInputStream is = openFileInput(TMConstant.Config.CONFIG_FILE_NAME);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.

            Gson gson = new Gson();
            JsonObject moduleJson = gson.fromJson(text, JsonObject.class);

            JsonArray otherArray = moduleJson.getAsJsonArray("other");
            for (int i = 0; i < otherArray.size(); i++) {
               JsonObject otherJsonObj = otherArray.get(i).getAsJsonObject();
               if ("search".equals(otherJsonObj.get("key").getAsString())){
                   FragmentManager manager = getSupportFragmentManager();
                   FragmentTransaction transaction = manager.beginTransaction();
                   Fragment searchFragment = Fragment.instantiate(this, otherJsonObj.get("androidSrc").getAsString());
                   transaction.add(R.id.search_content_ll, searchFragment);
                   transaction.commit();
                   break;
               }
            }
        }catch (Exception e){

        }
    }
}
