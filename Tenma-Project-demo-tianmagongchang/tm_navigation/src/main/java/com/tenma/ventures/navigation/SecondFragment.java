package com.tenma.ventures.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tenma.ventures.base.TMFragment;
import com.tianma.tm_own_find.view.FindThreeFragmentActivity;

public class SecondFragment extends TMFragment {
    private static String title_name = "测试页面";  //二级页面标题
    private static int type = 1;    //类型，非原生0，原生1
//    private static String url = "com.tenma.ventures.usercenter.UserCenterNewFragment";  //非原生填写url地址，原生填写Fragment完整路径
    private static String url = "com.ya02wmsj_cecoe.yl_newtimes.TestFragment";  //非原生填写url地址，原生填写Fragment完整路径

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sencond, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fragment_second_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAway();
            }
        });

    }


    private void clickAway() {
        Intent pushIntent = new Intent(getActivity(), FindThreeFragmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("model_name", title_name);
        bundle.putInt("type", type); //原生
        bundle.putString("url", url);
        pushIntent.putExtras(bundle);
        startActivity(pushIntent);
    }


}
