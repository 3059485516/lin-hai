package com.ya02wmsj_cecoe.linhaimodule.rx;

import android.text.TextUtils;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.Config;
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
import com.ya02wmsj_cecoe.linhaimodule.bean.OnlineCommunity;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;

import static com.ya02wmsj_cecoe.linhaimodule.Constant.BASE_URL_LT_INTERFACE;


public class Api {
    private static final String TAG = "Api";
    private static final ApiService API_SERVICE = RetrofitUtils.getInstance().getProxy(ApiService.class);

    private static String getToken() {
        return Config.getInstance().getToken();
    }

    private static String getVolunteerToken() {
        return Config.getInstance().getVolunteerToken();
    }

    private static String getLtToken() {
        return Config.getInstance().getLtToken();
    }

    /**
     * 创建
     */
    private static Map<String, Object> createParamsMap(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("TOKEN", token);
        return map;
    }

    private static List<MultipartBody.Part> createParamsList(String token) {
        List<MultipartBody.Part> list = new ArrayList<>();
        list.add(MultipartBody.Part.createFormData("TOKEN", token));
        return list;
    }

    /**
     * 上传文件公用方法
     */
    private static MultipartBody.Part createMultipartBody(String key, String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), requestBody);
    }

    public static Observable<List<NodeContent>> getIndexTop(String region_code) {
        return API_SERVICE.getIndexTop(region_code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getContentList(String region_code, String top_status, String is_announce, String node_id, String page, String rows) {
        Map<String, Object> param = createParamsMap(getToken());
        param.put("region_code", region_code);
        param.put("top_status", top_status);
        param.put("is_announce", is_announce);
        param.put("node_id", node_id);
        param.put("page", page);
        param.put("rows", rows);
        return API_SERVICE.getContentList(param).compose(RxSchedulers.io_main());
    }

    /**
     * 获取网络社区内容列表
     *
     * @param region_code
     * @param node_id
     * @param page
     * @param rows
     * @return
     */
    public static Observable<List<OnlineCommunity>> getNetQueueList(String region_code, String node_id, String page, String rows) {
        Map<String, Object> param = createParamsMap(getToken());
        param.put("region_code", region_code);
        param.put("node_id", node_id);
        param.put("page", page);
        param.put("rows", rows);
        return API_SERVICE.getNetQueueList(param).compose(RxSchedulers.io_main());
    }


    /**
     * 获取首页活动
     *
     * @return
     */
    public static Observable<List<AppraiseEntity>> getIndexActivity() {
        Map<String, Object> param = createParamsMap(getToken());
        return API_SERVICE.getIndexActivity(param).compose(RxSchedulers.io_main());
    }


    /**
     * 发起评议
     *
     * @param map
     * @return
     */
    public static Observable<Object> commitAction(Map<String, String> map, List<String> imagePath) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("icon_path".equals(entry.getKey())) {
                parts.add(createMultipartBody("icon_path", entry.getValue()));
            } else {
                parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue()));
            }
        }
        if (imagePath != null && imagePath.size() > 0) {
            int i = 0;
            for (String path : imagePath) {
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.commitAction(parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<RegionEntity>> getRegionData(String type, String pid) {
        Map<String, Object> params = createParamsMap(getToken());
        params.put("type", type);
        params.put("pid", TextUtils.isEmpty(pid) ? "" : pid);
        return API_SERVICE.getRegionData(params).compose(RxSchedulers.io_main());
    }

    public static Observable<List<ActionRecruitEntity>> getOfflineActivityList() {
        return API_SERVICE.getOfflineActivityList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> applyForVolunteer(String name, String address, String intro) {
        Map<String, Object> params = createParamsMap(getToken());
        params.put("name", name);
        params.put("intro", intro);
        params.put("address", address);
        return API_SERVICE.applyForVolunteer(params).compose(RxSchedulers.io_main());
    }

    public static Observable<User> login(String json, String serialnumber) {
        return API_SERVICE.login(json, serialnumber).compose(RxSchedulers.io_main());
    }

    public static Observable<LtUser> loginLt(String json, String serialnumber) {
        return API_SERVICE.loginLt(BASE_URL_LT_INTERFACE + "login", json, serialnumber).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> getSMSCode(String phone) {
        return API_SERVICE.getSMSCode(phone, "2").compose(RxSchedulers.io_main());
    }

    public static Observable<Object> bindMobile(String phone, String code) {
        return API_SERVICE.bindMobile(phone, code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<OrganizeListEntity>> getVolunteerOrganList() {
        return API_SERVICE.getVolunteerOrganList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<OrganizeDetailEntity> getVolunteerOrganDetail(String voran_id) {
        return API_SERVICE.getVolunteerOrganDetail(getToken(), voran_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> applyJoinVolunteerOrgan(String voran_id, String intro) {
        return API_SERVICE.applyJoinVolunteerOrgan(getToken(), voran_id, intro).compose(RxSchedulers.io_main());
    }

    /**
     * 根据内容id获取评论
     *
     * @param c_id
     * @return
     */
    public static Observable<List<CommentEntity>> getCommentById(String c_id, String com_id) {
        return API_SERVICE.getCommentByConId(c_id, com_id).compose(RxSchedulers.io_main());
    }

    /**
     * 新增评论
     *
     * @param c_id
     * @param content
     * @return
     */
    public static Observable<String> addComment(String c_id, String content) {
        return API_SERVICE.operateComment(getToken(), "add", c_id, content).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> operateDiscuss(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return API_SERVICE.operateDiscuss(params).compose(RxSchedulers.io_main());
    }

    /*用户操作相关***********************************************/
    public static Observable<Object> likeContent(String id) {
        return API_SERVICE.recordContent(getToken(), id, "like").compose(RxSchedulers.io_main());
    }

    public static Observable<Object> collectContent(String id) {
        return API_SERVICE.recordContent(getToken(), id, "collect").compose(RxSchedulers.io_main());
    }

    public static Observable<Object> browseContent(String id) {
        return API_SERVICE.recordContent(getToken(), id, "browse").compose(RxSchedulers.io_main());
    }

    public static Observable<Object> shareContent(String id) {
        return API_SERVICE.recordContent(getToken(), id, "share").compose(RxSchedulers.io_main());
    }

    public static Observable<Object> activitySign(String action_id) {
        return API_SERVICE.activitySign(getToken(), action_id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<VenueEntity>> getStadiaList() {
        return API_SERVICE.getStadiaList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<VenueDetailEntity> getStadiaDetail(String id) {
        return API_SERVICE.getStadiaDetail(getToken(), id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LoveRankEntity>> getVolunteerServeList() {
        return API_SERVICE.getVolunteerServeList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> answerQuestion(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return API_SERVICE.answerQuestion(params).compose(RxSchedulers.io_main());
    }

    public static Observable<String> getAnswerScores(String action_id) {
        return API_SERVICE.getAnswerScores(getToken(), action_id).compose(RxSchedulers.io_main());
    }

    /**
     * 获取活动类栏目的内容列表 / 获取我发布的活动列表
     *
     * @param map
     * @return
     */
    public static Observable<List<AppraiseEntity>> getActivityList(Map<String, String> map) {
        Map<String, Object> params = createParamsMap(getToken());
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return API_SERVICE.getActivityList(params).compose(RxSchedulers.io_main());
    }

    /**
     * 公众用户参与  征询/人物评选活动投票
     *
     * @param map
     * @return
     */
    public static Observable<String> consultOrSelectVote(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return API_SERVICE.consultOrSelectVote(params).compose(RxSchedulers.io_main());
    }

    /**
     * 发表看法
     *
     * @param map
     * @param mediaList
     * @return
     */
    public static Observable<Object> addContent(Map<String, Object> map, List<LocalMedia> mediaList) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (mediaList != null && mediaList.size() > 0) {
            int i = 0;
            for (LocalMedia media : mediaList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.addContent(parts).compose(RxSchedulers.io_main());
    }

    /**
     * 爆料
     *
     * @param map
     * @return
     */
    public static Observable<Object> addContentAppeal(Map<String, Object> map) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        parts.add(MultipartBody.Part.createFormData("description", "爆料视频"));
        return API_SERVICE.addContent(parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<VolunteerOrderEntity>> getServiceOrderList() {
        return API_SERVICE.getServiceOrderList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<OrderDetailEntity> getServiceOrderDetail(String service_id) {
        return API_SERVICE.getServiceOrderDetail(getToken(), service_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> addOrder(String service_id) {
        return API_SERVICE.addOrder(getToken(), service_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> bindRegion(String region_code) {
        return API_SERVICE.bindRegion(getToken(), region_code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<AppraiseEntity>> getOnlineActivityList(String page, String rows, String region_code, String theme_id) {
        return API_SERVICE.getOnlineActivityList(getToken(), page, rows, "32", region_code, theme_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> giveScore(String activityId, String scoreIds, String optionIds, String scores) {
        return API_SERVICE.giveScore(getToken(), activityId, scoreIds, optionIds, scores).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> addEvent(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return API_SERVICE.addEvent(params).compose(RxSchedulers.io_main());
    }

    /**
     * [志愿服务-点单]用户提交“其他服务”点单
     * @param regionCode
     * @param name
     * @param phone
     * @param address
     * @param content
     * @return
     */
    public static Observable<Object> addOtherOrder(String regionCode, String name, String phone, String address, String content) {
        Map<String, Object> params = createParamsMap(getToken());
        params.put("region_code", regionCode);
        params.put("name", name);
        params.put("phone", phone);
        params.put("address", address);
        params.put("content", content);
        return API_SERVICE.addOtherOrder(params).compose(RxSchedulers.io_main());
    }


    public static Observable<List<CategoryTypeEntity>> getEventCategory(String region_code) {
        return API_SERVICE.getEventCategory(getToken(), region_code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<AppealHistoryEntity>> getMyEventList() {
        return API_SERVICE.getMyEventList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<EventDetail> getEventDetail(String event_id) {
        return API_SERVICE.getEventDetail(getToken(), event_id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<OrganizeTreeEntity>> getOrganTree() {
        return API_SERVICE.getOrganTree(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<OrganizationDetailEntity> getOrganDetail(String type, String id) {
        return API_SERVICE.getOrganDetail(getToken(), type, id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<OrganizationSubEntity>> searchOrgan(String name) {
        return API_SERVICE.searchOrgan(getToken(), name).compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getDiscuzContentList(String region_code) {
        return API_SERVICE.getDiscuzContentList(getToken(), region_code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<OrderHistoryEntity>> getMyOrderList() {
        return API_SERVICE.getMyOrderList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<List<Node>> getNodeList(String node_id) {
        return API_SERVICE.getNodeList(node_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> addTinyWish(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.addTinyWish(parts).compose(RxSchedulers.io_main());
    }


    public static Observable<Object> finishTinyWish(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.finishTinyWish(parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<WishListEntity>> getWishList(String page, String rows) {
        return API_SERVICE.getWishList(page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<WishDetailEntity> getWishDetail(String id) {
        return API_SERVICE.getWishDetail(id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> claimTinyWish(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return API_SERVICE.claimTinyWish(params).compose(RxSchedulers.io_main());
    }

    /**
     * @param type 0-我发起;1-我认领
     * @param page
     * @param rows
     * @return
     */
    public static Observable<List<WishListEntity>> getMyWishList(int type, String page, String rows) {
        if (type == 0) {
            return API_SERVICE.getMyWishList(getToken(), page, rows).compose(RxSchedulers.io_main());
        } else {
            return API_SERVICE.getMyClaimWishList(getToken(), page, rows).compose(RxSchedulers.io_main());
        }
    }

    public static Observable<List<NodeContent>> getAuditoriumContentList(String page, String rows) {
        return API_SERVICE.getAuditoriumContentList(getToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<String> loginByOpenId() {
        return API_SERVICE.loginByOpenId(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<ZhiyuanhuiListEntity> getRecruitList(String name, String page, String rows) {
        return API_SERVICE.getRecruitList(getToken(), name, page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> signoutRecruit(String recruitId) {
        return API_SERVICE.signoutRecruit(getToken(), getVolunteerToken(), recruitId).compose(RxSchedulers.io_main());
    }

    public static Observable<RankingListEntity> organRank(String page, String rows) {
        return API_SERVICE.organRank(getToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<RankingListEntity> getVolunteerRank(String page, String rows) {
        return API_SERVICE.getVolunteerRank(getToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<List<OrginazeListEntity>> getZyhOrganList(String name, String page, String rows) {
        return API_SERVICE.getZyhOrganList(getToken(), name, page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> joinOrginize(String depId) {
        return API_SERVICE.joinOrginize(getToken(), getVolunteerToken(), depId).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> quitOrginize(String depId) {
        return API_SERVICE.quitOrginize(getToken(), getVolunteerToken(), depId).compose(RxSchedulers.io_main());
    }

    public static Observable<List<EduEntity>> getEduLinkList(String page, String rows) {
        return API_SERVICE.getEduLinkList(getToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<List<TalentEntity>> getTalentList(String page, String rows) {
        return API_SERVICE.getTalentList(getToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> collectNetInfo(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.collectNetInfo(parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<ActivityThemeEntity>> getActivityTheme() {
        return API_SERVICE.getActivityTheme(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<OrginazeDetailEntity> getZyhOrganDetail(String depId) {
        return API_SERVICE.getZyhOrganDetail(getToken(), getVolunteerToken(), depId).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LiveListEntity>> getLiveList() {
        return API_SERVICE.getLiveList(getToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LiveRecordEntity>> getLiveVideolist(String cid) {
        return API_SERVICE.getLiveVideolist(getToken(), cid).compose(RxSchedulers.io_main());
    }

    public static Observable<String> getLiveStatus(String cid) {
        return API_SERVICE.getLiveStatus(getToken(), cid).compose(RxSchedulers.io_main());
    }

    public static Observable<ActionDetailBetEntity> getRecruitDetail(String id) {
        return API_SERVICE.getRecruitDetail(getToken(), getVolunteerToken(), id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> signupRecruit(String id) {
        return API_SERVICE.signupRecruit(getToken(), getVolunteerToken(), id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<ZhiyuanhuiEntity>> signupListRecruit(String page, String rows) {
        return API_SERVICE.signupListRecruit(getToken(), getVolunteerToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<List<ServiceInfoEntity>> getServeInfoList(String id, String page, String rows, String longitude, String latitude) {
        return API_SERVICE.getServeInfoList(getToken(), id, page, rows, longitude, latitude).compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getContentDetail(Map<String, Object> map) {
        return API_SERVICE.getContentDetail(map).compose(RxSchedulers.io_main());
    }

    public static Observable<List<Node>> getServiceOrder(String id, String type) {
        return API_SERVICE.getServiceOrder(getToken(), id, type).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> addNonMaterial(Map<String, Object> map, List<LocalMedia> imageList, List<LocalMedia> thumbList) {
        List<MultipartBody.Part> parts = createParamsList(getToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        if (thumbList != null && thumbList.size() > 0) {
            int i = 0;
            for (LocalMedia media : thumbList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("icon_path", path));
                i++;
            }
        }
        return API_SERVICE.addNonMaterial(parts).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> getOrderActivityDetail(String order_id) {
        return API_SERVICE.getOrderActivityDetail(getToken(), order_id).compose(RxSchedulers.io_main());
    }

    public static Observable<String> getVolunteerScore() {
        return API_SERVICE.getVolunteerScore(getToken(), getVolunteerToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<ExchangeListEntity> getGoodsList(String price, String page, String rows) {
        return API_SERVICE.getGoodsList(getToken(), price, page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<MyExchangeListEntity> getExchangeList(String trade_status, String page, String rows) {
        return API_SERVICE.getExchangeList(getToken(), trade_status, page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> refund(String orderNo, String desc) {
        return API_SERVICE.refund(getToken(), orderNo, desc).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> payment(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getToken());
        params.put("token", getVolunteerToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return API_SERVICE.payment(params).compose(RxSchedulers.io_main());
    }

    //--------------------------文化礼堂------------------------

    public static Observable<List<LtStreetEntity>> getAllCABranchList() {
        return API_SERVICE.getAllCABranchList(BASE_URL_LT_INTERFACE + "getAllCABranchList").compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtEntitiy>> getCAList(String code) {
        return API_SERVICE.getCAList(BASE_URL_LT_INTERFACE + "getCAList", code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtResouceEntity>> getCASourceList(String code) {
        return API_SERVICE.getCASourceList(BASE_URL_LT_INTERFACE + "getCASourceList", code).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> applyCASource(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getLtToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return API_SERVICE.applyCASource(BASE_URL_LT_INTERFACE + "applyCASource", params).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtAppointEntity>> getUserSourceApplyList() {
        return API_SERVICE.getUserSourceApplyList(BASE_URL_LT_INTERFACE + "getUserSourceApplyList", getLtToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtAppointEntity>> getManSourceApplyList(String flag) {
        return API_SERVICE.getManSourceApplyList(BASE_URL_LT_INTERFACE + "getManSourceApplyList", getLtToken(), flag).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> auditSourceApply(String id, String status, String review_msg) {
        return API_SERVICE.auditSourceApply(BASE_URL_LT_INTERFACE + "auditSourceApply", getLtToken(), id, status, review_msg).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtStreetEntity>> getCABranchList() {
        return API_SERVICE.getCABranchList(BASE_URL_LT_INTERFACE + "getCABranchList").compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getLtIndexTop(String region_code) {
        return API_SERVICE.getLtIndexTop(BASE_URL_LT_INTERFACE + "getIndexTop", region_code).compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getLtContentList(String page, String rows, String mark_id) {
        return API_SERVICE.getLtContentList(BASE_URL_LT_INTERFACE + "getContentList", page, rows, mark_id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtMarkEntity>> getContentMarks() {
        return API_SERVICE.getContentMarks(BASE_URL_LT_INTERFACE + "getContentMarks").compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getLtContentDetail(Map<String, Object> map) {
        Map<String, Object> params = createParamsMap(getLtToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return API_SERVICE.getLtContentDetail(BASE_URL_LT_INTERFACE + "getContentDetail", params).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtExpoenentEntity>> getCAEvaList(String code) {
        return API_SERVICE.getCAEvaList(BASE_URL_LT_INTERFACE + "getCAEvaList", code).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> addCAAdvance(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getLtToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.addCAAdvance(BASE_URL_LT_INTERFACE + "addCAAdvance", parts).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> editContent(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getLtToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.editContent(BASE_URL_LT_INTERFACE + "editContent", parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtWatcherEntity>> getCameraList() {
        return API_SERVICE.getCameraList(BASE_URL_LT_INTERFACE + "getCameraList", getLtToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<LtWatcherPlayEntity> getCameraUrl(String code) {
        return API_SERVICE.getCameraUrl(BASE_URL_LT_INTERFACE + "getCameraUrl", code).compose(RxSchedulers.io_main());
    }

    public static Observable<LtDetailEntity> getCAInfo(String ca_id) {
        return API_SERVICE.getCAInfo(BASE_URL_LT_INTERFACE + "getCAInfo", ca_id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtEvaMainEntity>> getMyEvaApplies() {
        return API_SERVICE.getMyEvaApplies(BASE_URL_LT_INTERFACE + "getMyEvaApplies", getLtToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> caEvaApply(Map<String, Object> map, List<LocalMedia> imageList) {
        List<MultipartBody.Part> parts = createParamsList(getLtToken());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parts.add(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue().toString()));
        }
        if (imageList != null && imageList.size() > 0) {
            int i = 0;
            for (LocalMedia media : imageList) {
                String path;
                if (!TextUtils.isEmpty(media.getCompressPath())) {
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                parts.add(createMultipartBody("pics_" + i, path));
                i++;
            }
        }
        return API_SERVICE.caEvaApply(BASE_URL_LT_INTERFACE + "caEvaApply", parts).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtEvaEntity>> getEvaRules() {
        return API_SERVICE.getEvaRules(BASE_URL_LT_INTERFACE + "getEvaRules", getLtToken()).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtMyAdvanceEntity>> getUserCAAdvanceList(String page, String rows) {
        return API_SERVICE.getUserCAAdvanceList(BASE_URL_LT_INTERFACE + "getUserCAAdvanceList", page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<List<LtMyPublishEntity>> getUserConList(String page, String rows) {
        return API_SERVICE.getUserConList(BASE_URL_LT_INTERFACE + "getUserConList", getLtToken(), page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> ltAddComment(String c_id, String pid, String content) {
        return API_SERVICE.ltAddComment(BASE_URL_LT_INTERFACE + "addComment", getLtToken(), c_id, pid, content).compose(RxSchedulers.io_main());
    }

    public static Observable<List<CommentEntity>> getLtCommentByConId(String c_id, String page, String rows) {
        return API_SERVICE.getLtCommentByConId(BASE_URL_LT_INTERFACE + "getCommentByConId", c_id, page, rows).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> conPraise(String c_id) {
        return API_SERVICE.conPraise(BASE_URL_LT_INTERFACE + "conPraise", getLtToken(), c_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> conShare(String c_id) {
        return API_SERVICE.conShare(BASE_URL_LT_INTERFACE + "conShare", getLtToken(), c_id).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> conCollect(String c_id) {
        return API_SERVICE.conCollect(BASE_URL_LT_INTERFACE + "conCollect", getLtToken(), c_id).compose(RxSchedulers.io_main());
    }


    public static Observable<Object> recordUserBrowsing(String c_id) {
        return API_SERVICE.recordUserBrowsing(BASE_URL_LT_INTERFACE + "recordUserBrowsing", getLtToken(), c_id).compose(RxSchedulers.io_main());
    }

    /* ***************** 智慧科协 ******************************* */

    public static Observable<KjcgEntity> getScientificList(String page) {
        return API_SERVICE.getScientificList("http://kp.appwzd.cn/kexieScore/scientific/getScientificList/" + page).compose(RxSchedulers.io_main());
    }

    public static Observable<KjcgEntity> getScienceList(String page) {
        return API_SERVICE.getScienceList("http://kp.appwzd.cn/kexieScore/science/getScienceList/1/" + page).compose(RxSchedulers.io_main());
    }

    public static Observable<String> chaoXingLogin() {
        return API_SERVICE.chaoXingLogin("http://www.appwzd.cn/kexieScore/check/chaoXingLogin").compose(RxSchedulers.io_main());
    }

    public static Observable<KjcgDetailEntity> getScientificDetail(String id) {
        return API_SERVICE.getScientificDetail("http://kp.appwzd.cn/kexieScore/scientific/getScientificDetail/" + id).compose(RxSchedulers.io_main());
    }

    public static Observable<KjxqDetailEntity> getScienceDetail(String id) {
        return API_SERVICE.getScienceDetail("http://kp.appwzd.cn/kexieScore/science/getScienceDetail/" + id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<ZjkProfessionEntity>> queryCateList() {
        return API_SERVICE.queryCateList("http://kp.appwzd.cn/kexieScore/expert/queryCateList").compose(RxSchedulers.io_main());
    }

    public static Observable<List<ZjkzjEntiey>> queryExpertList(String id, String page) {
        return API_SERVICE.queryExpertList("http://kp.appwzd.cn/kexieScore/expert/queryExpertList/" + id + "/" + page).compose(RxSchedulers.io_main());
    }

    public static Observable<ZjkZjDetailEntity> queryExpertDetail(String id) {
        return API_SERVICE.queryExpertDetail("http://kp.appwzd.cn/kexieScore/expert/queryExpertDetail/" + id).compose(RxSchedulers.io_main());
    }

    public static Observable<List<NodeContent>> getVideoList(Map<String, Object> param) {
        return API_SERVICE.getVideoList(param).compose(RxSchedulers.io_main());
    }

    public static Observable<Object> shootBadHabit(@FieldMap Map<String, Object> map) {
        map.put("TOKEN", getToken());
        return API_SERVICE.shootBadHabit(map).compose(RxSchedulers.io_main());
    }

    public static Observable<List<Node>> getBadHabitList() {
        return API_SERVICE.getBadHabitList(getToken()).compose(RxSchedulers.io_main());
    }
}