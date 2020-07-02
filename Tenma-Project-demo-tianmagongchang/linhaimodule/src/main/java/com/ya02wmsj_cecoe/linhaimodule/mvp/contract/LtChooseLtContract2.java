package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;

import java.util.ArrayList;
import java.util.List;

public interface LtChooseLtContract2 {
    interface View extends IView {
        void updateArea();

        void updateLt();
    }

    abstract class Presenter extends APresenter<View> {
        private List<LtStreetEntity> mAreaList = new ArrayList<>();
        private List<LtEntitiy> mLtList = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getArea();

        public abstract void getLt(String code);

        public List<LtStreetEntity> getAreaList() {
            return mAreaList;
        }

        public List<LtEntitiy> getLtList() {
            return mLtList;
        }
    }
}
