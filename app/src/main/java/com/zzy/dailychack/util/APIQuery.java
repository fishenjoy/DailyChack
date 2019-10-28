package com.zzy.dailychack.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIQuery extends AsyncTask<String,Void ,String> {
    private  static final int TIME_OUT = 5000;
    private  static final int STREAM_MAX_LENGTH = 10240;

    private TextView textView1 =null;
    private TextView textView2 =null;

    public APIQuery(TextView textView1, TextView textView2) {
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    @Override
    protected String doInBackground(String... strings) {


        HttpURLConnection connection=null;
        InputStream inputStream = null;
        String result =null;

        try{

            URL url = new URL(strings[0]);

            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            if(200 == connection.getResponseCode()){

                inputStream = connection.getInputStream();

                char[] buffer = new char[STREAM_MAX_LENGTH];
                InputStreamReader reader = new InputStreamReader(inputStream,"GBK");
                reader.read(buffer,0,STREAM_MAX_LENGTH);
                result = new String(buffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream !=null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        Log.i("returnResult","======="+result);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        //返回结果切割解析
        String[] resultArray= s.split(",");//var 代码=“证券简称,最新价,涨跌额,涨跌幅,成交量,成交额”

        String fluctuate = resultArray[2];
        textView1.setText(resultArray[1]);
        textView2.setText(resultArray[2]+"   "+resultArray[3]+"%");

        if(fluctuate.startsWith("-")){
            textView1.setTextColor(0xff008000);//绿色
            textView2.setTextColor(0xff008000);//绿色
        }else{
            textView1.setTextColor(0xffff0000);//红色
            textView2.setTextColor(0xffff0000);//红色
        }
    }
}
