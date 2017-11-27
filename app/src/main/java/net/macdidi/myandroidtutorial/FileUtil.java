package net.macdidi.myandroidtutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    //資料庫目前有的項目
    private static String titleString[] = {"單號:","序號:","客戶單號:","客戶簡稱:","訂單數量:","單位:","品號:","型號:","尺寸:","閥號:","試壓等級:","部門名稱:"};

    // 應用程式儲存檔案的目錄
    public static final String APP_DIR = "androidtutorial";

    // 外部儲存設備是否可寫入
    public static boolean isExternalStorageWritable() {
        // 取得目前外部儲存設備的狀態
        String state = Environment.getExternalStorageState();

        // 判斷是否可寫入
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }

        return false;
    }

    // 外部儲存設備是否可讀取
    public static boolean isExternalStorageReadable() {
        // 取得目前外部儲存設備的狀態
        String state = Environment.getExternalStorageState();

        // 判斷是否可讀取
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }

        return false;
    }

    // 建立並傳回在公用相簿下參數指定的路徑
    public static File getPublicAlbumStorageDir(String albumName) {
        // 取得公用的照片路徑
        File pictures = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        // 準備在照片路徑下建立一個指定的路徑
        File file = new File(pictures, albumName);

        // 如果建立路徑不成功
        if (!file.mkdirs()) {
            Log.e("getAlbumStorageDir", "Directory not created");
        }

        return file;
    }

    // 建立並傳回在應用程式專用相簿下參數指定的路徑
    public static File getAlbumStorageDir(Context context, String albumName) {
        // 取得應用程式專用的照片路徑
        File pictures = context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES);
        // 準備在照片路徑下建立一個指定的路徑
        File file = new File(pictures, albumName);

        // 如果建立路徑不成功
        if (!file.mkdirs()) {
            Log.e("getAlbumStorageDir", "Directory not created");
        }

        return file;
    }

    // 建立並傳回外部儲存媒體參數指定的路徑
    public static File getExternalStorageDir(String dir) {
        File result = new File(
                Environment.getExternalStorageDirectory(), dir);

        if (!isExternalStorageWritable()) {
            return null;
        }

        if (!result.exists() && !result.mkdirs()) {
            return null;
        }

        return result;
    }

    // 讀取指定的照片檔案名稱設定給ImageView元件

    //new
    public static void fileToImageView(String fileName, ImageView imageView) {
        /*if (new File(fileName).exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(fileName);
            Bitmap bitmap2 = null;
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas1 = new Canvas(bitmap2);
            Paint paint1 = new Paint();
            paint1.setColor(Color.YELLOW);
            paint1.setTextSize(200);//設定字體大小
            canvas1.drawBitmap(bitmap, 0, 0, paint1);
            canvas1.drawText("移動滑鼠，捕捉座標", 30, 200, paint1);
            imageView.setImageBitmap(bitmap2);


            int DotLocation = fileName.lastIndexOf(".");
            int stringLength = fileName.length();
            String findSlashIntegerNumString = fileName.substring(0, DotLocation);
            findSlashIntegerNumString = findSlashIntegerNumString + "_Drawtext" + fileName.substring(DotLocation, stringLength);
            //String tmp = "/sdcard/demo/takepicture.jpg";
            try {
                System.out.println("FileUtil__" + findSlashIntegerNumString + "__System.out.println");
                FileOutputStream fOut = new FileOutputStream(findSlashIntegerNumString);
                //options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%;
                int options = 100;
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            } catch (FileNotFoundException e) {
                System.out.println("FileUtil__FileNotFoundException**********__System.out.println");
                e.printStackTrace();
            }
        }
        else {
            Log.e("fileToImageView", fileName + " not found.");
        }
        */


        int DotLocation = fileName.lastIndexOf(".");
        int slopeLocation = fileName.lastIndexOf("/");
        int stringLength = fileName.length();
        String myPath = fileName.substring(0, slopeLocation);
        String findSlashIntegerNumString = fileName.substring(slopeLocation, DotLocation);
        //findSlashIntegerNumString = findSlashIntegerNumString + "_Drawtext" + fileName.substring(DotLocation, stringLength);
        findSlashIntegerNumString = myPath + returnFolderName() + findSlashIntegerNumString + "_Drawtext" + ".png";
        ///storage/sdcard/androidtutorial/FV-41007 -20170618_153038-1  管路拆裝前 _Drawtext.png
        System.out.println("FFFFFileUtil___fileToImage" + findSlashIntegerNumString + "|System.out.println");
        //if (new File(fileName).exists() && !(new File(findSlashIntegerNumString).exists()) ) {
        if (new File(fileName).exists()) {
            System.out.println("FileUtil___fileToImageView new File(fileName) is exists() |System.out.println");
            Bitmap bitmap = BitmapFactory.decodeFile(fileName);
            Bitmap bitmap2 = null;
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas1 = new Canvas(bitmap2);
            Paint paint1 = new Paint();
            paint1.setColor(Color.YELLOW);
            paint1.setTextSize(200);//設定字體大小
            canvas1.drawBitmap(bitmap, 0, 0, paint1);
/*
            int addPixel = 250;
            String drawVal;
            for(int i=0;i<titleString.length;i++)
            {
                //System.out.println("FileUtil__fileToImageView" + ItemActivity.json.getMyOrderno() + "__System.out.println");
                if(ItemActivity.val == null)
                    break;
                drawVal = titleString[i];
                switch(i)
                {
                    case 0:drawVal += ItemActivity.json.getMyOrderno();
                        break;
                    case 1:drawVal += ItemActivity.json.getMyOrdersn();
                        break;
                    case 2:drawVal += ItemActivity.json.getMyCaseNumber();
                        break;
                    case 3:drawVal += ItemActivity.json.getMyCustomer();
                        break;
                    case 4:drawVal += ItemActivity.json.getMyQty();
                        break;
                    case 5:drawVal += ItemActivity.json.getMyUnit();
                        break;
                    case 6:drawVal += ItemActivity.json.getMyProduct();
                        break;
                    case 7:drawVal += ItemActivity.json.getMyProductType();
                        break;
                    case 8:drawVal += ItemActivity.json.getMyProductSize();
                        break;
                    case 9:drawVal += ItemActivity.json.getMyTagno();
                        break;
                    case 10:drawVal += ItemActivity.json.getMyTestPressure();
                        break;
                    case 11:drawVal += ItemActivity.json.getMyDept();
                        break;
                    case 12:drawVal += ItemActivity.json.getMyWorkItem();
                        break;
                }
                canvas1.drawText(drawVal, 30, addPixel, paint1);
                addPixel = addPixel + 200;
            }*/
            String thisVal = null;
            thisVal = ItemActivity.content_text.getText().toString();
            System.out.println("FileUtil__fileToImageView" + thisVal + "__System.out.println");

            String drawTextSting;
            int i;
            int addPixel = 250;
            int indexNum[] = new int[30];
            indexNum[1] = 0;
            drawTextSting = "";

            if(thisVal != null) {
                for (i = 0; i <= 13; i++) {
                    indexNum[i] = 0;
                }

                //indexNum[1] = thisVal.indexOf(" ", indexNum[0]+1);
                //indexNum[2] = thisVal.indexOf(" ", indexNum[1]+1);

                for (i = 0; thisVal.indexOf("\n", indexNum[i] + 1) != -1; i++) {
                    if(thisVal.indexOf("\n", indexNum[i] + 1) != -1) {
                        indexNum[i + 1] = thisVal.indexOf("\n", indexNum[i] + 1);
                        System.out.println("thisVal = " + thisVal.charAt(indexNum[i + 1]) + "__System.out.println");
                    }
                    System.out.println("***__i = " + Integer.toString(i) + ",,  indexNum[i + 1] = " +indexNum[i + 1] + "__System.out.println");
                }
                i--;
                System.out.println("+++__i = " + Integer.toString(i) + "__System.out.println");

                System.out.println("indexNum[i+1] = " + Integer.toString(indexNum[i+1]));
                System.out.println("thisVal.length() = " + Integer.toString(thisVal.length()));
                if(indexNum[i+1] != thisVal.length() - 1){
                    i++;
                    System.out.println("if(indexNum[i] != thisVal.length() - 1)___true");
                    indexNum[i + 1] = thisVal.length() ;

                }


                int j = 0;
                for (j = 0; j <= i; j++) {
                    System.out.println("FileUtil__fileToImageView___j = " + Integer.toString(j) + "__System.out.println");
                    if (j == 0) {
                        drawTextSting = thisVal.substring(indexNum[j], indexNum[j + 1]);
                    } else if (j <= i) {
                        //System.out.println("FileUtil__fileToImageView___j = " + thisVal.substring(indexNum[j] + 2, indexNum[j + 1]) + "__System.out.println");
                        drawTextSting = thisVal.substring(indexNum[j] + 1, indexNum[j + 1]);
                    }
                    //System.out.println("FileUtil__fileToImageView___i = " + Integer.toString(i) + "__System.out.println");
                    canvas1.drawText(drawTextSting, 50, addPixel, paint1);
                    //System.out.println("FileUtil__fileToImageView___addPixel = addPixel + 200;");
                    addPixel = addPixel + 200;
                    System.out.println("FileUtil__fileToImageView___j = " + Integer.toString(j) + "__System.out.println");
                }
            }
            imageView.setImageBitmap(bitmap2);

            //String tmp = "/sdcard/demo/takepicture.jpg";
            try {
                System.out.println("FileUtil__" + findSlashIntegerNumString + "__System.out.println");
                FileOutputStream fOut = new FileOutputStream(findSlashIntegerNumString);
                //options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%;
                int options = 100;
                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, fOut); //圖片存到手機記憶體
            } catch (FileNotFoundException e) {
                System.out.println("FileUtil__FileNotFoundException**********__System.out.println");
                e.printStackTrace();
            }
        }
        /*
        else if(new File(fileName).exists() && new File(findSlashIntegerNumString).exists() ) {
            Bitmap bitmap = BitmapFactory.decodeFile(findSlashIntegerNumString);
            imageView.setImageBitmap(bitmap);
        }*/
        else {
            Log.e("fileToImageView", fileName + " not found.");
        }
    }

    private static int idNum[] = new int[2];
    private static String idName = "";
    private static int serialNum[] = new int[2];
    private static String serialName = "";
    private static int departNum[] = new int[2];
    private static String departName = "";
    private static int workNum[] = new int[2];
    private static String workName = "";
    private static int clientNum[] = new int[2];
    private static String clientName = "";
    private static int valveNum[] = new int[2];
    private static String valveName = "";

    // 產生唯一的檔案名稱
    public static String getUniqueFileName() {
        System.out.println("FileUtil__getUniqueFileName__System.out.println");
        // 使用年月日_時分秒格式為檔案名稱
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //String ordernoString = ItemActivity.orderno.toString();

        String thisVal = null;
        thisVal = ItemActivity.content_text.getText().toString();


        idNum[0] = thisVal.indexOf("單號:") + 3;
        if(idNum[0] != -1 ) {
            if(thisVal.indexOf("\n", idNum[0]) != -1)
                idNum[1] = thisVal.indexOf("\n", idNum[0]);
            else
                idNum[1] = thisVal.length();
            idName = thisVal.substring(idNum[0], idNum[1]);
        }

        valveNum[0] = thisVal.indexOf("閥號:") + 3;
        if(valveNum[0] != -1 ) {
            if(thisVal.indexOf("\n", valveNum[0]) != -1)
                valveNum[1] = thisVal.indexOf("\n", valveNum[0]);
            else
                valveNum[1] = thisVal.length();
            valveName = thisVal.substring(valveNum[0], valveNum[1]);
        }

        serialNum[0] = thisVal.indexOf("序號:") + 3;
        if(serialNum[0] != -1 ) {
            if(thisVal.indexOf("\n", serialNum[0]) != -1)
                serialNum[1] = thisVal.indexOf("\n", serialNum[0]);
            else
                serialNum[1] = thisVal.length();
            serialName = thisVal.substring(serialNum[0], serialNum[1]);
        }

        workNum[0] = thisVal.indexOf("作業項目:") + 5;
        if(workNum[0] != -1 ) {
            if(thisVal.indexOf("\n", departNum[0]) != -1)
                workNum[1] = thisVal.indexOf("\n", workNum[0]);
            else
                workNum[1] = thisVal.length();
            workName = thisVal.substring(workNum[0], workNum[1]);
        }

        departNum[0] = thisVal.indexOf("部門名稱:") + 5;
        if(departNum[0] != -1 ) {
            if(thisVal.indexOf("\n", departNum[0]) != -1)
                departNum[1] = thisVal.indexOf("\n", departNum[0]);
            else
                departNum[1] = thisVal.length();
            departName = thisVal.substring(departNum[0], departNum[1]);
        }

        clientNum[0] = thisVal.indexOf("客戶簡稱:") + 5;
        if(clientNum[0] != -1 ) {
            if(thisVal.indexOf("\n", clientNum[0]) != -1)
                clientNum[1] = thisVal.indexOf("\n", clientNum[0]);
            else
                clientNum[1] = thisVal.length();
            clientName = thisVal.substring(clientNum[0], clientNum[1]);
        }
        String returnFileString;
        if(serialName != "")
            returnFileString = valveName + "-" + sdf.format(new Date()) + "-" + serialName + " " + departName + AboutActivity.employee.toString();
        else //(serialName == "")
            returnFileString = valveName + "-" + sdf.format(new Date()) + " " + departName + AboutActivity.employee.toString();
        //returnString = ItemActivity.orderno.getText().toString() +"-"+ Integer.toString(ItemActivity.button2_textNum) + "-" + Integer.toString(ItemActivity.pictureNum) + "-" + sdf.format(new Date());
        System.out.println("FileUtil__getUniqueFileName__returnString = "+ returnFileString +"___System.out.println");


        String year = new SimpleDateFormat("yyyy").format(new Date()).toString();
        year = Integer.toString(Integer.valueOf(year)-1911);

        //String returnfolderString = FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + "/" + departName + clientName + "-" + idName + "/" + year + "年度" + "/";
        String returnfolderString = FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + "/" + departName + clientName + "_" + idName + "/";
        System.out.println("FileUtil__File folder = new File(returnfolderString);*****__System.out.println");


        File folder = new File(returnfolderString);
        if(folder.exists() == false){// 如果資料夾不存在，創建一個
            System.out.println("FileUtil__folder.exists() == false*****__System.out.println");
            try {
                folder.mkdirs(); //這裡要用.mkdirs()方法，父類資料夾不存在時，可以自動創建
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } //而如果用.mkdir()方法則不會自動創建


        returnfolderString = returnfolderString + year + "年度" + "/" ;
        folder = new File(returnfolderString);
        if(!folder.exists()) {// 如果資料夾不存在，創建一個
            try {
                folder.mkdir(); //這裡要用.mkdirs()方法，父類資料夾不存在時，可以自動創建
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } //而如果用.mkdir()方法則不會自動創建


        return returnFileString;
        //return ("台中" + ItemActivity.orderno.getText().toString() +"-"+ Integer.toString(ItemActivity.button2_textNum) + "-" + Integer.toString(ItemActivity.pictureNum) + "-" + sdf.format(new Date()));
    }
    public static String returnFolderName(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date()).toString();
        System.out.println("FileUtil__getUniqueFileName__returnString = "+ year +"___System.out.println");
        year = Integer.toString(Integer.valueOf(year)-1911);
        System.out.println("FileUtil__getUniqueFileName__returnString = "+ year +"___System.out.println");
        //String returnString = FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + "/" + departName + clientName + "_" + idName + "/" + year + "年度" + "/";
        //File returnFile = new File(FileUtil.getExternalStorageDir(FileUtil.APP_DIR) + "/" + departName + clientName + "_" + idName + "/" + year + "年度" + "/");

        String returnfolderString = "/" + departName + clientName + "_" + idName + "/";
        returnfolderString = returnfolderString + year + "年度";

        return returnfolderString;
    }
}