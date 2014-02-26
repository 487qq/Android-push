package org.androidpn.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class NetUtil {
    private static final String CHARSET = "UTF-8";
    private static final String TAG = "Http";

    /**
     * @param url
     * @param map
     * @return
     */
    public synchronized static String doPost(String url, Map<String, String> map) {

        Log.e("do post", "url:" + url);

        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpParams httpParams = new BasicHttpParams();

            HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(httpParams, CHARSET);
            HttpProtocolParams.setUseExpectContinue(httpParams, false);

            HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
//            HttpConnectionParams.setSoTimeout(httpParams, 50000);
            httpPost.setParams(httpParams);

            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory
                    .getSocketFactory(), 443));
            ThreadSafeClientConnManager conMgr = new ThreadSafeClientConnManager(
                    httpParams, schReg);
            defaultHttpClient = new DefaultHttpClient(conMgr, httpParams);

            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    Log.e(TAG, "参数名     " + entry.getKey());
                    Log.e(TAG, "参数值     " + entry.getValue());

                    BasicNameValuePair valuePair = new BasicNameValuePair(
                            entry.getKey(), entry.getValue());
                    nameValuePairs.add(valuePair);
                }
            }
            HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs,
                    HTTP.UTF_8);
            httpPost.setEntity(httpEntity);

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse == null) {
                Log.e(TAG, "http response result null");
                return null;
            }
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(httpResponse.getEntity(),
                        HTTP.UTF_8);
                Log.e(TAG, "http response sucessful");
                if (result == null || "".equals(result)) {
                    Log.e(TAG,
                            "request was sucessful, but paser value was null or empty");
                }
                Log.e(TAG, "respnse result:" + result);
                return result;
            } else {
                Log.e(TAG, "http response code:"
                        + httpResponse.getStatusLine().getStatusCode());
                return null;
            }
        } catch (ConnectTimeoutException e) {
            Log.e(TAG, "connection time out exception");

        } catch (ClientProtocolException e) {
            Log.e(TAG, "client protocol exception" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "exception" + e.getMessage());
        }
        return null;
    }

    /**
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     *
     * @param actionUrl
     * @param params
     * @param files
     * @return
     * @throws java.io.IOException
     */
    public static String postUploadFile(String actionUrl,
                                        Map<String, String> params, Map<String, File> files) {
        // StringBuilder sb2 = null;
        StringBuffer sb2 = null;
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        try {
            URL uri = new URL(actionUrl);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setReadTimeout(5 * 1000); // 缓存的最长时间
            conn.setDoInput(true);// 允许输入
            conn.setDoOutput(true);// 允许输出
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
                    + ";boundary=" + BOUNDARY);

            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Log.e(TAG, "参数 名:" + entry.getKey());
                Log.e(TAG, "参数值" + entry.getValue());
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\""
                        + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Type: text/plain; charset=" + CHARSET
                        + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

            DataOutputStream outStream = new DataOutputStream(
                    conn.getOutputStream());
            outStream.write(sb.toString().getBytes());
            InputStream in = null;

            // 发送文件数据
            if (files != null) {
                for (Map.Entry<String, File> file : files.entrySet()) {
                    Log.e(TAG, "文件名 :" + file.getKey());
                    Log.e(TAG, "文件路径:" + file.getValue());
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(PREFIX);
                    sb1.append(BOUNDARY);
                    sb1.append(LINEND);
                    sb1.append("Content-Disposition: form-data; name=\"upload\"; filename=\""
                            + file.getKey() + "\"" + LINEND);
                    sb1.append("Content-Type: application/octet-stream; charset="
                            + CHARSET + LINEND);
                    sb1.append(LINEND);
                    outStream.write(sb1.toString().getBytes());

                    InputStream is = new FileInputStream(file.getValue());
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }

                    is.close();
                    outStream.write(LINEND.getBytes());
                }

            }
            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();
            // 得到响应码
            int res = conn.getResponseCode();
            Log.e(TAG, "code:" + res);
            if (res == 200) {
                in = conn.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        in, "utf-8"));
                sb2 = new StringBuffer();
                String data = "";
                while ((data = br.readLine()) != null) {
                    sb2.append(data + "\n");
                }
            } else {
                return null;
            }
            outStream.close();
            conn.disconnect();

        } catch (Exception e) {
            Log.e(TAG, "IOException:" + e.getMessage());

        }
        if (sb2 == null) {
            return null;
        }
        Log.e(TAG, "返回结果：" + sb2.toString());
        return sb2.toString();

    }

    public synchronized static String doJsonPost(String url, String json) {
        Log.e("do json post", "url:" + url);
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpParams httpParams = new BasicHttpParams();

            HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(httpParams, CHARSET);
            HttpProtocolParams.setUseExpectContinue(httpParams, false);

            HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
            HttpConnectionParams.setSoTimeout(httpParams, 15000);
            httpPost.setParams(httpParams);

            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory
                    .getSocketFactory(), 443));
            ThreadSafeClientConnManager conMgr = new ThreadSafeClientConnManager(
                    httpParams, schReg);
            defaultHttpClient = new DefaultHttpClient(conMgr, httpParams);

            StringEntity stringEntity = new StringEntity(json, CHARSET);
            stringEntity.setContentType("application/json");

            httpPost.setEntity(stringEntity);

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse == null) {
                Log.e(TAG, "http response result null");
                return null;
            }
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(httpResponse.getEntity(),
                        HTTP.UTF_8);

                Log.e(TAG, "http response sucessful");
                if (result == null || "".equals(result)) {
                    Log.e(TAG,
                            "request was sucessful, but paser value was null or empty");
                }
                Log.e(TAG, "respnse result:" + result);
                return result;
            } else {
                Log.e(TAG, "http response code:"
                        + httpResponse.getStatusLine().getStatusCode());
                return null;
            }
        } catch (ConnectTimeoutException e) {
            Log.e(TAG, "connection time out exception");
        } catch (ClientProtocolException e) {
            Log.e(TAG, "client protocol exception" + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "io exception" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "exception" + e.getMessage());
        }
        return null;
    }

}
