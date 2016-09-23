package be.android.hcpl.retrofitfirebaseexample.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.R;
import be.android.hcpl.retrofitfirebaseexample.adapter.TaskAdapter;
import be.android.hcpl.retrofitfirebaseexample.model.Task;
import be.android.hcpl.retrofitfirebaseexample.mvp.AppActivity;
import be.android.hcpl.retrofitfirebaseexample.mvp.OverviewPresenterImpl;
import be.android.hcpl.retrofitfirebaseexample.remote.RemoteServiceImpl;
import butterknife.BindView;

public class MainActivity extends AppActivity<OverviewPresenterImpl> implements OverviewPresenterImpl.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.recycler_tasks) RecyclerView recyclerView;
    private OverviewPresenterImpl presenter;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onAddItemClicked();
            }
        });
        initRecyclerView();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected OverviewPresenterImpl getPresenter() {
        if (presenter == null) {
            presenter = new OverviewPresenterImpl(RemoteServiceImpl.getInstance());
        }
        return presenter;
    }

    @NonNull
    @Override
    protected View getFallbackView() {
        return fab;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            getPresenter().onAboutClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().reloadData();
    }

    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        taskAdapter = new TaskAdapter(getApplicationContext(), new TaskAdapter.OnItemSelectedCallback() {
            @Override
            public void onClick(final Task task) {
                getPresenter().onItemClicked(task);
            }
        });
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void showAddItem() {
        startActivity(new Intent(getApplicationContext(), CrudActivity.class));
    }

    @Override
    public void showItem(final Task task) {
        startActivity(CrudActivity.getIntentFor(MainActivity.this, task));
    }

    @Override
    public void showAbout() {
        showMessage(R.string.info_about, null);
    }

    @Override
    public void showLoadedItems(final Map<String, Task> tasks) {
        taskAdapter.setItems(new ArrayList<>(tasks.values()));
    }

    @Override
    public void showErrorLoading() {
        showMessage(R.string.error_loading_data, null);
    }
}
