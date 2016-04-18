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
public class ListAdapter extends ArrayAdapter<Contain> {

    private Context context; // Activityのcontext
    private int layoutId; // ListViewレイアウトのID
    private ArrayList<Contain> lists; // 各項目の内容を格納しているArrayList
    private LayoutInflater inflater;

    /**
     * コンストラクタ
     * @param context Activityのcontext
     * @param layoutId ListViewレイアウトのID
     * @param lists 各項目の内容を格納しているArrayList
     */
    public ListAdapter(Context context, int layoutId, ArrayList<Contain> lists) {
        super(context, layoutId, lists);

        // 各フィールドの初期化・更新
        this.context = context;
        this.layoutId = layoutId;
        this.lists = lists;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 各項目の内容を返すメソッド
     * @param position リストの要素番号
     * @param convertView 更新したいview
     * @param parent 各項目のviewをまとめてる親となるview
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        ImageButton likeButton;

        if (convertView != null) {
            view = convertView;
        } else {
            view = inflater.inflate(layoutId, null);
        }

        final Contain contain = lists.get(position);

        likeButton = (ImageButton) view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int beforeClicked = Integer.parseInt(((TextView) view.findViewById(R.id.likeNum)).getText().toString());

                if (beforeClicked == contain.getLikeNum() + 1) {
                    ((TextView) view.findViewById(R.id.likeNum)).setText(String.valueOf(contain.getLikeNum()));
                } else {
                    ((TextView) view.findViewById(R.id.likeNum)).setText(String.valueOf(contain.getLikeNum() + 1));
                }
            }
        });

        ((TextView) view.findViewById(R.id.userName)).setText(contain.getUserName());
        ((TextView) view.findViewById(R.id.userId)).setText(contain.getUserId());
        ((TextView) view.findViewById(R.id.time)).setText(contain.getTime());
        ((TextView) view.findViewById(R.id.contain)).setText(contain.getText());
        ((TextView) view.findViewById(R.id.replyNum)).setText(String.valueOf(contain.getReplyNum()));
        ((TextView) view.findViewById(R.id.shareNum)).setText(String.valueOf(contain.getShareNum()));
        ((TextView) view.findViewById(R.id.likeNum)).setText(String.valueOf(contain.getLikeNum()));

        return view;
    }
}
