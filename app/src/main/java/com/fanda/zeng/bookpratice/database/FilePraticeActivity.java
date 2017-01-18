package com.fanda.zeng.bookpratice.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by zfd on 2017/1/18 0018.
 * 文件存储与读取练习
 */

public class FilePraticeActivity extends BaseActivity {

    private EditText et_file_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_pratice);
        et_file_content = (EditText) findViewById(R.id.et_file_content);

        String fileContent = loadFileContent();
        if (!TextUtils.isEmpty(fileContent)) {
            et_file_content.setText(fileContent);
            et_file_content.setSelection(fileContent.length());
        }
    }

    /**
     * 读取文件中的数据
     */
    private String loadFileContent() {
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("filePratice");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String fileContent = et_file_content.getText().toString().trim();
        if (!TextUtils.isEmpty(fileContent)) {
            save(fileContent);
        }
    }

    /**
     * 保存数据到文件
     *
     * @param fileContent 文件内容
     */
    private void save(String fileContent) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("filePratice", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
