package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-08-07.
 */
public class Node implements Serializable {

    /**
     * id : 29
     * pid : 16
     * title : 时代新人榜
     * icon :
     * node_level : county
     * is_leaf : y
     */

    private String id;
    private String pid;
    private String title;
    private String icon;
    private String node_level;
    private String is_leaf;
    private String extra;
    private boolean local;

    public Node() {
    }

    public Node(String title, String icon, boolean local) {
        this.title = title;
        this.icon = icon;
        this.local = local;
    }

    public Node(String title, String icon, boolean local, String id) {
        this.title = title;
        this.icon = icon;
        this.local = local;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNode_level() {
        return node_level;
    }

    public void setNode_level(String node_level) {
        this.node_level = node_level;
    }

    public String getIs_leaf() {
        return is_leaf;
    }

    public void setIs_leaf(String is_leaf) {
        this.is_leaf = is_leaf;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

}
