package com.fanda.zeng.bookpratice.material;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.fanda.zeng.bookpratice.listview.Fruit;
import com.fanda.zeng.bookpratice.recyclerview.CardViewFruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 曾凡达 on 2017/3/20.
 * des material 演示界面
 */

public class DrawerLayoutActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Fruit[] fruits = {new Fruit("Apple", R.mipmap.apple), new Fruit("Banana", R.mipmap.banana),
            new Fruit("Orange", R.mipmap.orange), new Fruit("Watermelon", R.mipmap.watermelon),
            new Fruit("Pear", R.mipmap.pear), new Fruit("Grape", R.mipmap.grape),
            new Fruit("Pineapple", R.mipmap.pineapple), new Fruit("Strawberry", R.mipmap.strawberry),
            new Fruit("Cherry", R.mipmap.cherry), new Fruit("Mango", R.mipmap.mango)};

    private List<Fruit> fruitList = new ArrayList<>();
    private CardViewFruitAdapter fruitAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawlayout);
        initViews();
        initToolbar();
        initFruits();
        initRecyclerView();
        initSwipeRefeshLayout();
    }

    private void initSwipeRefeshLayout() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFuritList();
            }
        });
    }

    private void refreshFuritList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        fruitAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fruitAdapter = new CardViewFruitAdapter(fruitList,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            fruitList.add(fruits[random.nextInt(fruits.length)]);
        }

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_actioin_button);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        floatingActionButton.setOnClickListener(this);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onClick(View v) {
        recyclerView.scrollToPosition(0);
//        Snackbar.make(v, "Data delete", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DrawerLayoutActivity.this, "Data Restored", Toast.LENGTH_SHORT).show();
//            }
//        }).show();
    }
}
