package coolweather.njupt.com.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DragonWarrior on 2016/4/12.
 */
public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;    //创建连接
                try{
                    URL url = new URL(address); //URL对象
                    connection  = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接
                    connection.setRequestMethod("GET"); //使用Get请求
                    connection.setConnectTimeout(8000); //8s
                    connection.setReadTimeout(8000);
                    //输入流
                    InputStream inputStream = connection.getInputStream();  //获取输入流，此时才真正建立链接
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null){
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e){
                    if(listener != null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }finally {
                    if(connection != null){
                        connection.disconnect();    //断开连接
                    }
                }
            }
        }).start();
    }
}
