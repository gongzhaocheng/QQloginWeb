package com.cgz.qqloginweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtNumber;
    private EditText mEtPassword;
    private CheckBox mCbRemember;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtNumber = findViewById(R.id.et_number);
        mEtPassword = findViewById(R.id.et_password);
        mCbRemember = findViewById(R.id.cb_remember);
        mBtLogin = findViewById(R.id.bt_login);



    }
}
