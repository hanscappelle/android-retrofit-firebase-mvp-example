package be.android.hcpl.retrofitfirebaseexample.mvp;

import android.support.annotation.StringRes;
import android.util.Log;

import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.model.Task;
import be.android.hcpl.retrofitfirebaseexample.remote.RemoteServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OverviewPresenterImpl extends AppPresenter<OverviewPresenter.View> implements OverviewPresenter {

    private static final String TAG = "OverviewPresenterImpl";

    private RemoteServiceImpl.RemoteService remoteService; // instead of passing in ctor we could also look into Dagger Dependency Injection

    public OverviewPresenterImpl(final RemoteServiceImpl.RemoteService remoteService) {

        this.remoteService = remoteService;
    }

    @Override
    public void onAddItemClicked() {
        getView().showAddItem();
    }

    @Override
    public void reloadData() {
        Call<Map<String, Task>> call = remoteService.getAllTasks();
        call.enqueue(new Callback<Map<String, Task>>() {
            @Override
            public void onResponse(
                    final Call<Map<String, Task>> call,
                    final Response<Map<String, Task>> response) {
                final Map<String, Task> tasks = response.body();
                if (tasks != null && !tasks.isEmpty()) {
                    getView().showLoadedItems(tasks);
                    Log.d(TAG, "onResponse: tasks found as map with size: " + tasks.size());
                } else {
                    Log.d(TAG, "onResponse: no tasks found");
                }
            }

            @Override
            public void onFailure(
                    final Call<Map<String, Task>> call,
                    final Throwable t) {
                getView().showErrorLoading();
                Log.e(TAG, "onResume: failed to find task", t);
            }
        });
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