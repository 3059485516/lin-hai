package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 评分详情列表
 */
public class ScoreInfo implements Serializable {
    private String id;
    private String activity_id;
    private String title;
    private String pic;
    private String content;
    private List<Options> options;

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public class Options implements Serializable{
        public String id;
        public String activity_id;
        public String option_desc;
        public int score_me;
        public float score_ave;
        public int my_score;

        public int getMy_score() {
            return my_score;
        }

        public void setMy_score(int my_score) {
            this.my_score = my_score;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getOption_desc() {
            return option_desc;
        }

        public void setOption_desc(String option_desc) {
            this.option_desc = option_desc;
        }

        public int getScore_me() {
            return score_me;
        }

        public void setScore_me(int score_me) {
            this.score_me = score_me;
        }

        public float getScore_ave() {
            return score_ave;
        }

        public void setScore_ave(float score_ave) {
            this.score_ave = score_ave;
        }
    }
}
