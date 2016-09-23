package be.android.hcpl.retrofitfirebaseexample.mvp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * For this MVP implementation all Activities and Fragment should extend these base classes so the presenter is attached and detached when needed.
 * The Activity and Fragment represent the AppView.
 *
 * @param <P> is your AppPresenter impl, takes care of attaching and detaching view when need. Same could be implemented for a Fragment if using
 *            fragments also.
 */
public abstract class AppActivity<P extends AppPresenter> extends AppCompatActivity implements AppView {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
    }

    // TODO instance state saving and recovery would go here

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().attach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detach(this);
    }

    /**
     * implement to specify layout view
     *
     * @return
     */
    protected abstract
    @LayoutRes
    int getLayoutResourceId();

    /**
     * implement to specify presenter instance
     *
     * @return
     */
    protected abstract P getPresenter();

    /**
     * Should always return a non null view that can be used as a fallback when current focus view is not set
     *
     * @return
     */
    protected abstract
    @NonNull
    View getFallbackView();

    /**
     * helper to always return a valid view even if non in focus
     */
    protected
    @NonNull
    View getCurrentView() {
        return getCurrentFocus() != null ? getCurrentFocus() : getFallbackView();
    }

    @Override
    public void showMessage(
            @StringRes final int messageResourceId,
            final MessageCallback callback) {

        Snackbar.make(getCurrentView(), messageResourceId, Snackbar.LENGTH_LONG).setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(
                    final Snackbar snackbar,
                    final int event) {
                super.onDismissed(snackbar, event);
                if (callback != null) {
                    callback.done();
                }
            }

            @Override
            public void onShown(final Snackbar snackbar) {
                super.onShown(snackbar);
            }
        }).show();
    }
}
