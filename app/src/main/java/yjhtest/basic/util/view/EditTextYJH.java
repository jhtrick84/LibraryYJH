package yjhtest.basic.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @author 윤준혁
 */
public class EditTextYJH extends EditText {
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
