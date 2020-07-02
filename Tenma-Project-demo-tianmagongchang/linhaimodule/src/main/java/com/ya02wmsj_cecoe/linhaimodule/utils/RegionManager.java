package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-03.
 */
public class RegionManager {
    private static RegionManager instance;

    private List<RegionEntity> mTownList = new ArrayList<>();
    private List<RegionEntity> mVillageList = new ArrayList<>();
    private RegionEntity mCurrentCounty;
    private RegionEntity mCurrentTown;
    private RegionEntity mCurrentVillage;

    private RegionManager() {

    }

    public static RegionManager getInstance() {
        if (instance == null) {
            instance = new RegionManager();
        }
        return instance;
    }

    public String getCurrentCountyCode() {
        if (mCurrentCounty != null)
            return mCurrentCounty.getCode();
        if (Config.getInstance().getUser() != null && !TextUtils.isEmpty(Config.getInstance().getUser().getCounty()))
            return Config.getInstance().getUser().getCounty();
        return Constant.DEFAULT_REGION_CODE;
    }

    /**
     * 获取当前乡镇区域码
     *
     * @return
     */
    public String getCurrentTownCode() {
        if (mCurrentTown != null)
            return mCurrentTown.getCode();
        if (Config.getInstance().getUser() != null && !TextUtils.isEmpty(Config.getInstance().getUser().getTown()))
            return Config.getInstance().getUser().getTown();
        if (mTownList.size() > 0)
            return mTownList.get(0).getCode();
        return Constant.DEFAULT_REGION_CODE;
    }

    /**
     * 获取当前乡镇名称
     *
     * @return
     */
    public String getCurrentTownName() {
        if (mCurrentTown != null)
            return mCurrentTown.getName();
        if (Config.getInstance().getUser() != null && !TextUtils.isEmpty(Config.getInstance().getUser().getTown_name()))
            return Config.getInstance().getUser().getTown_name();
        if (mTownList.size() > 0)
            return mTownList.get(0).getName();
        return "临海市";
    }

    /**
     * 获取当前村社区域码
     *
     * @return
     */
    public String getCurrentVillageCode() {
        if (mCurrentVillage != null)
            return mCurrentVillage.getCode();
        if (Config.getInstance().getUser() != null && !TextUtils.isEmpty(Config.getInstance().getUser().getVillage()))
            return Config.getInstance().getUser().getVillage();
        if (mVillageList.size() > 0)
            return mVillageList.get(0).getCode();
        return Constant.DEFAULT_REGION_CODE;
    }

    public String getCurrentVillageName() {
        if (mCurrentVillage != null)
            return mCurrentVillage.getName();
        if (Config.getInstance().getUser() != null && !TextUtils.isEmpty(Config.getInstance().getUser().getVillage_name()))
            return Config.getInstance().getUser().getVillage_name();
        if (mVillageList.size() > 0)
            return mVillageList.get(0).getName();
        return "临海市";
    }

    public List<RegionEntity> getTownList() {
        return mTownList;
    }

    public void setTownList(List<RegionEntity> townList) {
        mTownList.clear();
        mTownList.addAll(townList);
    }

    public List<RegionEntity> getVillageList() {
        return mVillageList;
    }

    public void setVillageList(List<RegionEntity> villageList) {
        mVillageList.clear();
        mVillageList.addAll(villageList);
    }

    public RegionEntity getCurrentCounty() {
        return mCurrentCounty;
    }

    public void setCurrentCounty(RegionEntity mCurrentCounty) {
        this.mCurrentCounty = mCurrentCounty;
    }

    public RegionEntity getCurrentTown() {
        return mCurrentTown;
    }

    public void setCurrentTown(RegionEntity mCurrentTown) {
        this.mCurrentTown = mCurrentTown;
    }

    public RegionEntity getCurrentVillage() {
        return mCurrentVillage;
    }

    public void setCurrentVillage(RegionEntity mCurrentVillage) {
        this.mCurrentVillage = mCurrentVillage;
    }
}

