package com.guoguoquan.myaccessibilityservice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartInstallButton;
    private Button mOpenAccessibilityServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mStartInstallButton = (Button) findViewById(R.id.bt_start_install);
        mOpenAccessibilityServiceButton = (Button) findViewById(R.id.bt_open_accessibility_service);

        mStartInstallButton.setOnClickListener(this);
        mOpenAccessibilityServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open_accessibility_service:
                //去打开自助服务
                Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                break;
            case R.id.bt_start_install:
                //开始自动化安装
                //正常的逻辑就是下载然后记录在安装，奴家为了省事儿直接给你放本地安装了哦
                RunAPK(MainActivity.this,"1");
                break;
        }
    }

    // 打开指定apk
    public void RunAPK(Context context, String Name) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + Name + ".apk");
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
            return;
        }
        Intent openFile = getFileIntent(file);
        context.startActivity(openFile);
    }

    public Intent getFileIntent(File file) {
        Uri uri = Uri.fromFile(file);
        String type = "application/vnd.android.package-archive";
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }
}
