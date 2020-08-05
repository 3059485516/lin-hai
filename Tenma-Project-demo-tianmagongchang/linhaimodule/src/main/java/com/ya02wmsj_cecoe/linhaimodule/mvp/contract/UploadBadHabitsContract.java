package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;

import java.util.List;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/31 3:02 PM
 * desc   : EMPTY
 * ================================================
 */
public interface UploadBadHabitsContract {
    interface View extends IView{

        void setUploadProgress(int progress);

        void dissCircleProgressDialog();

        String getVideoTitle();

        String getContent();

        String getRegionCode();

        String getBadHabitsNode();

        void showCircleProgressDialog();

        void showBadHabitNode(List<Node> o);
    }
    abstract class Presenter extends APresenter<View>{
        public String getFilePath() {
            return filePath;
        }

        private String filePath;
        private String nodeId;
        public String getSelectedNode(){
            return nodeId;
        }

        public void setSelectedNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public Presenter(View view, Intent intent) {
            super(view);
            filePath = intent.getStringExtra(Constant.KEY_STRING_1);
        }
        public abstract void addContent();
        public abstract void submitCancel();

        public abstract void getBadHabitList();

    }
}
