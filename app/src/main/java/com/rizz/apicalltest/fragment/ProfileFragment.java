package com.rizz.apicalltest.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.profile.model.Profile;
import com.rizz.apicalltest.adapters.ProfileAdapter;
import com.rizz.apicalltest.databinding.FragmentProfileBinding;
import com.rizz.apicalltest.viewmodels.employee.EmployeeViewModel;
import com.rizz.apicalltest.viewmodels.models.Message;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;
import com.rizz.apicalltest.viewmodels.profile.ProfileViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProfileViewModel profileViewModel;

    FragmentProfileBinding profileBinding;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileViewModel= ViewModelProviders.of(this).get(ProfileViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);
        setUp();
        return profileBinding.getRoot();
    }

    private void setUp() {
        profileBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profileViewModel.getMessage().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {

            }
        });

        profileViewModel.getNetworkCallStatus().observe(this, new Observer<NetworkCall>() {
            @Override
            public void onChanged(@Nullable NetworkCall networkCall) {
                if(networkCall.getNetworkTag()== EmployeeViewModel.NetworkTags.FETCH_EMPLOYEE_TAG){
                    updateUiForProfile(networkCall);
                }
            }
        });

        profileViewModel.updateProfileData().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(@Nullable List<Profile> profiles) {
                profileBinding.recyclerView.setAdapter(new ProfileAdapter(profiles));
            }
        });

        profileViewModel.fecthProfileDetails();
    }

    private void updateUiForProfile(NetworkCall networkCall) {
        switch (networkCall.getNetworkStatus()){
            case NO_INTERNET:
                break;
            case IN_PROCESS:
                break;
            case DONE:
                break;
            case ERROR:
                break;
            case FAIL:
                break;
        }
    }

}
