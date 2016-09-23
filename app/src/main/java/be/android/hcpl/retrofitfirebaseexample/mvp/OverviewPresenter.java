package be.android.hcpl.retrofitfirebaseexample.mvp;

import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.model.Task;


public interface OverviewPresenter {

    void onAddItemClicked();

    void reloadData();

    void onItemClicked(Task task);

    void onAboutClicked();

    interface View extends AppView {

        void showAddItem();

        void showItem(Task task);

        void showAbout();

        void showLoadedItems(Map<String, Task> tasks);

        void showErrorLoading();

    }

}
