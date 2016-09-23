package be.android.hcpl.retrofitfirebaseexample.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.R;
import be.android.hcpl.retrofitfirebaseexample.model.Task;
import be.android.hcpl.retrofitfirebaseexample.mvp.AppActivity;
import be.android.hcpl.retrofitfirebaseexample.mvp.OverviewPresenterImpl;

public class MainActivity extends AppActivity<OverviewPresenterImpl> implements OverviewPresenterImpl.View {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onAddItemClicked();
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected OverviewPresenterImpl getPresenter() {
        return new OverviewPresenterImpl();
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
    public void showAddItem() {
        // TODO show item crudgit s
    }

    @Override
    public void showItem(final Task task) {
        // TODO show item detail
    }

    @Override
    public void showAbout() {
        showMessage(R.string.info_about, null);
    }

    @Override
    public void showLoadedItems(final Map<String, Task> tasks) {
        // TODO show loaded items in a list
    }

    @Override
    public void showErrorLoading() {
        showMessage(R.string.err_loading, null);
    }
}
