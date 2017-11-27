package net.macdidi.myandroidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class AboutActivity extends Activity {

    static String employee = "0000";
    EditText employeeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消元件的應用程式標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);

    }

    // 結束按鈕
    public void clickOk(View view) {
        employeeNum = (EditText)findViewById(R.id.editText);
        employee = employeeNum.getText().toString();
        MainActivity.show_app_name.setText("Android Camera" + AboutActivity.employee);
        // 呼叫這個方法結束Activity元件
        finish();
    }

}
