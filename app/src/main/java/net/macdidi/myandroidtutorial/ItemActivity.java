package net.macdidi.myandroidtutorial;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ItemActivity extends AppCompatActivity {

    private EditText title_text;
    public static EditText content_text;

    // 啟動功能用的請求代碼
    private static final int START_CAMERA = 0;
    private static final int START_RECORD = 1;
    private static final int START_LOCATION = 2;
    private static final int START_ALARM = 3;
    private static final int START_COLOR = 4;

    // 記事物件
    private Item item;

    // 檔案名稱
    private String fileName;
    // 照片
    private ImageView picture;
    // 寫入外部儲存設備授權請求代碼
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 100;
    // 錄音設備授權請求代碼
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 101;
    // 錄音檔案名稱
    private String recFileName;

    // 定位設備授權請求代碼
    private static final int REQUEST_FINE_LOCATION_PERMISSION = 102;

    //20170506
    private Button button2;
    public static int button2_textNum = 1;

    //20170515
    public static int pictureNum = 0;

    //程駿DER
    Timer time = new Timer();
    Handler handler = new Handler();
    QueryMySql Q = new QueryMySql();
    public static EditText orderno,ordersn;//單號跟型號
    static EditText text;
    Gson gson = new Gson();
    static MyJson json;
    static String val = new String();
    String Q_string;
    TextView resMsg;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ItemActivity__onCreate" + "__System.out.println");
        setContentView(R.layout.activity_item);

        processViews();

        // 取得Intent物件
        Intent intent = getIntent();
        // 讀取Action名稱
        String action = intent.getAction();

        // 如果是修改記事
        if (action.equals("net.macdidi.myandroidtutorial.EDIT_ITEM")) {
            // 接收記事物件與設定標題、內容
            item = (Item) intent.getExtras().getSerializable(
                    "net.macdidi.myandroidtutorial.Item");
            title_text.setText(item.getTitle());
            content_text.setText(item.getContent());
        }
        // 新增記事
        else {
            item = new Item();
        }
        //程駿DER
        text = (EditText)findViewById(R.id.content_text);
        orderno = (EditText)findViewById(R.id.orderno);
        upload = (Button) findViewById(R.id.upload);
        resMsg = (TextView) findViewById(R.id.msg);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileUpload mFileUpload = new FileUpload();
                        //System.out.println("++++"+Environment.getExternalStorageDirectory().getAbsolutePath());
                        mFileUpload.setOnFileUploadListener(new FileUpload.OnFileUploadListener() {
                            @Override
                            public void onFileUploadSuccess(final String msg) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        resMsg.setText(msg);
                                    }
                                });

                            }

                            @Override
                            public void onFileUploadFail(final String msg) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        resMsg.setText(msg);
                                    }
                                });
                            }
                        });

                        //System.out.println("+" + FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + "/" + item.getFileName() + ".png");
                        System.out.println("+++" + FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + onResumeConfigFileName("P", "_Drawtext.png").getName().toString());
                        mFileUpload.doFileUpload(FileUtil.getExternalStorageDir(FileUtil.APP_DIR) +"/"+ onResumeConfigFileName("P", "_Drawtext.png").getName().toString());

                    }
                }).start();
            }
        });

        //20170506
        button2 = (Button)findViewById(R.id.button2);
    }

    //程駿DER
    public void Query(View v) {
        System.out.println("ItemActivity__Query");
        orderno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2_textNum = 1;
                button2.setText(Integer.toString(button2_textNum));
            }

        });
        Q.setNumber(orderno.getText().toString(), Integer.toString(button2_textNum));
        Thread thread = new Thread(Q.runnable);
        thread.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("ItemActivity__onActivityResult" + "__System.out.println");
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case START_CAMERA:
                    // 設定照片檔案名稱
                    item.setFileName(fileName);
                    break;
                case START_RECORD:
                    // 設定錄音檔案名稱
                    item.setRecFileName(recFileName);
                    break;
                case START_LOCATION:
                    // 讀取與設定座標
                    double lat = data.getDoubleExtra("lat", 0.0);
                    double lng = data.getDoubleExtra("lng", 0.0);
                    item.setLatitude(lat);
                    item.setLongitude(lng);
                    break;
                case START_ALARM:
                    break;
                // 設定顏色
                case START_COLOR:
                    int colorId = data.getIntExtra(
                            "colorId", Colors.LIGHTGREY.parseColor());
                    item.setColor(getColors(colorId));
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("ItemActivity__onResume" + "__System.out.println");
        // 如果有檔案名稱
        if (item.getFileName() != null && item.getFileName().length() > 0) {
            // 照片檔案物件
            File file = onResumeConfigFileName("P", ".jpg");

            // 如果照片檔案存在
            if (file.exists()) {
                // 顯示照片元件
                picture.setVisibility(View.VISIBLE);
                // 設定照片
                String fileName = file.getAbsolutePath();
                FileUtil.fileToImageView(fileName, picture);
                //int DotLocation = fileName.lastIndexOf(".");
                //int stringLength = fileName.length();
                //String findSlashIntegerNumString = fileName.substring(0, DotLocation);
                //findSlashIntegerNumString = findSlashIntegerNumString + "_Drawtext" + fileName.substring(DotLocation, stringLength);


                //Context context = this;
                //String text = "顯示Toast訊息";
                //int duration = Toast.LENGTH_LONG;
                //Toast toast = Toast.makeText(context, findSlashIntegerNumString, duration);
                //設定toast要改變的訊息
                //toast.setText("改變Toast訊息");
                //使用show()把Toast顯示出來
                //toast.show();

                System.out.println("ItemActivity__onResume" + "__System.out.println");
            }
        }
    }

    public static Colors getColors(int color) {
        Colors result = Colors.LIGHTGREY;

        if (color == Colors.BLUE.parseColor()) {
            result = Colors.BLUE;
        }
        else if (color == Colors.PURPLE.parseColor()) {
            result = Colors.PURPLE;
        }
        else if (color == Colors.GREEN.parseColor()) {
            result = Colors.GREEN;
        }
        else if (color == Colors.ORANGE.parseColor()) {
            result = Colors.ORANGE;
        }
        else if (color == Colors.RED.parseColor()) {
            result = Colors.RED;
        }

        return result;
    }

    private void processViews() {
        title_text = (EditText) findViewById(R.id.title_text);
        content_text = (EditText) findViewById(R.id.content_text);

        // 取得顯示照片的ImageView元件
        picture = (ImageView) findViewById(R.id.picture);
    }

    // 點擊確定與取消按鈕都會呼叫這個方法
    public void onSubmit(View view) {
        // 確定按鈕
        if (view.getId() == R.id.ok_teim) {
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();

            item.setTitle(titleText);
            item.setContent(contentText);

            if (getIntent().getAction().equals(
                    "net.macdidi.myandroidtutorial.EDIT_ITEM")) {
                item.setLastModify(new Date().getTime());
            }
            // 新增記事
            else {
                item.setDatetime(new Date().getTime());
                // 建立SharedPreferences物件
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(this);
                // 讀取設定的預設顏色
                int color = sharedPreferences.getInt("DEFAULT_COLOR", -1);
                item.setColor(getColors(color));
            }

            Intent result = getIntent();
            result.putExtra("net.macdidi.myandroidtutorial.Item", item);
            setResult(Activity.RESULT_OK, result);
        }

        // 結束
        finish();
    }

    public void clickFunction(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.take_picture:
                // 讀取與處理寫入外部儲存設備授權請求
                requestStoragePermission();
                break;
            case R.id.record_sound:
                // 讀取與處理錄音設備授權請求
                requestRecordPermission();
                break;
            case R.id.set_location:
                // 讀取與處理定位設備授權請求
                requestLocationPermission();
                break;
            case R.id.set_alarm:
                break;
            case R.id.select_color:
                // 啟動設定顏色的Activity元件
                startActivityForResult(
                        new Intent(this, ColorActivity.class), START_COLOR);
                break;
        }

    }

    // 啟動地圖與定位元件
    private void processLocation() {
        // 啟動地圖元件用的Intent物件
        Intent intentMap = new Intent(this, MapsActivity.class);

        // 設定儲存的座標
        intentMap.putExtra("lat", item.getLatitude());
        intentMap.putExtra("lng", item.getLongitude());
        intentMap.putExtra("title", item.getTitle());
        intentMap.putExtra("datetime", item.getLocaleDatetime());

        // 啟動地圖元件
        startActivityForResult(intentMap, START_LOCATION);
    }

    // 拍攝照片
    private void takePicture() {
        pictureNum++;
        System.out.println("ItemActivity__takePicture___pictureNum" +Integer.toString(pictureNum) + "__System.out.println");
        // 啟動相機元件用的Intent物件
        Intent intentCamera =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 照片檔案名稱
        File pictureFile = takePictureConfigFileName("P", ".jpg");
        System.out.println("ItemActivity__takePicture___pictureNum" +pictureFile.getName().toString() + "__System.out.println");
        Uri uri = Uri.fromFile(pictureFile);
        // 設定檔案名稱
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // 啟動相機元件
        startActivityForResult(intentCamera, START_CAMERA);
    }

    private File configFileName(String prefix, String extension) {
        System.out.println("ItemActivity__configFileName" + "__System.out.println");
        // 如果記事資料已經有檔案名稱
        if (item.getFileName() != null && item.getFileName().length() > 0) {
            fileName = item.getFileName();
            System.out.println("ItemActivity__configFileName___if___fileName: "+ fileName + "|System.out.println");
        }
        // 產生檔案名稱
        else {
            fileName = FileUtil.getUniqueFileName();
            item.setFileName(fileName);
            System.out.println("ItemActivity__configFileName___else___fileName: "+ fileName + "|System.out.println");
        }

        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + fileName + extension);
    }

    private File onResumeConfigFileName(String prefix, String extension) {
        fileName = item.getFileName();
        System.out.println("ItemActivity__onResumeConfigFileName__if___fileName: " + fileName + "|System.out.println");

        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + fileName + extension);
    }

    private File takePictureConfigFileName(String prefix, String extension) {
        // 產生檔案名稱
        fileName = FileUtil.getUniqueFileName();
        System.out.println("ItemActivity__takePictureConfigFileName___else___fileName: " + fileName + "|System.out.println");
        item.setFileName(fileName);
        System.out.println("ItemActivity__takePictureConfigFileName___else___fileName: " + item.getFileName() + "|System.out.println");


        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + fileName + extension);
    }
    // 錄音與播放
    public void processRecord() {
        // 錄音檔案名稱
        final File recordFile = configRecFileName("R", ".mp3");

        // 如果已經有錄音檔，詢問播放或重新錄製
        if (recordFile.exists()) {
            // 詢問播放還是重新錄製的對話框
            AlertDialog.Builder d = new AlertDialog.Builder(this);

            d.setTitle(R.string.title_record)
                    .setCancelable(false);
            d.setPositiveButton(R.string.record_play,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 播放
                            Intent playIntent = new Intent(
                                    ItemActivity.this, PlayActivity.class);
                            playIntent.putExtra("fileName",
                                    recordFile.getAbsolutePath());
                            startActivity(playIntent);
                        }
                    });
            d.setNeutralButton(R.string.record_new,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // 重新錄音
                            Intent recordIntent = new Intent(ItemActivity.this, RecordActivity.class);
                            recordIntent.putExtra("fileName", recordFile.getAbsolutePath());
                            startActivityForResult(recordIntent, START_RECORD);
                        }
                    });
            d.setNegativeButton(android.R.string.cancel, null);

            // 顯示對話框
            d.show();
        }
        // 如果沒有錄音檔，啟動錄音元件
        else {
            // 錄音
            Intent recordIntent = new Intent(this, RecordActivity.class);
            recordIntent.putExtra("fileName", recordFile.getAbsolutePath());
            startActivityForResult(recordIntent, START_RECORD);
        }
    }

    private File configRecFileName(String prefix, String extension) {
        // 如果記事資料已經有檔案名稱
        if (item.getRecFileName() != null && item.getRecFileName().length() > 0) {
            recFileName = item.getRecFileName();
        }
        // 產生檔案名稱
        else {
            recFileName = FileUtil.getUniqueFileName();
        }

        return new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR),
                prefix + recFileName + extension);
    }

    // 讀取與處理寫入外部儲存設備授權請求
    private void requestStoragePermission() {
        // 如果裝置版本是6.0（包含）以上
        /*if (Build.VERSION.SDK_INT &gt;= Build.VERSION_CODES.M) {
            // 取得授權狀態，參數是請求授權的名稱
            int hasPermission = checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            // 如果未授權
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                // 請求授權
                //     第一個參數是請求授權的名稱
                //     第二個參數是請求代碼
                requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION);
                return;
            }
        }*/
        
        // 如果裝置版本是6.0以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權，
        // 拍攝照片
        takePicture();
    }

    // 讀取與處理錄音設備授權請求
    private void requestRecordPermission() {
        // 如果裝置版本是6.0（包含）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 取得授權狀態，參數是請求授權的名稱
            int hasPermission = checkSelfPermission(
                    Manifest.permission.RECORD_AUDIO);

            // 如果未授權
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                // 請求授權
                //     第一個參數是請求授權的名稱
                //     第二個參數是請求代碼
                requestPermissions(
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_AUDIO_PERMISSION);
                return;
            }
        }

        // 如果裝置版本是6.0以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權，        
        // 錄音或播放
        processRecord();
    }
    
    // 讀取與處理定位設備授權請求
    private void requestLocationPermission() {
        // 如果裝置版本是6.0（包含）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 取得授權狀態，參數是請求授權的名稱
            int hasPermission = checkSelfPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION);

            // 如果未授權
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                // 請求授權
                //     第一個參數是請求授權的名稱
                //     第二個參數是請求代碼
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_FINE_LOCATION_PERMISSION);
                return;
            }
        }

        // 如果裝置版本是6.0以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權，        
        // 啟動地圖與定位元件
        processLocation();
    }

    // 覆寫請求授權後執行的方法
    //     第一個參數是請求代碼
    //     第二個參數是請求授權的名稱
    //     第三個參數是請求授權的結果，PERMISSION_GRANTED或PERMISSION_DENIED
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        // 如果是寫入外部儲存設備授權請求
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION) {
            // 如果在授權請求選擇「允許」
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 拍攝照片
                takePicture();

            }
            // 如果在授權請求選擇「拒絕」
            else {
                // 顯示沒有授權的訊息
                Toast.makeText(this, R.string.write_external_storage_denied,
                        Toast.LENGTH_SHORT).show();
            }
        }
        // 如果是錄音設備授權請求
        else if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            // 如果在授權請求選擇「允許」
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 錄音或播放
                processRecord();
            }
            // 如果在授權請求選擇「拒絕」
            else {
                // 顯示沒有授權的訊息
                Toast.makeText(this, R.string.record_audio_denied,
                        Toast.LENGTH_SHORT).show();
            }
        }
        // 如果是定位設備授權請求
        else if (requestCode == REQUEST_FINE_LOCATION_PERMISSION) {
            // 如果在授權請求選擇「允許」
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 啟動地圖與定位元件
                processLocation();
            }
            // 如果在授權請求選擇「拒絕」
            else {
                // 顯示沒有授權的訊息
                Toast.makeText(this, R.string.write_external_storage_denied,
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void buttonOnClick(View v) {
        int myNumOfOrder;
        try{
            myNumOfOrder = Integer.parseInt(ItemActivity.json.getMyNumOfOrdersn());
        }
        catch(Exception e){
            myNumOfOrder = 1;
        }
        if(button2_textNum < myNumOfOrder) {
            button2_textNum++;
            button2.setText(Integer.toString(button2_textNum));
        }

        else if(button2_textNum >= myNumOfOrder) {
            button2_textNum = 1;
            button2.setText(Integer.toString(button2_textNum));
        }
        pictureNum = 0;
    }

}
