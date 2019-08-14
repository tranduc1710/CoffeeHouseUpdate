package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.coffeehouse.nhom2.model.QLViewPage;

public class Main2Activity extends AppCompatActivity {
    private Toolbar toolbarDatban;
    private TabLayout tabDatban;
    private ViewPager viewDatban;
    public static String IDNH,IMGNH,NAMENH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbarDatban = (Toolbar) findViewById(R.id.toolbarQLDatban);
        tabDatban = (TabLayout) findViewById(R.id.tabQLDatban);
        viewDatban = (ViewPager) findViewById(R.id.viewQLDatban);

        PagerAdapter adapter = new QLViewPage(getSupportFragmentManager());
        viewDatban.setAdapter(adapter);
        tabDatban.setupWithViewPager(viewDatban);
        tabDatban.getTabAt(0).setText("Tầng 1");
        tabDatban.getTabAt(1).setText("Tầng 2");
        ActionToolbar();
        Intent intent = getIntent();
        IDNH = intent.getStringExtra("IDNH");
        NAMENH = intent.getStringExtra("NAMENH");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.itemDangdat:
                Intent intent = new Intent(Main2Activity.this,QuanLyLichsuActivity.class);
                startActivity(intent);
                break;
            case R.id.itemThemBan:
                Intent intent1 = new Intent(Main2Activity.this,ThemBanActivity.class);
                startActivity(intent1);
                break;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}
