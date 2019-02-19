package yjhtest.basic.main;

import android.os.Bundle;

import yjhtest.basic.BaseActivity;
import yjhtest.basic.R;

/**
 * 메인 액티비티 - 기능에 대한 리스트 출력
 * @author 윤준혁
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initViews();
        initData();
    }

    public void initViews() {

    }

    public void initData() {

    }
}
