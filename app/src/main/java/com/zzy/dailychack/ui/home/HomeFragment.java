package com.zzy.dailychack.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zzy.dailychack.R;
import com.zzy.dailychack.util.DateUtil;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //当前日期
        TextView textView_cu = root.findViewById(R.id.textView_cur_date);

        String cur_date_str=DateUtil.getStandardNowDateStr();
        textView_cu.setText("当前日期:"+cur_date_str);


        //浦发信用卡
        String yearMonthNow_pf = DateUtil.getShortNowDateStr();
        yearMonthNow_pf+="13";
        TextView textView_pf = root.findViewById(R.id.textView_acc_bill_date_pf);

        textView_pf.setText(DateUtil.shortDateStrCovertStandard(yearMonthNow_pf));
        if(DateUtil.isBeforeNow(yearMonthNow_pf)){
            textView_pf.setTextColor(0xff008000);//绿色
        }

        EditText editText_pf=root.findViewById(R.id.free_day_pf);
        int free_day_pf=Integer.parseInt(editText_pf.getText().toString());

        TextView due_date_pf = root.findViewById(R.id.due_date_pf);
        due_date_pf.setText(DateUtil.shortDateStrCovertStandardByNDays(yearMonthNow_pf,free_day_pf));

        //广发信用卡

        String yearMonthNow_gf = DateUtil.getShortNowDateStr();
        yearMonthNow_gf+="13";
        TextView textView_gf = root.findViewById(R.id.textView_acc_bill_date_gf);

        textView_gf.setText(DateUtil.shortDateStrCovertStandard(yearMonthNow_gf));
        if(DateUtil.isBeforeNow(yearMonthNow_gf)){
            textView_gf.setTextColor(0xff008000);//绿色
        }

        EditText editText_gf=root.findViewById(R.id.free_day_gf);
        int free_day_gf=Integer.parseInt(editText_gf.getText().toString());

        TextView due_date_gf = root.findViewById(R.id.due_date_gf);
        due_date_gf.setText(DateUtil.shortDateStrCovertStandardByNDays(yearMonthNow_gf,free_day_gf));
        //兴业信用卡

        String yearMonthNow_xy = DateUtil.getShortNowDateStr();
        yearMonthNow_xy+="22";
        TextView textView_xy = root.findViewById(R.id.textView_acc_bill_date_xy);

        textView_xy.setText(DateUtil.shortDateStrCovertStandard(yearMonthNow_xy));
        if(DateUtil.isBeforeNow(yearMonthNow_xy)){
            textView_xy.setTextColor(0xff008000);//绿色
        }

        EditText editText_xy=root.findViewById(R.id.free_day_xy);
        int free_day_xy=Integer.parseInt(editText_xy.getText().toString());

        TextView due_date_xy = root.findViewById(R.id.due_date_xy);
        due_date_xy.setText(DateUtil.shortDateStrCovertStandardByNDays(yearMonthNow_xy,free_day_xy));

        //交行信用卡

        String yearMonthNow_jh = DateUtil.getShortNowDateStr();
        yearMonthNow_jh+="27";
        TextView textView_jh = root.findViewById(R.id.textView_acc_bill_date_jh);
        textView_jh.setText(DateUtil.shortDateStrCovertStandard(yearMonthNow_jh));
        if(DateUtil.isBeforeNow(yearMonthNow_jh)){
            textView_jh.setTextColor(0xff008000);//绿色
        }


        TextView due_date_jh = root.findViewById(R.id.due_date_jh);
        due_date_jh.setText(DateUtil.standardNextMonthTimeFormatStr("21"));

        //招商信用卡
        String yearMonthNow_zs = DateUtil.getShortNowDateStr();
        yearMonthNow_zs+="16";
        TextView textView_zs = root.findViewById(R.id.textView_acc_bill_date_zs);

        textView_zs.setText(DateUtil.shortDateStrCovertStandard(yearMonthNow_zs));
        if(DateUtil.isBeforeNow(yearMonthNow_zs)){
            textView_zs.setTextColor(0xff008000);//Green纯绿
        }


        TextView due_date_zs = root.findViewById(R.id.due_date_zs);
        due_date_zs.setText(DateUtil.standardNextMonthTimeFormatStr("4"));


        return root;
    }

   /* *//**
     * 自动变换页面信用卡信息
     *//*
    public void changeCreditInfo(){


    }*/
}