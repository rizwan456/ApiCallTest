package com.rizz.apicalltest.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.feeds.model.Feeds;
import com.rizz.apicalltest.databinding.FeedsItemBinding;

import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private FeedsItemBinding itemBinding;
    private List<Feeds> feedsList;
    private Context context;

    public FeedsAdapter(List<Feeds> feedsList, Context context) {
        this.feedsList = feedsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.feeds_item,viewGroup,false);
        return new FeedsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof FeedsViewHolder){
            FeedsViewHolder feedsViewHolder=(FeedsViewHolder) viewHolder;
            feedsViewHolder.bindData(i);
        }
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    class FeedsViewHolder extends RecyclerView.ViewHolder{
        FeedsItemBinding itemBinding;
        FeedsViewHolder(FeedsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
        void bindData(int position){
            itemBinding.pageImage.setImageURI(Uri.parse(feedsList.get(position).getPageImage()));
           //itemBinding.videoView.setImageURI(Uri.parse(feedsList.get(position).getPageThumbnail()));

            itemBinding.likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemBinding.likeText.getCurrentTextColor()==context.getResources().getColor(R.color.white,context.getTheme())){
                        itemBinding.likeText.setTextColor(context.getResources().getColor(R.color.blue,context.getTheme()));
                        itemBinding.likeIcon.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
                    }else{
                        itemBinding.likeText.setTextColor(context.getResources().getColor(R.color.white,context.getTheme()));
                        itemBinding.likeIcon.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            });
        }
    }
}
