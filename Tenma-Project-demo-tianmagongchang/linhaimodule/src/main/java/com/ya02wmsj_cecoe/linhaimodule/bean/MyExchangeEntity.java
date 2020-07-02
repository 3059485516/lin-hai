package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class MyExchangeEntity implements Serializable {
    private String id;  //记录id
    private String order_no;    //订单号
    private String transaction_id;  //志愿汇交易id
    private String goods_no;    //商品编码
    private String goods_num;   //购买数量，不是商品库存
    private String total_amount;    //订单总价
    private String goods_desc;  //交易具体描述信息
    private String goods_subject;   //商品的标题/交易标题/订单标题/订单关键字等
    private String trade_status;    //交易状态
    private String claim_time;  //领取时间
    private String refund_time; //退还时间
    private String order_time;  //订单创建时间
    private String claim_code;  //兑换码
    private GoodInfo goods_info;    //商品信息
    private RefundInfo refund_info; //退还信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_subject() {
        return goods_subject;
    }

    public void setGoods_subject(String goods_subject) {
        this.goods_subject = goods_subject;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getClaim_time() {
        return claim_time;
    }

    public void setClaim_time(String claim_time) {
        this.claim_time = claim_time;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getClaim_code() {
        return claim_code;
    }

    public void setClaim_code(String claim_code) {
        this.claim_code = claim_code;
    }

    public GoodInfo getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodInfo goods_info) {
        this.goods_info = goods_info;
    }

    public RefundInfo getRefund_info() {
        return refund_info;
    }

    public void setRefund_info(RefundInfo refund_info) {
        this.refund_info = refund_info;
    }

    public class RefundInfo {
        private String order_no;    //退还订单号
        private String transaction_id;  //退还志愿汇交易id
        private String refund_desc; //退还原因

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getRefund_desc() {
            return refund_desc;
        }

        public void setRefund_desc(String refund_desc) {
            this.refund_desc = refund_desc;
        }
    }

    public class GoodInfo {
        private String goods_no;    //商品编码
        private String goods_title; //商品标题
        private String goods_pic;   //商品封面
        private String claim_address;   //领取地址
        private String claim_charge;    //领取联系人
        private String claim_concat;    //领取联系电话
        private String goods_price;    //购买时商品单价

        public String getGoods_no() {
            return goods_no;
        }

        public void setGoods_no(String goods_no) {
            this.goods_no = goods_no;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public String getClaim_address() {
            return claim_address;
        }

        public void setClaim_address(String claim_address) {
            this.claim_address = claim_address;
        }

        public String getClaim_charge() {
            return claim_charge;
        }

        public void setClaim_charge(String claim_charge) {
            this.claim_charge = claim_charge;
        }

        public String getClaim_concat() {
            return claim_concat;
        }

        public void setClaim_concat(String claim_concat) {
            this.claim_concat = claim_concat;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }
    }
}
