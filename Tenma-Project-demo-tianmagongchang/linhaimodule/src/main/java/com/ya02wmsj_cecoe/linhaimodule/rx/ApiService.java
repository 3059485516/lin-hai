package com.ya02wmsj_cecoe.linhaimodule.rx;

import com.ya02wmsj_cecoe.linhaimodule.bean.ActionDetailBetEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionRecruitEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActivityThemeEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppealHistoryEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.CategoryTypeEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.EduEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.EventDetail;
import com.ya02wmsj_cecoe.linhaimodule.bean.ExchangeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjxqDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveRecordEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LoveRankEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtAppointEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaMainEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtExpoenentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyAdvanceEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyPublishEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtResouceEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtUser;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherPlayEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.MyExchangeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderHistoryEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeTreeEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RankingListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.TalentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.User;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VolunteerOrderEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkZjDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkzjEntiey;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 *
 */
public interface ApiService {

    /**
     * 获取首页轮播图
     *
     * @param region_code
     * @return
     */
    @FormUrlEncoded
    @POST("getIndexTop")
    Observable<List<NodeContent>> getIndexTop(@Field("region_code") String region_code);

    @FormUrlEncoded
    @POST("getContentList")
    Observable<List<NodeContent>> getContentList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getContentList")
    Observable<List<NodeContent>> getTips(@Field("node_id") String id);


    /**
     * 首页活动
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getIndexActivity")
    Observable<List<AppraiseEntity>> getIndexActivity(@FieldMap Map<String, Object> map);

    /**
     * 发起线上评议
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("addActivity")
    Observable<Object> commitAction(@Part() List<MultipartBody.Part> list);


    @FormUrlEncoded
    @POST("getRegionData")
    Observable<List<RegionEntity>> getRegionData(@FieldMap Map<String, Object> map);

    /**
     * 获取活动招募列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getOfflineActivityList")
    Observable<List<ActionRecruitEntity>> getOfflineActivityList(@Field("TOKEN") String token);

    /**
     * 申请成为志愿者
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("applyForVolunteer")
    Observable<Object> applyForVolunteer(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("login")
    Observable<User> login(@Field("data") String json, @Field("serialnumber") String serialnumber);

    /**
     * 获取验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("getSMSCode")
    Observable<Object> getSMSCode(@Field("phone") String phone, @Field("type") String type);

    /**
     * 绑定手机
     *
     * @param phone
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("bindMobile")
    Observable<Object> bindMobile(@Field("phone") String phone, @Field("code") String code);

    /**
     * 获取志愿者组织列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getVolunteerOrganList")
    Observable<List<OrganizeListEntity>> getVolunteerOrganList(@Field("TOKEN") String token);

    /**
     * 获取志愿者组织详情
     *
     * @param token
     * @param vorgan_id
     * @return
     */
    @FormUrlEncoded
    @POST("getVolunteerOrganDetail")
    Observable<OrganizeDetailEntity> getVolunteerOrganDetail(@Field("TOKEN") String token, @Field("vorgan_id") String vorgan_id);

    /**
     * 申请加入该组织
     *
     * @param token
     * @param vorgan_id
     * @param intro
     * @return
     */
    @FormUrlEncoded
    @POST("applyJoinVolunteerOrgan")
    Observable<Object> applyJoinVolunteerOrgan(@Field("TOKEN") String token, @Field("vorgan_id") String vorgan_id, @Field("intro") String intro);

    /**
     * 根据内容id获取评论
     *
     * @param c_id   内容id 必传
     * @param com_id 评论的id （非必传， 用于跟帖）
     * @return
     */
    @FormUrlEncoded
    @POST("getCommentByConId")
    Observable<List<CommentEntity>> getCommentByConId(@Field("c_id") String c_id, @Field("com_id") String com_id);

    /**
     * 新增/删除 评论
     *
     * @param token
     * @param operate_type add/delete
     * @param c_id
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("operateComment")
    Observable<String> operateComment(@Field("TOKEN") String token, @Field("operate_type") String operate_type, @Field("c_id") String c_id, @Field("content") String content);

    /**
     * 跟帖
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("operateComment")
    Observable<Object> operateDiscuss(@FieldMap Map<String, Object> map);

    /**
     * @param token
     * @param id
     * @param operate_type like-点赞 collect-收藏 browse-浏览
     * @return
     */
    @FormUrlEncoded
    @POST("recordContent")
    Observable<Object> recordContent(@Field("TOKEN") String token, @Field("id") String id, @Field("operate_type") String operate_type);

