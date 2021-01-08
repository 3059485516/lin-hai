package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.CloudClassActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.DiscoverActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.DoVolunteerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.EditRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.EduActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ExchangeFuliActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtEvaluationActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiTangActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtAddressActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtAdvanceActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtAppointManageActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtContentPublishActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtExponentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtImActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtResourceAppointActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtShowActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtWatcherActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.MyWantSignIn;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.NetPersonBetActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.NodeContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrderBetActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrderBetSubActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrginazeActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OtherServicesActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.RankingListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ServiceListInfo2Activity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ServiceListInfoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ShowCivilizeActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.SocialCollectActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TakeBadHabitsListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TalentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WebActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WishListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ZhiyuanhuiActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ZhkxBetActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ZjkActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.AppUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.MD5EncodeUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2018/10/16.
 */
public class NodeAdapter extends CommonAdapter<Node> {
    public NodeAdapter(Context context, List<Node> nodeParentList) {
        super(context, R.layout.ya02wmsj_cecoe_item_node, nodeParentList);
    }

    @Override
    protected void convert(ViewHolder holder, Node node, int position) {
        ImageManager.getInstance().loadImage(mContext, Constant.getBaseUrl() + node.getIcon(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_node_name, node.getTitle());
        if (node.isLocal()) {
            ImageManager.getInstance().loadImage(mContext, Integer.parseInt(node.getIcon()), holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadImage(mContext, node.getIcon(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        }
        holder.getConvertView().setOnClickListener(v -> {
            switch (node.getTitle()) {
                /*首页节点*/
                case "志愿服务厅":
                case "志愿服务":
                    mContext.startActivity(new Intent(mContext, ZhiyuanhuiActivity.class));
                    break;
                case "文化礼堂":
                    mContext.startActivity(new Intent(mContext, LiTangActivity.class));
                    break;
                case "教育服务":
                    mContext.startActivity(new Intent(mContext, EduActivity.class));
                    break;
                case "智慧科协":
                case "智慧科普":
                    mContext.startActivity(new Intent(mContext, ZhkxBetActivity.class));
                    break;
                case "理论云学堂":
                case "理论云课堂":
                    Intent intent = new Intent(mContext, CloudClassActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, node.getId());
                    mContext.startActivity(intent);
                    break;
                case "网络惠民":
                    mContext.startActivity(new Intent(mContext, NetPersonBetActivity.class));
                    break;
                /*志愿汇节点*/
                case "我要点单":
                case "我要点服务":
                    mContext.startActivity(new Intent(mContext, OrderBetActivity.class));
                    break;
                case "微心愿":
                case "我有微心愿":
                    mContext.startActivity(new Intent(mContext, WishListActivity.class));
                    break;
                case "兑福利":
                case "我要兑福利":
                    if (TextUtils.isEmpty(Config.getInstance().getVolunteerToken())) {
                        T.showShort(mContext, "请先登录志愿汇再操作");
                        break;
                    }
                    mContext.startActivity(new Intent(mContext, ExchangeFuliActivity.class));
                    break;
                case "文明实践榜":
                case "我要看排名":
                    mContext.startActivity(new Intent(mContext, RankingListActivity.class));
                    break;
                case "我要做志愿":
                    mContext.startActivity(new Intent(mContext, DoVolunteerActivity.class));
                    break;
                case "我要加组织":
                case "我要加团队":
                    if (TextUtils.isEmpty(Config.getInstance().getVolunteerToken())) {
                        T.showShort(mContext, "请先登录志愿汇再操作");
                        break;
                    }
                    mContext.startActivity(new Intent(mContext, OrginazeActivity.class));
                    break;
                case "秀文明":
                case "我要秀文明":
                    mContext.startActivity(new Intent(mContext, ShowCivilizeActivity.class));
                    break;
                case "签到":
                case "我要签到":
                    /*IWXAPI api = WXAPIFactory.createWXAPI(mContext, "wx3a3159c772abde27");
                    api.registerApp("wx3a3159c772abde27");
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_9b78c83143ba"; // 填小程序原始id
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
                    api.sendReq(req);*/
                    Intent intent6 = new Intent(mContext, MyWantSignIn.class);
                    mContext.startActivity(intent6);
                    break;
                case "学习强国":
                    AppUtils.gotoOtherApp(mContext, "cn.xuexi.android", "");
                    break;
                case "视频":
                    Intent intent2 = new Intent(mContext, NodeContentActivity.class);
                    intent2.putExtra(Constant.KEY_STRING_1, "视频");
                    intent2.putExtra(Constant.KEY_STRING_2, "41");
                    mContext.startActivity(intent2);
                    break;
                case "图文":
                    Intent intent3 = new Intent(mContext, NodeContentActivity.class);
                    intent3.putExtra(Constant.KEY_STRING_1, "图文");
                    intent3.putExtra(Constant.KEY_STRING_2, "42");
                    mContext.startActivity(intent3);
                    break;
                case "直播":
                    mContext.startActivity(new Intent(mContext, LiveListActivity.class));
                    break;
                case "达人库":
                    mContext.startActivity(new Intent(mContext, TalentActivity.class));
                    break;
                /*网络惠民*/
                case "社情收集":
                    mContext.startActivity(new Intent(mContext, SocialCollectActivity.class));
                    break;
                /*网络社区-更多服务-点单*/
                case "理论宣讲服务":
                case "健身体育服务":
                case "文化服务":
                case "科技与科普服务":
                case "教育志愿服务":
                case "网络惠民服务":
                case "特色公益服务":
                case "政策宣讲服务":
                case "法治宣讲服务":
                case "百行知识宣讲服务":
                    Intent intentDD = new Intent(mContext, OrderBetSubActivity.class);
                    intentDD.putExtra(Constant.KEY_BEAN, node);
                    mContext.startActivity(intentDD);
                    break;
                case "修改区域":
                    Intent editRegionIntent = new Intent(mContext, EditRegionActivity.class);
                    mContext.startActivity(editRegionIntent);
                    break;
                case "家政":
                    Intent intentJZ = new Intent(mContext, ServiceListInfoActivity.class);
                    intentJZ.putExtra(Constant.KEY_STRING_1, "家政");
                    intentJZ.putExtra(Constant.KEY_STRING_2, "18");
                    mContext.startActivity(intentJZ);
                    break;
                case "开锁":
                    Intent intentKS = new Intent(mContext, ServiceListInfoActivity.class);
                    intentKS.putExtra(Constant.KEY_STRING_1, "开锁");
                    intentKS.putExtra(Constant.KEY_STRING_2, "19");
                    mContext.startActivity(intentKS);
                    break;
                case "管道疏通":
                    Intent intentGDST = new Intent(mContext, ServiceListInfoActivity.class);
                    intentGDST.putExtra(Constant.KEY_STRING_1, "管道疏通");
                    intentGDST.putExtra(Constant.KEY_STRING_2, "20");
                    mContext.startActivity(intentGDST);
                    break;
                case "生活缴费":
                    AppUtils.gotoOtherApp(mContext, "com.eg.android.AlipayGphone", "alipays://platformapi/startapp?appId=20000193");
                    break;
                case "找厕所":
                    if (!AppUtils.appInstalled(mContext, "com.autonavi.minimap")) {
                        T.showShort(mContext, "请先安装高德地图再使用该功能");
                        break;
                    }
                    String uriString = null;
                    StringBuilder builder = new StringBuilder("amapuri://arroundpoi?sourceApplication=掌心临海");
                    builder.append("&keywords=").append("卫生间");
                    uriString = builder.toString();
                    Intent intentZCS = new Intent(Intent.ACTION_VIEW);
                    intentZCS.setPackage("com.autonavi.minimap");
                    intentZCS.setData(Uri.parse(uriString));
                    mContext.startActivity(intentZCS);

                    break;
                case "我要咨询":
                    Intent intentZX = new Intent(mContext, WebActivity.class);
                    intentZX.putExtra(Constant.KEY_STRING_1, "我要咨询");
                    intentZX.putExtra(Constant.KEY_STRING_2, "http://www.zjzwfw.gov.cn/jfaqfront/xiaomi/index.do");
                    mContext.startActivity(intentZX);
                    break;
                case "律师咨询":
                    Intent intentLvShi = new Intent(mContext, ServiceListInfo2Activity.class);
                    intentLvShi.putExtra(Constant.KEY_STRING_1, "律师咨询");
                    intentLvShi.putExtra(Constant.KEY_STRING_2, "15");
                    mContext.startActivity(intentLvShi);
                    break;
                case "农残检测":
                    Intent intentJiance = new Intent(mContext, ServiceListInfo2Activity.class);
                    intentJiance.putExtra(Constant.KEY_STRING_1, "农残检测");
                    intentJiance.putExtra(Constant.KEY_STRING_2, "16");
                    mContext.startActivity(intentJiance);
                    break;
                case "发现非遗":
                    mContext.startActivity(new Intent(mContext, DiscoverActivity.class));
                    break;
                case "电子图书馆":
                    Intent intentEbook = new Intent(mContext, WebActivity.class);
                    intentEbook.putExtra(Constant.KEY_STRING_1, "电子图书馆");
                    intentEbook.putExtra(Constant.KEY_STRING_2, "https://wk3.bookan.com.cn/?id=115&token=&productId=5!#/");
                    mContext.startActivity(intentEbook);
                    break;
                case "智慧养老":
                    Intent intentYL = new Intent(mContext, WebActivity.class);
                    intentYL.putExtra(Constant.KEY_STRING_1, "智慧养老");
                    intentYL.putExtra(Constant.KEY_STRING_2, "https://gzb.lewanyun.com/_tzgzd/#/pages/index/index");
                    mContext.startActivity(intentYL);
                    break;
                case "免费发药点":
                    Intent intentFYD = new Intent(mContext, ServiceListInfo2Activity.class);
                    intentFYD.putExtra(Constant.KEY_STRING_1, "免费发药点");
                    intentFYD.putExtra(Constant.KEY_STRING_2, "21");
                    mContext.startActivity(intentFYD);
                    break;
                case "体育馆地图":
                    Intent intentTYG = new Intent(mContext, ServiceListInfo2Activity.class);
                    intentTYG.putExtra(Constant.KEY_STRING_1, "体育馆地图");
                    intentTYG.putExtra(Constant.KEY_STRING_2, "22");
                    mContext.startActivity(intentTYG);
                    break;

                /*文化礼堂*/
                case "资源预约":
                    mContext.startActivity(new Intent(mContext, LtResourceAppointActivity.class));
                    break;
                case "礼堂秀场":
                    mContext.startActivity(new Intent(mContext, LtShowActivity.class));
                    break;
                case "礼堂指数":
                    mContext.startActivity(new Intent(mContext, LtExponentActivity.class));
                    break;
                case "礼堂地图":
                    mContext.startActivity(new Intent(mContext, LtAddressActivity.class));
                    break;
                case "礼堂建议":
                    mContext.startActivity(new Intent(mContext, LtAdvanceActivity.class));
                    break;
                case "随手拍":
                    mContext.startActivity(new Intent(mContext, LtContentPublishActivity.class));
                    break;
                case "测评记录":
                    if (!Config.getInstance().getUser().isLtManager()) {
                        T.showShort(mContext, "当前用户不是管理员，无法操作");
                        break;
                    }
                    mContext.startActivity(new Intent(mContext, LtEvaluationActivity.class));
                    break;
                case "消息":
                    if (!Config.getInstance().getUser().isLtManager()) {
                        T.showShort(mContext, "当前用户不是管理员，无法操作");
                        break;
                    }
                    if (!Config.getInstance().isLoginIm()) {
                        T.showShort(mContext, "正在登陆IM，请稍后重试");
                        Config.getInstance().loginNim(Config.getInstance().getUser().getUuid(), Config.getInstance().getUser().getToken());
                        break;
                    }
                    mContext.startActivity(new Intent(mContext, LtImActivity.class));
                    break;
                case "视频监控":
                    mContext.startActivity(new Intent(mContext, LtWatcherActivity.class));
                    break;
                case "预约管理":
                    if (!Config.getInstance().getUser().isLtManager()) {
                        T.showShort(mContext, "当前用户不是管理员，无法操作");
                        break;
                    }
                    mContext.startActivity(new Intent(mContext, LtAppointManageActivity.class));
                    break;

                /*智慧科协*/
                case "期刊":
                    Intent intentQK = new Intent(mContext, WebActivity.class);
                    intentQK.putExtra(Constant.KEY_STRING_1, "期刊");
                    intentQK.putExtra(Constant.KEY_STRING_2, "http://qk.chaoxing.com/mobile/index");
                    mContext.startActivity(intentQK);
                    break;
                case "书城":
                    if (TextUtils.isEmpty(node.getExtra())) {
                        T.showShort(mContext, "未获取到书城相关信息");
                        break;
                    }
                    Intent intentSC = new Intent(mContext, WebActivity.class);
                    intentSC.putExtra(Constant.KEY_STRING_1, "书城");
                    intentSC.putExtra(Constant.KEY_STRING_2, node.getExtra());
                    mContext.startActivity(intentSC);
                    break;
                case "公开课":
                    String phone = Config.getInstance().getUser().getPhone();
                    String time = System.currentTimeMillis() + "";
                    String enc = "40871" + phone + "webApp@#$%" + time;
                    Intent intentGKK = new Intent(mContext, WebActivity.class);
                    intentGKK.putExtra(Constant.KEY_STRING_1, "公开课");
                    intentGKK.putExtra(Constant.KEY_STRING_2, "http://mc.m.5read.com/other/webapp4OpenClass_webApp4OpenClass_recommend.jspx?schoolid=40871" + "&uid=" + phone + "&_time=" + time + "&enc=" + MD5EncodeUtil.md5(enc));
                    mContext.startActivity(intentGKK);
                    break;
                case "专家库":
                    mContext.startActivity(new Intent(mContext, ZjkActivity.class));
                    break;
                case "我要拍陋习":
                    mContext.startActivity(new Intent(mContext, TakeBadHabitsListActivity.class));
                    break;
                case "其他服务":
                    //我要点服务 -- 其他服务
                    mContext.startActivity(new Intent(mContext, OtherServicesActivity.class));
                    break;
            }
        });
    }
}
