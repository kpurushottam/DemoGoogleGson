package com.krp.android.demogooglegson;

/**
 * Created by purushottam.kumar on 11/26/2015.
 */
public class TestClassReflection {
    public static final String TAG = TestClassReflection.class.getSimpleName();

    private String loginUsername;
    private String loginPassword;

    protected User user;

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    protected User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    private int getSessionTimeOut() {
        return 500; // time in milliseconds
    }
}