    /**
     * 线下活动报名
     *
     * @param token
     * @param activity_id
     * @return
     */
    @FormUrlEncoded
    @POST("activitySign")
    Observable<Object> activitySign(@Field("TOKEN") String token, @Field("activity_id") String activity_id);


    /**
     * 获取体育场馆列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getStadiaList")
    Observable<List<VenueEntity>> getStadiaList(@Field("TOKEN") String token);

    /**
     * 获取场馆详情
     *
     * @param token
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("getStadiaDetail")
    Observable<VenueDetailEntity> getStadiaDetail(@Field("TOKEN") String token, @Field("id") String id);

    /**
     * 获取爱心排行榜
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getVolunteerServeList")
    Observable<List<LoveRankEntity>> getVolunteerServeList(@Field("TOKEN") String token);

    /**
     * 提交答案
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("answerQuestion")
    Observable<Object> answerQuestion(@FieldMap Map<String, Object> map);

    /**
     * 获取答题结果
     *
     * @param token
     * @param activityId
     * @return
     */
    @FormUrlEncoded
    @POST("getAnswerScores")
    Observable<String> getAnswerScores(@Field("TOKEN") String token, @Field("activityId") String activityId);

    /**
     * 获取活动类栏目的内容列表 / 获取我发布的活动列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getActivityList")
    Observable<List<AppraiseEntity>> getActivityList(@FieldMap Map<String, Object> map);

    /**
     * 公众用户参与  征询/人物评选活动投票
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("consultOrSelectVote")
    Observable<String> consultOrSelectVote(@FieldMap Map<String, Object> map);

    /**
     * 发表爆料
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("addContent")
    Observable<Object> addContent(@Part() List<MultipartBody.Part> list);

    /**
     * 28.获取志愿服务菜单
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getServiceOrderList")
    Observable<List<VolunteerOrderEntity>> getServiceOrderList(@Field("TOKEN") String token);

    /**
     * 29.获取志愿服务详情
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getServiceOrderDetail")
    Observable<OrderDetailEntity> getServiceOrderDetail(@Field("TOKEN") String token, @Field("service_id") String service_id);

    /**
     * 点单
     *
     * @param token
     * @param service_id
     * @return
     */
    @FormUrlEncoded
    @POST("addOrder")
    Observable<Object> addOrder(@Field("TOKEN") String token, @Field("service_id") String service_id);

    /**
     * 绑定区域
     *
     * @param token
     * @param region_code
     * @return
     */
    @FormUrlEncoded
    @POST("bindRegion")
    Observable<Object> bindRegion(@Field("TOKEN") String token, @Field("region_code") String region_code);

