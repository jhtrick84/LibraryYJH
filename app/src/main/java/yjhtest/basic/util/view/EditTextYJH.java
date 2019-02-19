package yjhtest.basic.util.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Custom EditText
 * @author 윤준혁
 */
public class EditTextYJH extends AppCompatEditText {
    public EditTextYJH(Context context) {
        super(context);
    }

    public EditTextYJH(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditTextYJH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context mContext, AttributeSet attrs){
    }
}
