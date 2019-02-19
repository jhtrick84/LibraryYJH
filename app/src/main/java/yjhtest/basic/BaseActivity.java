package yjhtest.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yjhtest.basic.util.LogYJH;

/**
 * 모든 액티비티가 상속받는 기본 액티비티
 * @author 윤준혁
 */
public class BaseActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogYJH.e("Start Activity : " + getLocalClassName());

        mContext = this;
    }
}
