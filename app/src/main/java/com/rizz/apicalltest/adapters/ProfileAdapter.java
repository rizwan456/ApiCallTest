package com.rizz.apicalltest.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.profile.model.Profile;
import com.rizz.apicalltest.databinding.ItemProfileBinding;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Profile> profileList;
    ItemProfileBinding profileBinding;

    public ProfileAdapter(List<Profile> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        profileBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_profile,viewGroup,false);
        return new ProfileViewHolder(profileBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ProfileViewHolder){
            ProfileViewHolder profileViewHolder=(ProfileViewHolder) viewHolder;
            profileViewHolder.bindData(i);
        }
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder{
        ItemProfileBinding profileBinding;
        public ProfileViewHolder(ItemProfileBinding profileBinding) {
            super(profileBinding.getRoot());
            this.profileBinding=profileBinding;
        }

        public void bindData(int position){
            profileBinding.fNameTv.setText(profileList.get(position).getfName());
            profileBinding.lNameTv.setText(profileList.get(position).getlName());
            profileBinding.mobileTv.setText(profileList.get(position).getMobile());
            profileBinding.addressTv.setText(profileList.get(position).getAddress());
            profileBinding.emailTv.setText(profileList.get(position).getEmail());
            profileBinding.profileImage.setImageURI(profileList.get(position).getImgUrl());
        }
    }
}
