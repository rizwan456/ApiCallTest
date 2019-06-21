package com.rizz.apicalltest.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.feeds.model.Feeds;
import com.rizz.apicalltest.adapters.FeedsAdapter;
import com.rizz.apicalltest.databinding.FragmentFeedsBinding;
import com.rizz.apicalltest.viewmodels.feeds.FeedsViewModel;
import com.rizz.apicalltest.viewmodels.models.Message;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentFeedsBinding feedsBinding;

    FeedsViewModel feedsViewModel;


    public FeedsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedsFragment newInstance(String param1, String param2) {
        FeedsFragment fragment = new FeedsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedsViewModel= ViewModelProviders.of(this).get(FeedsViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        feedsBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_feeds, container, false);
        setUp();
        return feedsBinding.getRoot();
    }

    private void setUp() {
        feedsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //feedsBinding.recyclerView.setAdapter(new FeedsAdapter(null,getActivity()));

        feedsBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedsBinding.swipeRefreshLayout.setRefreshing(false);
                feedsViewModel.fetchFeeds();
            }
        });

        feedsViewModel.message.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {

            }
        });

        feedsViewModel.networkCall.observe(this, new Observer<NetworkCall>() {
            @Override
            public void onChanged(@Nullable NetworkCall networkCall) {
                if(networkCall.getNetworkTag()== FeedsViewModel.NetworkTag.FETCH_FEEDS){
                    updateUi(networkCall);
                }
            }
        });

        feedsViewModel.updateFeeds().observe(this, new Observer<List<Feeds>>() {
            @Override
            public void onChanged(@Nullable List<Feeds> feeds) {
                feedsBinding.recyclerView.setAdapter(new FeedsAdapter(feeds,getActivity()));
            }
        });

        feedsViewModel.fetchFeeds();


    }

    private void updateUi(NetworkCall networkCall) {
        switch (networkCall.getNetworkStatus()){
            case SUCCESS:
                break;
            case FAIL:
                break;
            case DONE:
                break;
            case ERROR:
                break;
            case NO_DATA:
                break;
            case IN_PROCESS:
                break;
            case NO_INTERNET:
                break;
            case INVALID_DATA:
                break;
            case UNAUTHORIZED:
                break;
        }
    }

}
