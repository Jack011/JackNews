package com.jackson.jacknews.moudle;

import com.jackson.jacknews.moudle.bean.HuaBainListBean;

import java.util.List;

/**
 * Created by 90720 on 2016/8/26.
 */
public class HuaBianResponse extends BaseResponse{




    /**
     * ctime : 2016-08-18 13:52
     * title : 穿白裙露香肩！朴信惠美得不食人间烟火
     * description : 腾讯明星
     * picUrl : http://img1.gtimg.com/17/1723/172394/17239445_small.jpg
     * url : http://ent.qq.com/a/20160818/030857.htm
     */

    private List<HuaBainListBean> newslist;



    public List<HuaBainListBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<HuaBainListBean> newslist) {
        this.newslist = newslist;
    }


}
