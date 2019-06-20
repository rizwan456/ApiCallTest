package com.rizz.apicalltest.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.notification.model.Notification;
import com.rizz.apicalltest.databinding.FragmentNotificationBinding;
import com.rizz.apicalltest.viewmodels.models.Message;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;
import com.rizz.apicalltest.viewmodels.notification.NotificationViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentNotificationBinding notificationBinding;
    NotificationViewModel notificationViewModel;


    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationViewModel= ViewModelProviders.of(this).get(NotificationViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        notificationBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_notification, container, false);
        setUp();
        return notificationBinding.getRoot();
    }

    private void setUp() {
        notificationViewModel.getMessage().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {

            }
        });

        notificationViewModel.networkCall.observe(this, new Observer<NetworkCall>() {
            @Override
            public void onChanged(@Nullable NetworkCall networkCall) {

            }
        });

        notificationViewModel.updateNotification().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(@Nullable List<Notification> notifications) {

            }
        });

        notificationViewModel.fetchNotifications();
    }

}
