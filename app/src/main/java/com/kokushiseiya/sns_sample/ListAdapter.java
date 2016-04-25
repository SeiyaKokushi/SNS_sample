package com.kokushiseiya.sns_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class ListAdapter extends ArrayAdapter<Post> {

    static class ViewHolder {
        TextView userId;
        TextView userName;
        TextView contain;
        ImageView likeButton;
        TextView likeNum;
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
            viewHolder.contain = (TextView) convertView.findViewById(R.id.contain);
            viewHolder.likeButton = (ImageView) convertView.findViewById(R.id.likeButton);
            viewHolder.likeNum = (TextView) convertView.findViewById(R.id.likeNum);
            viewHolder.userId = (TextView) convertView.findViewById(R.id.userId);
            convertView.setTag(viewHolder);

        }

        final Post post = getItem(position);

        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.plusLike();
                int likeNum = post.getLikeNum();

                Firebase root = new Firebase(BuildConfig.FIREBASE_URL).child("post/" + post.getTimeStampKey() + "/likeNum");
                root.setValue(likeNum);

                ((TextView)viewHolder.likeNum.findViewById(R.id.likeNum)).setText(String.valueOf(likeNum));
            }
        });

        ((TextView) viewHolder.userName.findViewById(R.id.userName)).setText(post.getUser().getUserName());
        ((TextView) viewHolder.userId.findViewById(R.id.userId)).setText("@" + post.getUser().getUserId());
        ((TextView) viewHolder.contain.findViewById(R.id.contain)).setText(post.getText());
        ((TextView) viewHolder.likeNum.findViewById(R.id.likeNum)).setText(String.valueOf(post.getLikeNum()));

        return convertView;
    }
}
