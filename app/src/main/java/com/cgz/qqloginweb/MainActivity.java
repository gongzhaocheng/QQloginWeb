package com.cgz.qqloginweb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtNumber;
    private EditText mEtPassword;
    private CheckBox mCbRemember;
    private Button mBtLogin;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSp = this.getSharedPreferences("config", MODE_PRIVATE);
        mEtNumber = findViewById(R.id.et_number);
        mEtPassword = findViewById(R.id.et_password);
        mCbRemember = findViewById(R.id.cb_remember);
        mBtLogin = findViewById(R.id.bt_login);

        mBtLogin.setOnClickListener(this);


    }

    /**
     * 登录按钮的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        String number =  mEtNumber.getText().toString().trim();
        String password =  mEtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        } else {
            // 判断是否需要记录用户名和密码
            // 需要将数据保存到sp文件中
             if (mCbRemember.isChecked()) {
                 // 被选中状态，需要记录用户名和密码
                 SharedPreferences.Editor editor = mSp.edit();
                 editor.putString("number",number);
                 editor.putString("password",password);
                 editor.commit(); // 提交数据，类似关闭流，事务

             }
        }
    }
}
