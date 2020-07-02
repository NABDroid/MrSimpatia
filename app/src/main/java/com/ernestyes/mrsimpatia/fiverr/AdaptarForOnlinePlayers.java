package com.ernestyes.mrsimpatia.fiverr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptarForOnlinePlayers extends RecyclerView.Adapter<AdaptarForOnlinePlayers.ViewHolder> {
    private ArrayList<OnlineStatus> onlineStatusArrayList;
    private Context context;

    public AdaptarForOnlinePlayers(ArrayList<OnlineStatus> onlineStatusArrayList, Context context) {
        this.onlineStatusArrayList = onlineStatusArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptarForOnlinePlayers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_player_single_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptarForOnlinePlayers.ViewHolder holder, int position) {
        OnlineStatus onlineStatus = onlineStatusArrayList.get(position);
        holder.userName.setText(onlineStatus.getUserName());
    }

    @Override
    public int getItemCount() {
        return onlineStatusArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameOnlineUser);
        }
    }
}
