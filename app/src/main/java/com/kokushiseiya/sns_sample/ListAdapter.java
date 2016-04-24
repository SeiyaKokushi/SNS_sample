package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class ListAdapter extends ArrayAdapter<Post> {

    private Context context; // Activityのcontext
    private int layoutId; // ListViewレイアウトのID
    private ArrayList<Post> lists; // 各項目の内容を格納しているArrayList
    private LayoutInflater inflater;

    public ListAdapter(Context context, int layoutId, ArrayList<Post> lists) {
        super(context, layoutId, lists);

        // 各フィールドの初期化・更新
        this.context = context;
        this.layoutId = layoutId;
        this.lists = lists;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
        ここにgetViewの内容を記述していく
     */
}
