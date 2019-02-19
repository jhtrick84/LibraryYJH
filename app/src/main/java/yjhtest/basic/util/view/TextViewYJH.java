package yjhtest.basic.util.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Custom TextView
 * @author 윤준혁
 */
public class TextViewYJH extends AppCompatTextView {
    public TextViewYJH(Context context) {
        super(context);
    }

    public TextViewYJH(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextViewYJH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context mContext, AttributeSet attrs){
    }
}
