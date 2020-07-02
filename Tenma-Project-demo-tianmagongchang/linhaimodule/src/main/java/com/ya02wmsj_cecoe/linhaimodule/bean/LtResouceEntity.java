package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtResouceEntity implements Serializable {

    /**
     * name : 图书馆
     * source_type : 图书馆
     * id : 5
     * pic : http://47.99.86.101:8040//uploads/ya02lhwhlt_wdhaw/pic/20200318/ada83e01f41b1c45f70ff7f04902a3c7.jpg
     */
    private String name;
    private String source_type;
    private String id;
    private String pic;

    public void setName(String name) {
        this.name = name;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public String getSource_type() {
        return source_type;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }
}
