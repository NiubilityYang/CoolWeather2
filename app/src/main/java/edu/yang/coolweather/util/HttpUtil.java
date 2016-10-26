package edu.yang.coolweather.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/*import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;*/

/**
 * Created by 69462 on 2016/10/23.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackLisener lisener){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(address);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity,"utf-8");
                        if (lisener != null){
                            lisener.onFinish(response.toString());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    if (lisener != null){
                        lisener.onError(e);
                    }
                }

            }
        }).start();
    }

/*    public static void sendHttpRequest(final String address,final HttpCallbackLisener lisener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine() )!= null){
                        response.append(line);
                    }
                    if (lisener != null){
                        lisener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG", "Exception: "+Log.getStackTraceString(e));
                    if (lisener != null){
                        lisener.onError(e);
                    }
                } finally {

                    if (connection != null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }*/
}
