package com.fanda.zeng.bookpratice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "NotificationMainActivity";

    private static final int REQUEST_MAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Tasd id is"+ getTaskId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent ,REQUEST_MAIN);
                return true;
            case R.id.item_remove:
                Toast.makeText(this, getString(R.string.delete), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_query:
                Toast.makeText(this, getString(R.string.query), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_update:
                Toast.makeText(this, getString(R.string.update), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_MAIN:
                if (resultCode == RESULT_OK) {
                    Log.i(TAG,data.getStringExtra("return"));
                }
                break;
        }
    }

    /**
     * 测试Intent传递的数据
     */
    private void testIntentData() {
//        Intent intent = new Intent(this, SecondActivity.class);
//        Intent intent = new Intent("com.fanda.zeng.bookpratice.ACTION_SECOND");
//        intent.addCategory("com.fanda.zeng.bookpratice.MY_CATEGORY");
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.baidu.com"));
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:10086"));
//        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
}
