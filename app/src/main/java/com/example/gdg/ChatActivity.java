package com.example.gdg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recycler;
    AdapterCommentExpand adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );

//        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar);
//        setSupportActionBar(toolbar);
        recycler = (RecyclerView) findViewById(R.id.main_recycler);
        adapter = new AdapterCommentExpand(this);
        adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_expand_all:
                adapter.expandAll();
                return true;
            case R.id.action_collapse_all:
                adapter.collapseAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}