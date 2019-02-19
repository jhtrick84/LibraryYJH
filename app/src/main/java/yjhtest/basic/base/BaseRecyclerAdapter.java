package yjhtest.basic.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yjhtest.basic.util.LogYJH;

/**
 * @author 윤준혁
 * @date 2017-02-01
 */
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private List<Object> arrayData = new ArrayList<>();
    protected LayoutInflater mInflater;
    protected Context mContext;

    public static class VH extends RecyclerView.ViewHolder {
        public VH(View v) {
            super(v);
        }
    }

    public BaseRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public View getView(int layout, ViewGroup parent) {
        return mInflater.inflate(layout, parent, false);
    }

    /**
     * View를 모두 삭제한 후 초기화한다. (데이터는 유지)
     */
    public void update() {
        notifyDataSetChanged();
    }

    /**
     * 리스트 아이템 데이터 가져오기
     * @param pos 인덱스 번호
     * @return 리스트 아이템 오브젝트
     */
    public <T> T getData(int pos) {
        try {
            return (T) arrayData.get(pos);
        } catch (Exception e) {
            LogYJH.e(e);
        }
        return null;
    }

    /**
     * 리스트 아이템 데이터 가져오기
     * @return 아답터가 갖고있는 데이터
     */
    public List<Object> getData() {
        try {
            return arrayData;
        } catch (Exception e) {
            LogYJH.e(e);
        }
        return null;
    }

    /**
     * 리스트 아이템을 세팅 (기존 데이터에 덮어쓰기)
     * @param arrayData 리스트 아이템 데이터
     */
    public <T> void setData(List<T> arrayData) {
        this.arrayData.clear();
        addData(arrayData);
    }

    /**
     * 리스트 아이템을 세팅 (기존 데이터에 추가)
     * @param arrayData 리스트 아이템 데이터
     */
    protected <T> void addData(List<T> arrayData) {
        this.arrayData.addAll(arrayData);
        update();
    }

    /**
     * 리스트 아이템을 세팅 (기존 데이터에 추가)
     * @param mData 1개의 데이터
     */
    protected <T> void addData(T mData) {
        this.arrayData.add(mData);
        update();
    }

    public void clear() {
        arrayData.clear();
    }

    public void deleteAll() {
        arrayData.clear();
        update();
    }

    /**
     * 현재 데이터들을 역순으로 재정렬한다.
     */
    public void reverse() {
        if (arrayData != null) {
            Collections.reverse(arrayData);
            update();
        }
    }

    /**
     * 요청에 따라 정렬한다.
     * @param mComparator 정렬 방식 : Comparator
     */
    public void sort(Comparator mComparator) {
        if (arrayData != null) {
            Collections.sort(arrayData, mComparator);
            update();
        }
    }

    @Override
    public int getItemCount() {
        if (arrayData != null)
            return arrayData.size();
        return 0;
    }
}
