package com.oscaretnia.authenticationfirebase.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oscaretnia.authenticationfirebase.MainActivity;
import com.oscaretnia.authenticationfirebase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SigninActivity extends AppCompatActivity implements SigninContract.View {

    @BindView(R.id.email_signin) EditText fieldEmail;
    @BindView(R.id.password_signin) EditText fieldPassword;
    @BindView(R.id.btn_signin) Button btnSignin;
    @BindView(R.id.loader_signin) ProgressBar loaderSignin;

    private SigninContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        presenter = new SigninPresenter(this);
    }

    @OnClick(R.id.btn_signin)
    void onClick(View view) {
        final String email = fieldEmail.getText().toString().trim();
        final String password = fieldPassword.getText().toString().trim();
        if(presenter.isValidForm(email, password)) {
            presenter.attemptSignin(email, password);
        }
    }

    @Override
    public void onNavigateHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", fieldEmail.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    @Override
    public void displayEmailError(String error) {
        fieldEmail.setError(error);
    }

    @Override
    public void displayPasswordError(String error) {
        fieldPassword.setError(error);
    }

    @Override
    public void displaySigninError(String error) {
        showMessage(error);
    }

    @Override
    public void displayLoader(boolean loader) {
        int stateLoader = loader ? View.VISIBLE : View.GONE;
        loaderSignin.setVisibility(stateLoader);
    }

    @Override
    public void setEnabledView(boolean enable) {
        fieldEmail.setEnabled(enable);
        fieldPassword.setEnabled(enable);
        btnSignin.setEnabled(enable);
    }


    @Override
    protected void onDestroy() {
        super .onDestroy();
        presenter.onDestroy();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
