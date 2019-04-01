package com.example.minsookang.soms;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ntsysMac01 on 2015-12-08.
 */

//IconTextView 자바 클래스는 무엇을 어떤 값에서 보여줄지를 설정해주는 역할

public class IconTextView2 extends LinearLayout {

    private ImageView mIcon2;
    private TextView mText03;
    private TextView mText04;


    public IconTextView2(Context context, IconTextItem2 aItem) {
        super(context);

        //보일 해당 listView에 들어갈 디자인 레이아웃 xml 설정(만들어진 listitem.xml을 보일 디자인 레이아웃으로 설정하였음)
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item2, this, true);

        //각 변수를 설정하고 그 변수가 어떤 값을 의미하는지 또, 무엇을 읽어오고 보여주는지를 설정
        mIcon2 = (ImageView) findViewById(R.id.iconItem2);
        mIcon2.setImageDrawable(aItem.getIcon());

        mText03 = (TextView) findViewById(R.id.dataItem03);
        mText03.setText(aItem.getData(0));

        mText04 = (TextView) findViewById(R.id.dataItem04);
        mText04.setText(aItem.getData(1));


    }

    //위에 방법과 구성은 동일한 방법이지만 조건 문과 파라메타 값으로 자동적으로 셋팅을 해주는것
    //파라메타로 Adapter 자바에서 설정된 int형 index 값과 String형 데이터 값을 가져와서 위처럼 변수를 자동으로 설정해줌
    public void setText(int index, String data){
        if (index == 0 ){
            mText03.setText(data);
        } else if (index == 1){
            mText04.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setIcon(Drawable icon){
        mIcon2.setImageDrawable(icon);
    }
    public IconTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public IconTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IconTextView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
