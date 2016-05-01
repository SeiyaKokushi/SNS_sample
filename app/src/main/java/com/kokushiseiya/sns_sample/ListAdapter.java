package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class ListAdapter extends ArrayAdapter<Post> {

    private static class ViewHolder{
        public TextView userName;
        public TextView userId;
        public TextView contain;
        public TextView likeNum;
        public ImageButton likeButton;
    }

    public ListAdapter(Context context, int layoutId, ArrayList<Post> lists) {
        super(context, layoutId, lists);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contain_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.userId = (TextView) convertView.findViewById(R.id.userId);
            viewHolder.contain = (TextView) convertView.findViewById(R.id.contain);
            viewHolder.likeNum = (TextView) convertView.findViewById(R.id.likeNum);
            viewHolder.likeButton = (ImageButton) convertView.findViewById(R.id.likeButton);
            convertView.setTag(viewHolder);
        }

        final Post post = getItem(position);

        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.plusLike();
                int likeNum = post.getLikeNum();

                viewHolder.likeNum.setText(String.valueOf(likeNum));
            }
        });

        viewHolder.userName.setText(post.getUser().getUserName());
        viewHolder.userId.setText("@" + post.getUser().getUserId());
        viewHolder.contain.setText(post.getText());
        viewHolder.likeNum.setText(String.valueOf(post.getLikeNum()));

        return convertView;
    }
}
