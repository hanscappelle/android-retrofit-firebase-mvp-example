package be.android.hcpl.retrofitfirebaseexample.mvp;

import android.support.annotation.StringRes;
import android.util.Log;

import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.model.Task;


public class OverviewPresenterImpl extends AppPresenter<OverviewPresenter.View> implements OverviewPresenter {

    private static final String TAG = "OverviewPresenterImpl";

    // TODO create service for fetching data

    public OverviewPresenterImpl() {

    }

    @Override
    public void onAddItemClicked() {
        getView().showAddItem();
    }

    @Override
    public void reloadData() {
        // TODO fetch data from service
    }

    @Override
    public void onItemClicked(final Task task) {
        getView().showItem(task);
    }

    @Override
    public void onAboutClicked() {
        getView().showAbout();
    }

    @Override
    protected View getDummyView() {
        return new OverviewPresenter.View() {
            @Override
            public void showAddItem() {
                Log.d(TAG, "showAddItem: view not attached");
            }

            @Override
            public void showItem(final Task task) {
                Log.d(TAG, "showItem: view not attached");
            }

            @Override
            public void showAbout() {
                Log.d(TAG, "showAbout: view not attached");
            }

            @Override
            public void showLoadedItems(final Map<String, Task> tasks) {
                Log.d(TAG, "showLoadedItems: not attached");
            }

            @Override
            public void showErrorLoading() {
                Log.d(TAG, "showErrorLoading: not attached");
            }

            @Override
            public void showMessage(
                    @StringRes final int messageResourceId,
                    final MessageCallback callback) {
                Log.d(TAG, "showMessage: not attached");
            }
        };
    }

}