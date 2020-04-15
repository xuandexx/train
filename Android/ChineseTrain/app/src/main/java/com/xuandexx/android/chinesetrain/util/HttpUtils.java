package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.entity.MessageContent;

/**
 * 向服务器发送请求,获取响应结果
 * 
 * @author LiTing
 */
public class HttpUtils {

	public HttpUtils() {
	}

	public String dosend(MessageContent content)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		BufferedReader in = null;
		String responsecontent = null;
		HttpClient client = new DefaultHttpClient();
		//
		HttpPost request = new HttpPost(ComConfig.LOGIN_URL);
		/* Post运作传送变数必须用NameValuePair[]阵列储存 */
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters
				.add(new BasicNameValuePair("message", content.toString()));
		// 发出HTTP request,最后的HTTP.UTF_8最重要,用于模拟UTF8的浏览器数据访问
		request.setEntity(new UrlEncodedFormEntity(postParameters, HTTP.UTF_8));
		// 取得HTTP response
		HttpResponse response = client.execute(request);
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		return responsecontent;
	}

	private static final String URL = "http://192.168.2.128:8080/carinfo_resource/resources";
	// private static final String URL = "http://www.baidu.com";
	/**
	 * 具体访问URL
	 */
	private static String requestUrl = null;
	/**
	 * 客户端连接
	 */
	private static HttpClient client = null;
	/**
	 * 请求
	 */
	private static HttpPost request = null;
	/**
	 * 数据
	 */
	private static BufferedReader in = null;
	/**
	 * Post运作传送变数必须用NameValuePair[]阵列储存
	 */
	/**
	 * 
	 */
	private static String responsecontent = null;
	/**
	 * 取得HTTP response
	 */
	private static HttpResponse response = null;

	public String dosend1() {
		// 注册第一步
		requestUrl = URL + "/personal/register_first";

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 200 * 1000);// 设置请求超时10秒
		HttpConnectionParams.setSoTimeout(httpParameters, 200 * 1000); // 设置等待数据超时10秒

		client = new DefaultHttpClient(httpParameters); // 此时构造DefaultHttpClient时将参数传入
		// request = new HttpPost(requestUrl);
		request = new HttpPost(requestUrl);
		try {
			/* Post运作传送变数必须用NameValuePair[]阵列储存 */
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			// postParameters
			// .add(new BasicNameValuePair("mobileNumber", "13651623268"));
			// postParameters.add(new BasicNameValuePair("psd", "123367"));
			// postParameters.add(new BasicNameValuePair("validateCode",
			// "123456"));
			// 取得HTTP response
			/* 发出HTTP request,最后的HTTP.UTF_8最重要,用于模拟UTF8的浏览器数据访问 */
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("mobileNumber", "13651623268");
				jsonObject.put("psd", "123367");
				jsonObject.put("validateCode", "123456");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			postParameters.add(new BasicNameValuePair("jsonstring", jsonObject
					.toString()));


			StringEntity entity = new StringEntity(jsonObject.toString(),
					HTTP.UTF_8);
			entity.setContentType("application/json");

			// request.setHeaders(getHeadByRequest(header));

			request.setEntity(entity);

			response = client.execute(request);

			System.out.println("status is:"
					+ response.getStatusLine().getStatusCode());

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			responsecontent = in.readLine();
			if (in != null) {
				in.close();
			}
			if (!TextUtils.isEmpty(responsecontent)) {
				MyApplication.getInstance().setUserSystemID(responsecontent);

				// 返回状态值
				responsecontent = Integer.toString(response.getStatusLine()
						.getStatusCode());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responsecontent;
	}

	public Header[] getHeadByRequest(HashMap<String, String> header) {
		Header[] headers = null;
		Header contentType = new BasicHeader("Content-Type", "application/json");
		headers = new Header[] { contentType };
		return headers;
	}

	private static final int TIME_OUT = 10 * 1000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码

	public String doPost() throws UnsupportedEncodingException,
			ClientProtocolException, IOException {
		String result = null;
		String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "application/json"; // 内容类型

		try {
			requestUrl = URL + "/personal/register_first";
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", "utf-8"); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);

			if (true) {
				/**
				 * 当文件不为空，把文件包装并且上传
				 */
				DataOutputStream dos = new DataOutputStream(
						conn.getOutputStream());
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后缀名的 比如:abc.png
				 */

				// sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END);
				sb.append("Content-Type: application/json; charset=" + CHARSET
						+ LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				dos.write(LINE_END.getBytes());

				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
						.getBytes();
				dos.write(end_data);
				dos.flush();
				/**
				 * 获取响应码 200=成功 当响应成功，获取响应的流
				 */
				int res = conn.getResponseCode();
				Log.e("httpUtils", "response code:" + res);
				if (res == 200) {
					Log.e("httpUtils", "request success");
					java.io.InputStream input = conn.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char) ss);
					}
					result = sb1.toString();
					Log.e("httpUtils", "result : " + result);
				} else {
					Log.e("httpUtils", "request error");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}