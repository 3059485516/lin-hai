package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class ExchangeEntitly implements Serializable {

    /**
     * promotion_status : 1
     * goods_pic : http://47.99.86.101:8030//uploads/ya02wmsj_cecoe/pic/20200417/4f5b480377bf30513d77ed6f8d387f42.png
     * promotion_start_time : 2020-04-17 00:00:00
     * goods_price : 39.90
     * goods_no : 18010d57f001fmkr2gxbez
     * goods_desc : 疫情期间，消毒除菌必备
     * claim_charge : 李阳
     * promotion_end_time : 2020-04-25 00:00:00
     * claim_concat : 15863952367
     * goods_title : 妍物除菌喷雾，入门喷一下
     * goods_org_price : 49.90
     * etime : 2020-04-17 11:18:13
     * goods_num : 100
     * ctime : 2020-04-17 10:19:33
     * id : 2
     * promotion_desc : 限时优惠
     * claim_address : 新时代文明中心一楼
     */
    private int promotion_status;
    private String goods_pic;
    private String promotion_start_time;
    private String goods_price;
    private String goods_no;
    private String goods_desc;
    private String claim_charge;
    private String promotion_end_time;
    private String claim_concat;
    private String goods_title;
    private String goods_org_price;
    private String etime;
    private int goods_num;
    private String ctime;
    private int id;
    private String promotion_desc;
    private String claim_address;

    public void setPromotion_status(int promotion_status) {
        this.promotion_status = promotion_status;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public void setPromotion_start_time(String promotion_start_time) {
        this.promotion_start_time = promotion_start_time;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public void setClaim_charge(String claim_charge) {
        this.claim_charge = claim_charge;
    }

    public void setPromotion_end_time(String promotion_end_time) {
        this.promotion_end_time = promotion_end_time;
    }

    public void setClaim_concat(String claim_concat) {
        this.claim_concat = claim_concat;
    }

    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public void setGoods_org_price(String goods_org_price) {
        this.goods_org_price = goods_org_price;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPromotion_desc(String promotion_desc) {
        this.promotion_desc = promotion_desc;
    }

    public void setClaim_address(String claim_address) {
        this.claim_address = claim_address;
    }

    public int getPromotion_status() {
        return promotion_status;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public String getPromotion_start_time() {
        return promotion_start_time;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public String getGoods_no() {
        return goods_no;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public String getClaim_charge() {
        return claim_charge;
    }

    public String getPromotion_end_time() {
        return promotion_end_time;
    }

    public String getClaim_concat() {
        return claim_concat;
    }

    public String getGoods_title() {
        return goods_title;
    }

    public String getGoods_org_price() {
        return goods_org_price;
    }

    public String getEtime() {
        return etime;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public String getCtime() {
        return ctime;
    }

    public int getId() {
        return id;
    }

    public String getPromotion_desc() {
        return promotion_desc;
    }

    public String getClaim_address() {
        return claim_address;
    }
}
