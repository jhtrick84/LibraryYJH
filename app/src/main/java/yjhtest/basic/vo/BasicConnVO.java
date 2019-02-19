package yjhtest.basic.vo;

import com.google.gson.annotations.SerializedName;

/**
 * @author 윤준혁
 */

public class BasicConnVO {
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
