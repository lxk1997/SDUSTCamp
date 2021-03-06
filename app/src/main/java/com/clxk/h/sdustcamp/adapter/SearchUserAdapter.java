package com.clxk.h.sdustcamp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clxk.h.sdustcamp.MyApplication;
import com.clxk.h.sdustcamp.R;
import com.clxk.h.sdustcamp.bean.User;
import com.clxk.h.sdustcamp.ui.ImStrangerInfo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Chook_lxk on 19/1/1
 *
 * 查找好友适配器
 */
public class SearchUserAdapter extends ArrayAdapter {

    private ViewHolder viewHolder = new ViewHolder();

    public SearchUserAdapter( Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        final User user = (User)getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_im_user,null);
            viewHolder.tv_user_nick = convertView.findViewById(R.id.tv_im_user_nick);
            viewHolder.riv_user_avatar = convertView.findViewById(R.id.riv_im_user_avatar);
            viewHolder.btn_im_user_search = convertView.findViewById(R.id.btn_im_user_search);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(user.getNick() == null) {
            viewHolder.tv_user_nick.setText(user.getMobilePhoneNumber());
        } else {
            viewHolder.tv_user_nick.setText(user.getNick());
        }
        final Activity a = (Activity) viewHolder.riv_user_avatar.getContext();
        if(user.getAvatar() != null) {
            Glide.with(viewHolder.riv_user_avatar.getContext()).load(user.getAvatar().getFileUrl()).into(viewHolder.riv_user_avatar);
        }
        viewHolder.btn_im_user_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a, ImStrangerInfo.class);
                MyApplication.getInstance().user = user;
                a.startActivity(intent);
                a.finish();
            }
        });
        return convertView;
    }

    class ViewHolder {

        RoundedImageView riv_user_avatar;
        TextView tv_user_nick;
        Button btn_im_user_search;

        public ViewHolder() {
            super();
        }
    }
}
