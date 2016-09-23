package be.android.hcpl.retrofitfirebaseexample.mvp;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;

import be.android.hcpl.retrofitfirebaseexample.model.Task;
import be.android.hcpl.retrofitfirebaseexample.remote.RemoteServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudPresenterImpl extends AppPresenter<CrudPresenter.View> implements CrudPresenter {

    private static final String TAG = "CrudPresenterImpl";

    private
    @Mode int mode = MODE_STANDARD;

    private RemoteServiceImpl.RemoteService service = RemoteServiceImpl.getInstance(); // TODO inject instead

    @Override
    public boolean showDeleteMenu() {
        return mode == MODE_EDIT;
    }

    @Override
    public int getMode() {
        return mode;
    }

    @Override
    public void setMode(@Mode final int desiredMode) {
        mode = desiredMode;
    }

    @Override
    public boolean enableTitleEdit() {
        return mode != MODE_EDIT;
    }

    @Override
    public void onDoneClicked(
            final String inputTitle,
            final String inputDescription
    ) {
        // validation needed to prevent pushing invalid data breaking json response from service
        if (validInput(inputTitle)) {
            // no need for create vs edit since push will also update when done with same key (title)
            pushTask(inputTitle, inputDescription);
        } else {
            getView().showInputInvalid();
        }
    }

    private boolean validInput(final String inputTitle) {
        return !TextUtils.isEmpty(inputTitle);
    }

    private void pushTask(
            final String inputTitle,
            final String inputDescription
    ) {
        Task task = new Task(inputTitle, inputDescription);
        Call<Task> call = service.createTask(task.getTitle(), task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(
                    final Call<Task> call,
                    final Response<Task> response) {
                getView().showUpdateDone();
            }

            @Override
            public void onFailure(
                    final Call<Task> call,
                    final Throwable t) {
                getView().showUpdateFailed();
            }
        });
    }

    @Override
    public void deleteItem(final String id) {
        getView().showDeleteConfirmation(id);
    }

    @Override
    public void deleteItemConfirmed(final String id) {
        Call<Task> call = service.deleteTask(id);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(
                    final Call<Task> call,
                    final Response<Task> response) {
                getView().showItemDeleted();
            }

            @Override
            public void onFailure(
                    final Call<Task> call,
                    final Throwable t) {
                getView().showItemDeleteFailed();
            }
        });
    }

    @Override
    protected View getDummyView() {
        return new CrudPresenter.View() {

            @Override
            public void showInputInvalid() {
                Log.d(TAG, "showInputInvalid: not attached");
            }

            @Override
            public void showUpdateDone() {
                Log.d(TAG, "showUpdateDone: not attached");
            }

            @Override
            public void showUpdateFailed() {
                Log.d(TAG, "showUpdateFailed: not attached");
            }

            @Override
            public void showItemDeleted() {
                Log.d(TAG, "showItemDeleted: not attached");
            }

            @Override
            public void showItemDeleteFailed() {
                Log.d(TAG, "showItemDeleteFailed: not attached");
            }

            @Override
            public void showMessage(
                    @StringRes final int messageResourceId,
                    final MessageCallback callback) {
                Log.d(TAG, "showMessage: not attached");
            }

            @Override
            public void showDeleteConfirmation(final String id) {
                Log.d(TAG, "showDeleteConfirmation: not attached");
            }
        };
    }

}