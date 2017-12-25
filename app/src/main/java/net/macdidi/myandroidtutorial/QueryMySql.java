package net.macdidi.myandroidtutorial;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static net.macdidi.myandroidtutorial.ItemActivity.json;

/**
 * Created by T on 2017/5/13.
 */

public class QueryMySql{


    String url = "http://192.168.1.111/query.php";// 要加上"http://" 否則會連線失敗
    //192.168.0.15 是我電腦的IP
    //140.121.199.158 研究室的IP

    Gson gson = new Gson();
    String orderno,ordersn;
    String val;


    Handler handler_Success = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            val = data.getString("key");//取出key中的字串存入val
            if (val != null) {
                json = gson.fromJson(val, MyJson.class);
                val = "單號:" + json.getMyOrderno() +
                        " \n序號:" + json.getMyOrdersn() +
                        " \n客戶單號:" + json.getMyCaseNumber() +
                        " \n客戶簡稱:" + json.getMyCustomer() +
                        " \n訂單數量:" + json.getMyQty() +
                        " \n單位:" + json.getMyUnit() +
                        " \n品號:" + json.getMyProduct() +
                        " \n型號:" + json.getMyProductType() +
                        " \n尺寸:" + json.getMyProductSize() +
                        " \n閥號:" + json.getMyTagno() +
                        " \n試壓等級:" + json.getMyTestPressure() +
                        " \n部門名稱:" + json.getMyWorkItem() + " \n";
                ItemActivity.text.setText(val);
            }
        }
    };

    Handler handler_Error = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            val = data.getString("key");
            ItemActivity.text.setText("Error!");
            //Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        }
    };

    Handler handler_Nodata = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            val = data.getString("key");
            ItemActivity.text.setText("Nodata");
            //Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
        }
    };

    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            Message msg = new Message();
            Bundle data = new Bundle();
            msg.setData(data);
            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost(url);//連線到 url網址

                List<NameValuePair> vars=new ArrayList< NameValuePair>();
                vars.add(new BasicNameValuePair("ORDERNO",orderno));
                vars.add(new BasicNameValuePair("ORDERSN",ordersn));
                method.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));

                //接收PHP回傳值
                HttpResponse response = httpclient.execute(method);
                HttpEntity entity = response.getEntity();

                if(entity != null){
                    data.putString("key", EntityUtils.toString(entity));//如果成功將網頁內容存入key
                    handler_Success.sendMessage(msg);
                }
                else{
                    data.putString("key","無資料");
                    handler_Nodata.sendMessage(msg);
                }
            }
            catch(Exception e){
                data.putString("key","連線失敗");
                handler_Error.sendMessage(msg);
            }

        }
    };

    public String getString()
    {

        return handler_Success.toString();
    }

    public void setNumber(String m,String n)
    {
        orderno = m;
        ordersn = n;
    }
}