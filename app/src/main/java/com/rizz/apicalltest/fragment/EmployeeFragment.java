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
import com.rizz.apicalltest.aaprepos.employees.model.Employee;
import com.rizz.apicalltest.adapters.EmployeeAdadpter;
import com.rizz.apicalltest.databinding.FragmentEmployeeBinding;
import com.rizz.apicalltest.viewmodels.employee.EmployeeViewModel;
import com.rizz.apicalltest.viewmodels.models.Message;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EmployeeViewModel employeeViewModel;
    private FragmentEmployeeBinding fragmentBinding;


    public EmployeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmployeeFragment newInstance(String param1, String param2) {
        EmployeeFragment fragment = new EmployeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee, container, false);
        setUp();
        return fragmentBinding.getRoot();
    }

    private void setUp() {
        fragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragmentBinding.swipeRefreshLayout.setRefreshing(false);
                employeeViewModel.fetchEmployees();
            }
        });

        employeeViewModel.getMessage().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(@Nullable Message message) {

            }
        });


        employeeViewModel.getNetworkCallStatus().observe(this, new Observer<NetworkCall>() {
            @Override
            public void onChanged(@Nullable NetworkCall networkCall) {
                switch (networkCall.getNetworkTag())
                {
                    case EmployeeViewModel.NetworkTags.FETCH_EMPLOYEE_TAG:
                        updateUiForEmployees(networkCall);
                        break;
                }
            }
        });

        employeeViewModel.updateEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                fragmentBinding.recyclerView.setAdapter(new EmployeeAdadpter(employees));
            }
        });

        employeeViewModel.fetchEmployees();
    }

    private void updateUiForEmployees(NetworkCall networkCall) {
        switch (networkCall.getNetworkStatus())
        {
            case NO_INTERNET:
                break;
            case IN_PROCESS:
                fragmentBinding.progressBar.setVisibility(View.VISIBLE);
                break;
            case DONE:
                fragmentBinding.progressBar.setVisibility(View.GONE);
                break;
            case SUCCESS:
                break;
            case NO_DATA:
                break;
            case FAIL:
                break;
            case ERROR:
                break;
        }
    }


}
