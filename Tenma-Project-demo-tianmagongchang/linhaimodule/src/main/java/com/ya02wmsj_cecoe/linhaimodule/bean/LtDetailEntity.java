package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtDetailEntity implements Serializable {
    private CaInfo ca_info;
    private List<CaSourceList> ca_source_list;
    private List<NodeContent> con_list;

    public CaInfo getCa_info() {
        return ca_info;
    }

    public void setCa_info(CaInfo ca_info) {
        this.ca_info = ca_info;
    }

    public List<CaSourceList> getCa_source_list() {
        return ca_source_list;
    }

    public void setCa_source_list(List<CaSourceList> ca_source_list) {
        this.ca_source_list = ca_source_list;
    }

    public List<NodeContent> getCon_list() {
        return con_list;
    }

    public void setCon_list(List<NodeContent> con_list) {
        this.con_list = con_list;
    }

    public class CaInfo {
        private String name;
        private String pic;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public class CaSourceList {
        private String id;
        private String name;
        private String desc;
        private String source_type;
        private List<String> pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }
    }
}
