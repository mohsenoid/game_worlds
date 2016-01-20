package com.xyrality.gameworlds.network.event;

import com.xyrality.gameworlds.network.model.LoginResponse;

/**
 * Created by Mohsen on 1/20/16.
 */
public class LoginEvent {
    boolean success;
    LoginResponse loginResponse;
    Throwable throwable;

    public LoginEvent(boolean success, LoginResponse loginResponse) {
        this.success = success;
        this.loginResponse = loginResponse;
    }

    public LoginEvent(Throwable throwable) {
        success = false;
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
