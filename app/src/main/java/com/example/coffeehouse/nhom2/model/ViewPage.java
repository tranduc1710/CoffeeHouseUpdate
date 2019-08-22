package com.example.coffeehouse.nhom2.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.coffeehouse.nhom2.DatBanActivity;
import com.example.coffeehouse.nhom2.fragment.Tang2Fragemnt;
import com.example.coffeehouse.nhom2.fragment.Tang1Fragment;

public class ViewPage extends FragmentPagerAdapter {
    String IDNH,IMGNH;
    Bundle bundle;

    public ViewPage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        IDNH = DatBanActivity.IDNH;
        IMGNH = DatBanActivity.IMGNH;
        switch (i){
            case 0:
                Tang1Fragment buatruaFragment = new Tang1Fragment();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
//                bundle.putString("IMGNH",IMGNH);
                buatruaFragment.setArguments(bundle);
                return buatruaFragment;
            case 1:
                Tang2Fragemnt buaToiFragemnt = new Tang2Fragemnt();
                bundle = new Bundle();
//                bundle.putString("IDNH",IDNH);
                bundle.putString("IMGNH",IMGNH);
                buaToiFragemnt.setArguments(bundle);
                return buaToiFragemnt;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
