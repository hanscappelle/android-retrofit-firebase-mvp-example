package be.android.hcpl.retrofitfirebaseexample.mvp;

import android.support.annotation.IntDef;

public interface CrudPresenter {

    int MODE_STANDARD = 0;
    int MODE_EDIT = 1;

    void setMode(@Mode int mode);

    boolean showDeleteMenu();

    @Mode int getMode();

    boolean enableTitleEdit();

    void onDoneClicked(
            String inputTitle,
            String inputDescription);

    void deleteItem(String id);

    void deleteItemConfirmed(String id);

    interface View extends AppView {

        void showInputInvalid();

        void showUpdateDone();

        void showUpdateFailed();

        void showItemDeleted();

        void showItemDeleteFailed();

        void showDeleteConfirmation(String id);

    }

    @IntDef({MODE_STANDARD, MODE_EDIT})
    @interface Mode {

    }
}
