package com.rizz.apicalltest.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rizz.apicalltest.R;
import com.rizz.apicalltest.aaprepos.employees.model.Employee;
import com.rizz.apicalltest.databinding.EmpItemBinding;

import java.util.List;

public class EmployeeAdadpter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    EmpItemBinding itemBinding;
    List<Employee> employeeList;

    public EmployeeAdadpter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.emp_item,viewGroup,false);
        return new EmployeesItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof EmployeesItemViewHolder){
            EmployeesItemViewHolder employeesItemViewHolder=(EmployeesItemViewHolder)viewHolder;
            employeesItemViewHolder.bindData(i);
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeesItemViewHolder extends RecyclerView.ViewHolder {
        EmpItemBinding itemBinding;
        EmployeesItemViewHolder(EmpItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
        void bindData(int position){
            itemBinding.avtarImg.setImageURI(employeeList.get(position).getImageURL());
            itemBinding.name.setText(employeeList.get(position).getName());
            itemBinding.mobile.setText(employeeList.get(position).getMobile());
        }
    }
}
