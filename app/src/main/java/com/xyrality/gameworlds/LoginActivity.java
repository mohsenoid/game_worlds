package com.xyrality.gameworlds;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.xyrality.gameworlds.network.NetworkHelper;
import com.xyrality.gameworlds.network.event.LoginEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Mohsen on 1/20/16.
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    final static String TAG = LoginActivity.class.getSimpleName();

    boolean loginIsInProgress = false;
    // butterknife UI references.
    @Bind(R.id.email)
    EditText emailEditText;
    @Bind(R.id.password)
    EditText passwordEditText;
    @Bind(R.id.login_progress)
    View progressView;
    @Bind(R.id.login_form)
    View loginFormView;
    private NetworkHelper networkhelper;

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        if (loginIsInProgress) {
            return;
        }

        // Reset login fields errors.
        emailEditText.setError(null);
        passwordEditText.setError(null);

        // Store values at the time of the login attempt.
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError(getString(R.string.error_field_required));
            focusView = passwordEditText;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = passwordEditText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError(getString(R.string.error_field_required));
            focusView = emailEditText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            focusView = emailEditText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a retrofit task to
            // perform the user login attempt.
            showProgress(true);
            networkhelper.login(email, password);
            loginIsInProgress = true;
        }
    }

    @OnEditorAction(R.id.password)
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
            attemptLogin();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form UI.
        ButterKnife.bind(this);

        networkhelper = NetworkHelper.getInstance(this);
    }

    // email validation method
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    // password validation method
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //register EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        //unregister EventBus
        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    // This method will be called when a LoginEvent is posted
    @Subscribe
    public void onEvent(LoginEvent loginEvent) {
        showProgress(false);
        loginIsInProgress = false;

        if (loginEvent.isSuccess() && loginEvent.getWorldsResponse() != null) {
            Intent worldIntent = new Intent(this, WorldsActivity.class);
            worldIntent.putExtra(WorldsActivity.WORLDS_DATA, loginEvent.getWorldsResponse());
            startActivity(worldIntent);
        } else {
            // something goes wrong!
            Snackbar.make(loginFormView, loginEvent.getThrowable().getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

}

