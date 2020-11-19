package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-03.
 */
public class NodeContent implements Serializable {

    /**
     * albumDesc : ["1","","3"]
     * contents : 1@@@@@@3
     * likes_list :
     * icon_path :
     * type : 相册
     * top_icon :
     * pic_url :
     * top_status : n
     * node_id : 2
     * id : 4
     * title : 相册2
     * operate_time : 2019-06-04 11:22:39
     * source : pc
     * operate_user_id : 4d92a971360243b0bab91e2ffc6740e3
     * name : 系统管理员
     * path : /uploads/pic/20190604/1559617767_42699977.jpg,/uploads/pic/20190604/1559617771_274689625.jpg,/uploads/pic/20190604/1559617773_944525114.jpg
     * thumb : 0
     * collect : 0
     */

    private String contents;
    private String likes_list;
    private String icon_path;
    private String type;
    private String top_icon;
    private String pic_url;
    private String top_status;
    private String node_id;
    private String id;
    private String title;
    private String subtitle;
    private String operate_time;
    private String release_time;
    private String publish_time;
    private String source;
    private String operate_user_id;
    private String name;
    private String path;
    private int thumb;
    private int thumb_num;
    private int collect;
    private int collect_num;
    private int share;
    private int share_num;
    private int comment_count;
    private List<String> albumDesc;
    private VideoPath video_path;
    private LiveListEntity liveinfo;
    private ContentPreview prev;
    private ContentPreview next;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLikes_list() {
        return likes_list;
    }

    public void setLikes_list(String likes_list) {
        this.likes_list = likes_list;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTop_icon() {
        return top_icon;
    }

    public void setTop_icon(String top_icon) {
        this.top_icon = top_icon;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTop_status() {
        return top_status;
    }

    public void setTop_status(String top_status) {
        this.top_status = top_status;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

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

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOperate_user_id() {
        return operate_user_id;
    }

    public void setOperate_user_id(String operate_user_id) {
        this.operate_user_id = operate_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public int getThumb_num() {
        return thumb_num;
    }

    public void setThumb_num(int thumb_num) {
        this.thumb_num = thumb_num;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public List<String> getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(List<String> albumDesc) {
        this.albumDesc = albumDesc;
    }

    public VideoPath getVideo_path() {
        return video_path;
    }

    public void setVideo_path(VideoPath video_path) {
        this.video_path = video_path;
    }

    public LiveListEntity getLiveinfo() {
        return liveinfo;
    }

    public void setLiveinfo(LiveListEntity liveinfo) {
        this.liveinfo = liveinfo;
    }

    public ContentPreview getPrev() {
        return prev;
    }

    public void setPrev(ContentPreview prev) {
        this.prev = prev;
    }

    public ContentPreview getNext() {
        return next;
    }

    public void setNext(ContentPreview next) {
        this.next = next;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
