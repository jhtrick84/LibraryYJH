package yjhtest.basic.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import yjhtest.basic.R;
import yjhtest.basic.util.view.EditTextYJH;

/**
 * @author 윤준혁
 */
public class BaseUtilYJH {

    /**
     * Activity Start
     */
    public static void startActivity(Context mContext, Class<?> cls) {
        startActivity(mContext, cls, false);
    }

    public static void startActivity(Context mContext, Class<?> cls, boolean isFinish) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
        if (isFinish)
            ((Activity) mContext).finish();
    }

    public static void startActivity(Context mContext, Intent intent) {
        startActivity(mContext, intent, false);
    }

    public static void startActivity(Context mContext, Intent intent, boolean isFinish) {
        mContext.startActivity(intent);
        if (isFinish)
            ((Activity) mContext).finish();
    }

    /**
     * Toast 출력
     *
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg) {
        if (isStrNotNull(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * String Null, "" 체크
     *
     * @param str
     * @return 비어있는 String인지 아닌지 boolean 리턴
     */
    public static boolean isStrNotNull(@Nullable String str) {
        return (str != null && !"".equals(str.trim()));
    }

    /**
     * Hides the soft keyboard
     */
    public static void hideKeyboard(Context mContext, View mView) {
        if (mView != null && mView.getWindowToken() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showKeyboard(Context mContext, View mView) {
        if (mView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            mView.requestFocus();
            inputMethodManager.showSoftInput(mView, 0);
        }
    }

    /**
     * View가 보여지고 있는지 체크
     *
     * @param view
     * @return
     */
    public static boolean isVisible(View view) {
        if (view.getVisibility() == View.VISIBLE)
            return true;
        return false;
    }

    public interface IDialog{
        void onPositive();
        void onNegative();
    }
    /**
     * 다이얼로그 출력 약식
     * @param mContext
     * @param msg
     * @param mIDialog
     */
    public static void showDialog(Context mContext, String msg, IDialog mIDialog){
        showDialogComplete(mContext, "", msg, false, mContext.getString(R.string.dialog_ok), "", null, mIDialog);
    }

    public static void showDialogComplete(Context mContext, String title, String message, boolean isCancleable,
                                          String positiveMsg, String negativeMsg, final View mView, final IDialog mIDialog){

        ContextThemeWrapper mThemeWrapper = new ContextThemeWrapper(mContext, R.style.DialogTheme);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mThemeWrapper);

        if (BaseUtilYJH.isStrNotNull(title))
            mBuilder.setTitle(title);

        if (BaseUtilYJH.isStrNotNull(message))
            mBuilder.setMessage(message);

        if (mView != null)
            mBuilder.setView(mView);

        mBuilder.setCancelable(isCancleable);

        if (!BaseUtilYJH.isStrNotNull(positiveMsg))
            positiveMsg = mContext.getString(R.string.dialog_ok);

        mBuilder.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (mIDialog != null)
                    mIDialog.onPositive();
            }
        });

        if (BaseUtilYJH.isStrNotNull(negativeMsg)) {
            mBuilder.setNegativeButton(negativeMsg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (mIDialog != null)
                        mIDialog.onNegative();
                }
            });
        }

        final AlertDialog mDialog = mBuilder.create();

        mDialog.show();
    }

    /**
     * EditText에 커서를 맨 뒤로 이동
     * @param mView
     */
    public static void setSelection(EditTextYJH mView){
        mView.setSelection(mView.length());
        mView.setFocusableInTouchMode(true);
        mView.requestFocus();
    }
}