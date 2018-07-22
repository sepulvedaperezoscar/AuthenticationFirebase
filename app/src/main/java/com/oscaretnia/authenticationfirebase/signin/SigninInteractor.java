package com.oscaretnia.authenticationfirebase.signin;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninInteractor implements SigninContract.Interactor {

    private SigninContract.CompleteListener listener;
    private FirebaseAuth auth;

    public SigninInteractor(SigninContract.CompleteListener listener) {
        this.listener = listener;
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void performSignin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            listener.onSuccess();
                        else
                            listener.onError();
                    }

                });
    }
}
