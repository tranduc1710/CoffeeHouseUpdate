package com.example.coffeehouse.nhom2.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.coffeehouse.nhom2.Main2Activity;
import com.example.coffeehouse.nhom2.fragment.QLTang2Fragemnt;
import com.example.coffeehouse.nhom2.fragment.QLTang1Fragment;

public class QLViewPage extends FragmentPagerAdapter {
    String IDNH,IMGNH;
    Bundle bundle;

    public QLViewPage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        IDNH = Main2Activity.IDNH;
        IMGNH = Main2Activity.IMGNH;
        switch (i){
            case 0:
                QLTang1Fragment buatruaFragment = new QLTang1Fragment();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
                bundle.putString("IMGNH",IMGNH);
                buatruaFragment.setArguments(bundle);
                return buatruaFragment;
            case 1:
                QLTang2Fragemnt buaToiFragemnt = new QLTang2Fragemnt();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
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
