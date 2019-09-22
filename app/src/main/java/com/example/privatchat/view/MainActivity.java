package com.example.privatchat.view;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.privatchat.R;
import com.example.privatchat.model.GmailLogin;
import com.example.privatchat.viewmodel.MyViewModel;
import com.jakewharton.rxbinding2.widget.RxTextView;


import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    EditText login;
    EditText password;
    Button logInButton;
    Observable<Boolean> observable;
    TextView textView;

    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    ActionBar.LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.et_login);
        password = findViewById(R.id.et_password);
        logInButton = findViewById(R.id.logIn);


        Observable<String> nameObservable = RxTextView.textChanges(login).skip(1).map(charSequence -> charSequence.toString());
        Observable<String> passwordObservable = RxTextView.textChanges(password).skip(1).map(charSequence -> charSequence.toString());
        observable = Observable.combineLatest(nameObservable, passwordObservable, (s, s2) -> isValidForm(s, s2));
        observable.subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean)
                    logInButton.setEnabled(true);
                else
                    logInButton.setEnabled(false);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);//переходим во вьюмодел
        getLifecycle().addObserver(myViewModel);

        logInButton.setEnabled(false);
    }

    public boolean isValidForm(String name, String passwords) {
        boolean validName = !name.isEmpty() && name.equals("getGoogleAccounts");
        if (!validName) {
            login.setError("Please enter valid name");
        }
        boolean validPass = !passwords.isEmpty() && passwords.equals("getGoogleAccounts");
        if (!validPass) {
            password.setError("Incorrect password");
        }
        return validName && validPass;
    }

    @Override
    protected void onStop() {
        super.onStop();
        password.setText("");
    }

    public void getDataUser(View view) {
        String LDAP = login.getText().toString();
        myViewModel.getUserldap(LDAP, this);

    }


    public void onClick(View v) {
        GmailLogin gmailLogin = new GmailLogin(this, v);
        String[] accounts = gmailLogin.getGoogleAccounts();
        textView = (TextView) findViewById(R.id.tv_account);

        //textView.setText("123");activity_popup_gmail
        gmailLogin.showPopUpChangeAccount(v, accounts);
    }



}
