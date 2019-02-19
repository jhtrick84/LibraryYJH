package yjhtest.basic.vo;

import com.google.gson.annotations.SerializedName;

/**
 * @author 윤준혁
 */

public class BasicConnVO {
//    @SerializedName("basisImageItem.bimg_cate_zone")
//    String zone_cd;
//
//    public BasicConnVO(String zone_cd) {
//        this.zone_cd = zone_cd;
//    }

    @SerializedName("locationItem.memb_seq")
    String memb_seq;
    @SerializedName("locationItem.thisPage")
    String thisPage;
    @SerializedName("locationItem.listRows")
    String listRows;
    @SerializedName("locationItem.onPaging")
    String onPaging;
    public BasicConnVO(){
        memb_seq = "28037";
        thisPage = "1";
        listRows = "10";
        onPaging = "true";
    }
}
