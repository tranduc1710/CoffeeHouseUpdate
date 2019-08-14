package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.coffeehouse.nhom2.model.ViewPage;

public class DatBanActivity extends AppCompatActivity {
    private Toolbar toolbarDatban;
    private TabLayout tabDatban;
    private ViewPager viewDatban;
    public static String IDNH,IMGNH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ban);
        toolbarDatban = (Toolbar) findViewById(R.id.toolbarDatban);
        tabDatban = (TabLayout) findViewById(R.id.tabDatban);
        viewDatban = (ViewPager) findViewById(R.id.viewDatban);

        PagerAdapter adapter = new ViewPage(getSupportFragmentManager());
        viewDatban.setAdapter(adapter);
        tabDatban.setupWithViewPager(viewDatban);
        tabDatban.getTabAt(0).setText("Tầng 1");
        tabDatban.getTabAt(1).setText("Tầng 2");
        ActionToolbar();
        Intent intent = getIntent();
        IDNH = intent.getStringExtra("IDNH");
        IMGNH = intent.getStringExtra("IMGNH");

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarDatban);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDatban.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
