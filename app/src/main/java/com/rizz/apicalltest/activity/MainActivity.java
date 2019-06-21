package com.rizz.apicalltest.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.details.model.EmployeeDetails;
import com.rizz.apicalltest.databinding.ActivityMainBinding;
import com.rizz.apicalltest.fragment.EmployeeFragment;
import com.rizz.apicalltest.fragment.EmployeesDetailsFragment;
import com.rizz.apicalltest.fragment.FeedsFragment;
import com.rizz.apicalltest.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        //getActionBar().hide();
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUp();
    }

    private void setUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, FeedsFragment.newInstance(null, null)).commit();
    }
}
