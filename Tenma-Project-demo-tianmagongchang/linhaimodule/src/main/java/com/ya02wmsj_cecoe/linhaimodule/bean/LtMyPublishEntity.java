package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtMyPublishEntity implements Serializable {
    private String id;
    private String title;
    private VideoPath video_path;
    private String type;
    private String operate_user_id;
    private String operate_time;
    private String icon_path;
    private String top_icon;
    private String path;
    private String status;
    private String top_status;
    private String marks;
    private String review_msg;
    private String contents;
    private int collect;
    private int thumb;
    private String likes_list;
    private String pic_url;
    private int comment_count;
    private String name;
    private List<String> img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoPath getVideo_path() {
        return video_path;
    }

    public void setVideo_path(VideoPath video_path) {
        this.video_path = video_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperate_user_id() {
        return operate_user_id;
    }

    public void setOperate_user_id(String operate_user_id) {
        this.operate_user_id = operate_user_id;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getTop_icon() {
        return top_icon;
    }

    public void setTop_icon(String top_icon) {
        this.top_icon = top_icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTop_status() {
        return top_status;
    }

    public void setTop_status(String top_status) {
        this.top_status = top_status;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getReview_msg() {
        return review_msg;
    }

    public void setReview_msg(String review_msg) {
        this.review_msg = review_msg;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getLikes_list() {
        return likes_list;
    }

    public void setLikes_list(String likes_list) {
        this.likes_list = likes_list;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
