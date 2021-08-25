package com.ya02wmsj_cecoe.linhaimodule.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.ya02wmsj_cecoe.linhaimodule.widget.regionpicker.IRegionPickImp;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-05-31.
 */
public class RegionEntity implements Serializable,IRegionPickImp,IPickerViewData {
    /**
     * pid : 0
     * code : 330122000000
     * name : 桐庐县
     */

    private String pid;
    private String code;
    private String name;
    private boolean is_selected;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getText() {
        return name;
    }

    public String getPickerViewText() {
        return name;
    }

    @Override
    public String getId() {
        return code;
    }

    @Override
    public boolean isSelected() {
        return is_selected;
    }

    @Override
    public void setSelected(boolean selected) {
        is_selected = selected;
    }
}
