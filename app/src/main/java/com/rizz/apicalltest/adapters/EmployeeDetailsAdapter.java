package com.rizz.apicalltest.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.details.model.EmployeeDetails;
import com.rizz.apicalltest.databinding.EmployeeDetailsItemBinding;

import java.util.List;

public class EmployeeDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    EmployeeDetailsItemBinding itemBinding;
    List<EmployeeDetails> employeeDetailsLIst;

    public EmployeeDetailsAdapter(List<EmployeeDetails> employeeDetailsLIst) {
        this.employeeDetailsLIst = employeeDetailsLIst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.employee_details_item,viewGroup,false);
        return new EmployeeItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof EmployeeItemViewHolder){
            EmployeeItemViewHolder employeeItemViewHolder=(EmployeeItemViewHolder) viewHolder;
            employeeItemViewHolder.bindData(i);
        }
    }

    @Override
    public int getItemCount() {
        return employeeDetailsLIst.size();
    }

    class EmployeeItemViewHolder extends RecyclerView.ViewHolder {
        EmployeeDetailsItemBinding itemBinding;
        public EmployeeItemViewHolder(EmployeeDetailsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
        public void bindData(int poistion){
            itemBinding.name.setText(employeeDetailsLIst.get(poistion).getName());
            itemBinding.mobile.setText(employeeDetailsLIst.get(poistion).getMobile());
        }
    }
}
