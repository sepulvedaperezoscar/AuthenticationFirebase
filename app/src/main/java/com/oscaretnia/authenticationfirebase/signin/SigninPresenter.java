package com.oscaretnia.authenticationfirebase.signin;

import android.util.Patterns;

public class SigninPresenter implements SigninContract.Presenter, SigninContract.CompleteListener {

    private SigninContract.View view;
    private SigninContract.Interactor interactor;

    public SigninPresenter(SigninContract.View view) {
        this.view = view;
        this.interactor = new SigninInteractor(this);
    }

    @Override
    public void attemptSignin(String email, String password) {
        if(view != null) {
            view.setEnabledView(false);
            view.displayLoader(true);
        }
        interactor.performSignin(email, password);
    }

    @Override
    public boolean isValidForm(String email, String password) {
        boolean isValid = true;
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
            view.displayEmailError("Format email incorrect");
        }
        else if(password.length() <= 4) {
            isValid = false;
            view.displayPasswordError("Format password incorrect");
        }
        return isValid;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.onNavigateHome();
        }
    }

    @Override
    public void onError() {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.displaySigninError("Error authentication");
        }
    }
}
