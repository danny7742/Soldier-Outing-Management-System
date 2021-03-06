package com.example.minsookang.soms;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**

 * Created by Mac02 on 2015-12-08.

 */

public class IconTextListAdapter2 extends BaseAdapter {
    private Context mContext2;
    private List<IconTextItem2> mItems2 = new ArrayList<IconTextItem2>();

    public IconTextListAdapter2(Context mContext) {
        this.mContext2 = mContext;
    }


    public void addItem(IconTextItem2 it){
        mItems2.add(it);
    }

    public void remove(int position){
        mItems2.remove(position);
    }

    public void setmItems(List<IconTextItem2> mItems) {
        this.mItems2 = mItems;
    }

    @Override
    public int getCount() {
        return mItems2.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems2.get(position);
    }

    public boolean areAllltemsSelectable(){
        return false;
    }

    public boolean isSelectable(int position){
        try {
            return mItems2.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex){
            return false;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 화면 구성하는 getView
    // convertView가 null이 아니면 뷰는 재활용하고 안의 데이터만 바꿔주어 퍼포먼스 향상
    // null인 경우에는 새로 객체 생성해줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IconTextView2 itemView;
        if (convertView == null){
            itemView = new IconTextView2(mContext2, mItems2.get(position));
        } else {
            itemView = (IconTextView2)convertView;
            itemView.setIcon(mItems2.get(position).getIcon());
            itemView.setText(0, mItems2.get(position).getData(0));
            itemView.setText(1, mItems2.get(position).getData(1));
        }
        return itemView;

    }

}
