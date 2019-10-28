package com.zzy.dailychack.ui.gallery;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zzy.dailychack.R;
import com.zzy.dailychack.util.APIQuery;

import java.util.List;

public class GalleryFragment extends Fragment {

    public static final String SINA_STOCK_URL="https://hq.sinajs.cn/list=s_sh000001";//Sina股票数据接口

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        TextView a_stock_content = root.findViewById(R.id.a_stock_content);
        TextView a_stock_bodong= root.findViewById(R.id.a_stock_bodong);

        //获取实时股票参数
        new APIQuery(a_stock_content,a_stock_bodong).execute(SINA_STOCK_URL);

        PackageManager packageManager = context.getPackageManager();
        //获取所有安装的app
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        for(PackageInfo info : installedPackages){
            String packageName = info.packageName;//app包名
            ApplicationInfo ai = packageManager.getApplicationInfo(packageName, 0);
            String appName = (String) packageManager.getApplicationLabel(ai);//获取应用名称
        }



        return root;
    }
}