    /**
     * 33.获取评议列表【未开始，进行中，已结束】
     *
     * @param token
     * @param page
     * @param rows
     * @param node_id
     * @param region_code
     * @param theme_id
     * @return
     */
    @FormUrlEncoded
    @POST("getOnlineActivityList")
    Observable<List<AppraiseEntity>> getOnlineActivityList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows, @Field("node_id") String node_id, @Field("region_code") String region_code, @Field("theme_id") String theme_id);

    /**
     * 评分
     *
     * @param TOKEN
     * @param activityId
     * @param scoreIds
     * @param optionIds
     * @param scores
     * @return
     */
    @FormUrlEncoded
    @POST("giveScore")
    Observable<Object> giveScore(@Field("TOKEN") String TOKEN, @Field("activityId") String activityId, @Field("scoreIds") String scoreIds, @Field("optionIds") String optionIds, @Field("scores") String scores);

    /**
     * 37.公众用户发布诉求
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("addEvent")
    Observable<Object> addEvent(@FieldMap Map<String, Object> map);

    /**
     * 36.获取诉求分类列表
     *
     * @param token
     * @param region_code
     * @return
     */
    @FormUrlEncoded
    @POST("getEventCategory")
    Observable<List<CategoryTypeEntity>> getEventCategory(@Field("TOKEN") String token, @Field("region_code") String region_code);

    /**
     * 38.查看我发起的诉求列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getMyEventList")
    Observable<List<AppealHistoryEntity>> getMyEventList(@Field("TOKEN") String token);

    /**
     * 39.查看诉求详情
     *
     * @param token
     * @param event_id
     * @return
     */
    @FormUrlEncoded
    @POST("getEventDetail")
    Observable<EventDetail> getEventDetail(@Field("TOKEN") String token, @Field("event_id") String event_id);

    /**
     * 40.树状组织结构列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getOrganTree")
    Observable<List<OrganizeTreeEntity>> getOrganTree(@Field("TOKEN") String token);

    /**
     * 获取组织结构详情【志愿组织、实践机构、文化礼堂】
     *
     * @param token
     * @param type  组织结构类型（列表中的type字段）：organ_volunteer-志愿组织，
     *              organ_practice-实践机构，
     *              cultural_auditorium-文化礼堂
     * @param Id    组织结构id
     * @return
     */
    @FormUrlEncoded
    @POST("getOrganDetail")
    Observable<OrganizationDetailEntity> getOrganDetail(@Field("TOKEN") String token, @Field("type") String type, @Field("id") String Id);

    /**
     * 搜索组织结构【实践组织和文化礼堂】
     *
     * @param token
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("searchOrgan")
    Observable<List<OrganizationSubEntity>> searchOrgan(@Field("TOKEN") String token, @Field("name") String name);

    /**
     * 33.获取议题列表
     *
     * @param token
     * @param region_code
     * @return
     */
    @FormUrlEncoded
    @POST("getDiscuzContentList")
    Observable<List<NodeContent>> getDiscuzContentList(@Field("TOKEN") String token, @Field("region_code") String region_code);

    /**
     * 获取我发起的点单记录
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getMyOrderList")
    Observable<List<OrderHistoryEntity>> getMyOrderList(@Field("TOKEN") String token);

    /**
     * 44.获取图文栏目的子栏目列表
     *
     * @param node_id
     * @return
     */
    @FormUrlEncoded
    @POST("getNodeList")
    Observable<List<Node>> getNodeList(@Field("node_id") String node_id);

    /**
     * 微心愿提交
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("addTinyWish")
    Observable<Object> addTinyWish(@Part() List<MultipartBody.Part> list);

    /**
     * 获取微心愿列表
     *
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getWishList")
    Observable<List<WishListEntity>> getWishList(@Field("page") String page, @Field("rows") String rows);

    /**
     * 获取微心愿详情
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("getWishDetail")
    Observable<WishDetailEntity> getWishDetail(@Field("id") String id);

    /**
     * 48.微心愿-公众用户认领心愿
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("claimTinyWish")
    Observable<Object> claimTinyWish(@FieldMap Map<String, Object> map);

    /**
     * 49.微心愿-获取我发起的心愿列表
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getMyWishList")
    Observable<List<WishListEntity>> getMyWishList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 50.微心愿-获取我认领的心愿列表
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getMyClaimWishList")
    Observable<List<WishListEntity>> getMyClaimWishList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 微心愿办结
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("finishTinyWish")
    Observable<Object> finishTinyWish(@Part() List<MultipartBody.Part> list);

    /**
     * 文化礼堂首页内容列表
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getAuditoriumContentList")
    Observable<List<NodeContent>> getAuditoriumContentList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 刷新志愿汇登录token
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("loginByOpenId")
    Observable<String> loginByOpenId(@Field("TOKEN") String token);

    /**
     * 获取志愿活动列表
     *
     * @param token
     * @param name
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getRecruitList")
    Observable<ZhiyuanhuiListEntity> getRecruitList(@Field("TOKEN") String token, @Field("search_title") String name, @Field("page") String page, @Field("rows") String rows);

    /**
     * 取消活动报名
     *
     * @param TOKEN
     * @param token
     * @param recruitId
     * @return
     */
    @FormUrlEncoded
    @POST("signoutRecruit")
    Observable<Object> signoutRecruit(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("recruitId") String recruitId);

    /**
     * 志愿者组织时长排行
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("organRank")
    Observable<RankingListEntity> organRank(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 志愿者时长排行
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getVolunteerRank")
    Observable<RankingListEntity> getVolunteerRank(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 获取组织列表
     *
     * @param token
     * @param name
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getZyhOrganList")
    Observable<List<OrginazeListEntity>> getZyhOrganList(@Field("TOKEN") String token, @Field("search_title") String name, @Field("page") String page, @Field("rows") String rows);

    /**
     * 加入组织
     *
     * @param TOKEN
     * @param token 志愿汇登录token
     * @param depId
     * @return
     */
    @FormUrlEncoded
    @POST("signup")
    Observable<Object> joinOrginize(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("depId") String depId);

    /**
     * 退出组织
     *
     * @param TOKEN
     * @param token
     * @param depId
     * @return
     */
    @FormUrlEncoded
    @POST("signout")
    Observable<Object> quitOrginize(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("depId") String depId);

    /**
     * 教育查询
     *
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getEduLinkList")
    Observable<List<EduEntity>> getEduLinkList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 达人库
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getTalentList")
    Observable<List<TalentEntity>> getTalentList(@Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 社情收集
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("collectNetInfo")
    Observable<Object> collectNetInfo(@Part() List<MultipartBody.Part> list);

    @FormUrlEncoded
    @POST("getActivityTheme")
    Observable<List<ActivityThemeEntity>> getActivityTheme(@Field("TOKEN") String token);

    /**
     * 获取组织详情
     *
     * @param TOKEN
     * @param token
     * @param depId
     * @return
     */
    @FormUrlEncoded
    @POST("getZyhOrganDetail")
    Observable<OrginazeDetailEntity> getZyhOrganDetail(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("depId") String depId);

    /**
     * 获取直播间列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getLiveList")
    Observable<List<LiveListEntity>> getLiveList(@Field("TOKEN") String token);

    /**
     * 21.[首页-理论云学堂]获取直播间录播列表
     *
     * @param TOKEN
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST("getLiveVideolist")
    Observable<List<LiveRecordEntity>> getLiveVideolist(@Field("TOKEN") String TOKEN, @Field("cid") String cid);

    /**
     * 获取直播间状态
     *
     * @param token
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST("getLiveStatus")
    Observable<String> getLiveStatus(@Field("TOKEN") String token, @Field("cid") String cid);

    /**
     * 获取招募获得详情
     *
     * @param token
     * @param vtoken
     * @param recruitId
     * @return
     */
    @FormUrlEncoded
    @POST("getRecruitDetail")
    Observable<ActionDetailBetEntity> getRecruitDetail(@Field("TOKEN") String token, @Field("token") String vtoken, @Field("recruitId") String recruitId);

    /**
     * 报名招募活动
     *
     * @param TOKEN
     * @param token
     * @param recruitId
     * @return
     */
    @FormUrlEncoded
    @POST("signupRecruit")
    Observable<Object> signupRecruit(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("recruitId") String recruitId);

    /**
     * 获取我报名的活动列表
     *
     * @param TOKEN
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("signupListRecruit")
    Observable<List<ZhiyuanhuiEntity>> signupListRecruit(@Field("TOKEN") String TOKEN, @Field("token") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 获取服务分类下的信息服务列表
     *
     * @param token
     * @param category_id
     * @return
     */
    @FormUrlEncoded
    @POST("getServeInfoList")
    Observable<List<ServiceInfoEntity>> getServeInfoList(@Field("TOKEN") String token, @Field("category_id") String category_id, @Field("page") String page, @Field("rows") String rows, @Field("longitude") String longitude, @Field("latitude") String latitude);

    /**
     * 获取某条图文视频内容详情
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getContentDetail")
    Observable<List<NodeContent>> getContentDetail(@FieldMap Map<String, Object> map);

    /**
     * 获取志愿服务点单列表
     *
     * @param TOKEN
     * @param id    获取第一大类可不传
     * @param type  获取第一大类可不传
     * @return
     */
    @FormUrlEncoded
    @POST("getServiceOrder")
    Observable<List<Node>> getServiceOrder(@Field("TOKEN") String TOKEN, @Field("id") String id, @Field("type") String type);

    /**
     * 发现非遗
     *
     * @param list
     * @return
     */
    @Multipart
    @POST("addNonMaterial")
    Observable<Object> addNonMaterial(@Part() List<MultipartBody.Part> list);

    /**
     * 获取点单详情
     *
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("getOrderActivityDetail")
    Observable<Object> getOrderActivityDetail(@Field("TOKEN") String token, @Field("order_id") String order_id);

    /**
     * 55.[志愿汇]获得志愿者益币数
     *
     * @param TOKEN
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("getVolunteerScore")
    Observable<String> getVolunteerScore(@Field("TOKEN") String TOKEN, @Field("token") String token);

    /**
     * 56.[志愿汇-兑福利]获得商品列表
     *
     * @param TOKEN
     * @param price
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getGoodsList")
    Observable<ExchangeListEntity> getGoodsList(@Field("TOKEN") String TOKEN, @Field("price") String price, @Field("page") String page, @Field("rows") String rows);

    /**
     * 57.[志愿汇-兑福利]兑换记录列表
     *
     * @param TOKEN
     * @param trade_status
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST("getExchangeList")
    Observable<MyExchangeListEntity> getExchangeList(@Field("TOKEN") String TOKEN, @Field("trade_status") String trade_status, @Field("page") String page, @Field("rows") String rows);

    /**
     * 58.[志愿汇-兑福利]申请益币退还
     *
     * @param TOKEN
     * @param orderNo
     * @param desc
     * @return
     */
    @FormUrlEncoded
    @POST("refund")
    Observable<Object> refund(@Field("TOKEN") String TOKEN, @Field("orderNo") String orderNo, @Field("desc") String desc);

    /**
     * 兑福利支付
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("payment")
    Observable<Object> payment(@FieldMap Map<String, Object> map);



    /* ***************************************** 文化礼堂相关接口 ********************************* */

    /**
     * 获取全部街道
     *
     * @param url
     * @return
     */
    @POST
    Observable<List<LtStreetEntity>> getAllCABranchList(@Url String url);

    /**
     * 获取街道下面礼堂列表
     *
     * @param url
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtEntitiy>> getCAList(@Url String url, @Field("code") String code);

    /**
     * 获取礼堂下面资源列表
     *
     * @param url
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtResouceEntity>> getCASourceList(@Url String url, @Field("ca_id") String code);

    /**
     * 申请礼堂资源
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> applyCASource(@Url String url, @FieldMap Map<String, Object> map);


    /**
     * 登录文化礼堂
     *
     * @param url
     * @param json
     * @param serialnumber
     * @return
     */
    @FormUrlEncoded
    @POST()
    Observable<LtUser> loginLt(@Url String url, @Field("data") String json, @Field("serialnumber") String serialnumber);

    /**
     * 获取我的预约
     *
     * @param url
     * @param lttoken
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtAppointEntity>> getUserSourceApplyList(@Url String url, @Field("TOKEN") String lttoken);


    /**
     * 预约管理-获取预约列表
     *
     * @param url
     * @param token
     * @param flag
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtAppointEntity>> getManSourceApplyList(@Url String url, @Field("TOKEN") String token, @Field("flag") String flag);

    /**
     * 礼堂资源预约审核
     *
     * @param url
     * @param token
     * @param id
     * @param status
     * @param review_msg
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> auditSourceApply(@Url String url, @Field("TOKEN") String token, @Field("id") String id, @Field("status") String status, @Field("review_msg") String review_msg);

    /**
     * 获取街道列表（不包含全部模块的）
     *
     * @param url
     * @return
     */
    @POST
    Observable<List<LtStreetEntity>> getCABranchList(@Url String url);

    /**
     * 获取礼堂banner
     *
     * @param url
     * @param region_code
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<NodeContent>> getLtIndexTop(@Url String url, @Field("region_code") String region_code);

    /**
     * 获取礼堂首页内容列表
     *
     * @param url
     * @param page
     * @param rows
     * @param mark_id 筛选过滤标签
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<NodeContent>> getLtContentList(@Url String url, @Field("page") String page, @Field("rows") String rows, @Field("mark_id") String mark_id);

    /**
     * 获取首页标签列表
     *
     * @param url
     * @return
     */
    @POST
    Observable<List<LtMarkEntity>> getContentMarks(@Url String url);

    /**
     * 获取内容详情
     *
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<NodeContent>> getLtContentDetail(@Url String url, @FieldMap Map<String, Object> map);

    /**
     * 获取礼堂指数列表
     *
     * @param url
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtExpoenentEntity>> getCAEvaList(@Url String url, @Field("code") String code);

    /**
     * 新增礼堂建议
     *
     * @param url
     * @param list
     * @return
     */
    @Multipart
    @POST
    Observable<Object> addCAAdvance(@Url String url, @Part() List<MultipartBody.Part> list);

    /**
     * 礼堂内容发布
     *
     * @param url
     * @param list
     * @return
     */
    @Multipart
    @POST
    Observable<Object> editContent(@Url String url, @Part() List<MultipartBody.Part> list);

    /**
     * 获取监控列表
     *
     * @param url
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtWatcherEntity>> getCameraList(@Url String url, @Field("TOKEN") String token);

    /**
     * 获取监控
     *
     * @param url
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<LtWatcherPlayEntity> getCameraUrl(@Url String url, @Field("code") String code);

    /**
     * 获取礼堂详情
     *
     * @param url
     * @param ca_id
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<LtDetailEntity> getCAInfo(@Url String url, @Field("ca_id") String ca_id);

    /**
     * 获取当前礼堂管理员的礼堂活动提交记录
     *
     * @param url
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtEvaMainEntity>> getMyEvaApplies(@Url String url, @Field("TOKEN") String token);

    /**
     * 发布礼堂活动评审
     *
     * @param url
     * @param list
     * @return
     */
    @Multipart
    @POST
    Observable<Object> caEvaApply(@Url String url, @Part() List<MultipartBody.Part> list);

    /**
     * 获取礼堂指数评审标准列表
     *
     * @param url
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtEvaEntity>> getEvaRules(@Url String url, @Field("TOKEN") String token);

    /**
     * 获取当前登录用户的礼堂建议列表
     *
     * @param url
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtMyAdvanceEntity>> getUserCAAdvanceList(@Url String url, @Field("page") String page, @Field("rows") String rows);

    /**
     * 获取用户发布的内容列表
     *
     * @param url
     * @param token
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<LtMyPublishEntity>> getUserConList(@Url String url, @Field("TOKEN") String token, @Field("page") String page, @Field("rows") String rows);

    /**
     * 新增评论
     *
     * @param url
     * @param TOKEN
     * @param c_id
     * @param pid
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> ltAddComment(@Url String url, @Field("TOKEN") String TOKEN, @Field("c_id") String c_id, @Field("pid") String pid, @Field("content") String content);

    /**
     * 获取内容评论列表
     *
     * @param url
     * @param c_id
     * @param page
     * @param rows
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<List<CommentEntity>> getLtCommentByConId(@Url String url, @Field("c_id") String c_id, @Field("page") String page, @Field("rows") String rows);

    /**
     * 点赞
     *
     * @param url
     * @param token
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> conPraise(@Url String url, @Field("TOKEN") String token, @Field("cid") String cid);

    /**
     * 分享
     *
     * @param url
     * @param token
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> conShare(@Url String url, @Field("TOKEN") String token, @Field("cid") String cid);

    /**
     * 收藏
     *
     * @param url
     * @param token
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> conCollect(@Url String url, @Field("TOKEN") String token, @Field("cid") String cid);

    /**
     * 上报浏览内容接口
     *
     * @param url
     * @param token
     * @param cid
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<Object> recordUserBrowsing(@Url String url, @Field("TOKEN") String token, @Field("cid") String cid);


    /************ 智慧科协**************/

    /**
     * 获取科研成果列表
     *
     * @param url
     * @return
     */
    @POST
    Observable<KjcgEntity> getScientificList(@Url String url);

    /**
     * 获取科技需求列表
     *
     * @param url
     * @return
     */
    @POST
    Observable<KjcgEntity> getScienceList(@Url String url);

    /**
     * 登录超星
     *
     * @param url
     * @return
     */
    @GET
    Observable<String> chaoXingLogin(@Url String url);

    /**
     * 获取科研成果详情
     *
     * @param url
     * @return
     */
    @POST
    Observable<KjcgDetailEntity> getScientificDetail(@Url String url);

    /**
     * 获取科技需求详情
     *
     * @param url
     * @return
     */
    @POST
    Observable<KjxqDetailEntity> getScienceDetail(@Url String url);

    /**
     * 专家库获取行业分类
     *
     * @param url
     * @return
     */
    @POST
    Observable<List<ZjkProfessionEntity>> queryCateList(@Url String url);

    /**
     * 根据行业获取专家
     *
     * @param url
     * @return
     */
    @POST
    Observable<List<ZjkzjEntiey>> queryExpertList(@Url String url);

    /**
     * 获取专家详情
     *
     * @param url
     * @return
     */
    @POST
    Observable<ZjkZjDetailEntity> queryExpertDetail(@Url String url);

    @FormUrlEncoded
    @POST("getVideoList")
    Observable<List<NodeContent>> getVideoList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("shootBadHabit")
    Observable<Object> shootBadHabit(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getBadHabitNodeList")
    Observable<List<Node>> getBadHabitList(@Field("TOKEN") String TOKEN);

}


