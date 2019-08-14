package com.example.coffeehouse.nhom2.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.coffeehouse.nhom2.Main2Activity;
import com.example.coffeehouse.nhom2.fragment.QLBuaToiFragemnt;
import com.example.coffeehouse.nhom2.fragment.QLBuatruaFragment;

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
                QLBuatruaFragment buatruaFragment = new QLBuatruaFragment();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
                bundle.putString("IMGNH",IMGNH);
                buatruaFragment.setArguments(bundle);
                return buatruaFragment;
            case 1:
                QLBuaToiFragemnt buaToiFragemnt = new QLBuaToiFragemnt();
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
