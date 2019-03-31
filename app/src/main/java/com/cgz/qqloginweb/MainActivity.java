package com.cgz.qqloginweb;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STAUS_SUCCESS = 1;
    private static final int STAUS_ERROR = 2;
    private EditText mEtNumber;
    private EditText mEtPassword;
    private CheckBox mCbRemember;
    private Button mBtLogin;
    private SharedPreferences mSp;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STAUS_SUCCESS:

                    break;
                case STAUS_ERROR:
                    Toast.makeText(MainActivity.this,"登录失败，服务器或网络错误",Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };

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
        final String number =  mEtNumber.getText().toString().trim();
        final String password =  mEtPassword.getText().toString().trim();

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
             new Thread(){
                 @Override
                 public void run() {
                     String urlPath = "http://localhost:8080/Day10/LoginServlet?username="+ number +"&password="+password;
                     try {
                         URL url = new URL(urlPath);
                         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                         conn.setRequestMethod("GET");
                         conn.setConnectTimeout(5000);
                         int code = conn.getResponseCode();
                         if (code == 200) {
                             InputStream is = conn.getInputStream();

                         } else {
                             Message msg = Message.obtain();
                             msg.what = STAUS_ERROR;
                             mHandler.sendMessage(msg);
                         }
                     } catch (Exception e) {
                         e.printStackTrace();
                         Message msg = Message.obtain();
                         msg.what = STAUS_ERROR;
                         mHandler.sendMessage(msg);
                     }
                 }
             }.start();
        }
    }
}
