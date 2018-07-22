package com.oscaretnia.authenticationfirebase.signin;

public interface SigninContract {

    interface View {
        void onNavigateHome();
        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displaySigninError(String error);
        void displayLoader(boolean loader);
        void setEnabledView(boolean enable);
    }

    interface Presenter {
        void attemptSignin(String email, String password);
        boolean isValidForm(String email, String password);
        void onDestroy();
    }

    interface Interactor {
        void performSignin(String email, String password);
    }

    interface CompleteListener {
        void onSuccess();
        void onError();
    }

}
