package httpTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import cn.ieauto.autogroup.android.MyApplication;

/**
 * 个人信息处理
 * 
 * @author Simon
 * 
 */
public class PersonalRequest {
	/**
	 * 服务器访问父URL目录
	 */
	private static final String URL = "http://117.135.163.205:8080/carinfo_resource/resources/";

	// private static final String URL =
	// "http://121.41.116.152:8080/carinfo_resource-1.0.0/resources/";
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
	private static HttpGet httpGet = null;

	/**
	 * 取得HTTP response
	 */
	private static HttpResponse response = null;
	/**
	 * 数据
	 */
	private static BufferedReader in = null;
	/**
	 * Post运作传送变数必须用NameValuePair[]阵列储存
	 */
	private static List<NameValuePair> postParameters = null;
	/**
	 * 
	 */
	private static String responsecontent = null;
	/**
	 *  
	 */
	private static HttpParams httpParameters = null;
	/**
	 * 
	 */
	private static JSONObject jsonObject = null;

	/**
	 * 注册第一步
	 * 
	 * @param phone
	 *            :用户手机号码
	 * @param password
	 *            :用户密码
	 * @param validateCode
	 *            :验证码
	 * 
	 * @return responsecontent:用户车友信用户编号ID
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String register_first(String phone, String password,
			String validateCode) throws ClientProtocolException, IOException,
			JSONException {
		requestUrl = URL + "personal/register_first";
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 200 * 1000);// 设置请求超时10秒
		HttpConnectionParams.setSoTimeout(httpParameters, 200 * 1000); // 设置等待数据超时10秒
		client = new DefaultHttpClient(httpParameters); // 此时构造DefaultHttpClient时将参数传入
		request = new HttpPost(requestUrl);
		postParameters = new ArrayList<NameValuePair>();
		jsonObject = new JSONObject();
		jsonObject.put("mobileNumber", phone);
		jsonObject.put("psd", password);
		jsonObject.put("validateCode", validateCode);
		postParameters.add(new BasicNameValuePair("jsonstring", jsonObject
				.toString()));
		//
		StringEntity entity = new StringEntity(jsonObject.toString(),
				HTTP.UTF_8);
		entity.setContentType("application/json");
		// 设置头
		request.setEntity(entity);
		response = client.execute(request);
		// 打印日志
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		if (!TextUtils.isEmpty(responsecontent)) {
			MyApplication.getInstance().setUserSystemID(responsecontent);
		}
		return responsecontent;
	}

	/**
	 * 发送验证码给手机：GET <BR>
	 * 因为发送给手机号码，app中只需要返回是否已经收到即可 <BR>
	 * 注明：输入手机号即可<BR>
	 * 返回：true/false 表示短信验证码发送是否成功
	 */
	public static boolean requestSMS(String phone)
			throws ClientProtocolException, IOException {
		// SMS访问地址
		requestUrl = URL + "personal/sendSMS/" + phone + "/sms";
		// 返回
		client = new DefaultHttpClient();
		request = new HttpPost(requestUrl);

		// 发出HTTP request,最后的HTTP.UTF_8最重要,用于模拟UTF8的浏览器数据访问
		request.setEntity(new UrlEncodedFormEntity(postParameters, HTTP.UTF_8));
		// 取得HTTP response
		response = client.execute(request);
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		boolean resultSMS = false;
		resultSMS = Boolean.parseBoolean(responsecontent);
		return resultSMS;
	}

	/**
	 * 注册第二步
	 * 
	 * @param user_Id
	 * @param autoBrand
	 * @param autoSeries
	 * @param registCity
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String register_second(String user_Id, String autoBrand,
			String autoSeries, String registCity)
			throws ClientProtocolException, IOException, JSONException {
		requestUrl = URL + "personal/register_second";
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 200 * 1000);// 设置请求超时10秒
		HttpConnectionParams.setSoTimeout(httpParameters, 200 * 1000); // 设置等待数据超时10秒
		client = new DefaultHttpClient(httpParameters); // 此时构造DefaultHttpClient时将参数传入
		request = new HttpPost(requestUrl);

		postParameters = new ArrayList<NameValuePair>();
		jsonObject = new JSONObject();
		jsonObject.put("id", user_Id);
		jsonObject.put("autoBrand", autoBrand);
		jsonObject.put("mobileNumber", MyApplication.getInstance()
				.getUserPhone());
		jsonObject.put("psd", MyApplication.getInstance().getPassword());
		jsonObject.put("validateCode", "123456");
		jsonObject.put("autoSeries", autoSeries);
		postParameters.add(new BasicNameValuePair("jsonstring", jsonObject
				.toString()));
		StringEntity entity = new StringEntity(jsonObject.toString(),
				HTTP.UTF_8);
		entity.setContentType("application/json");
		request.setEntity(entity);
		response = client.execute(request);
		Log.w("ieauto", response.getStatusLine().getStatusCode() + "");
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		if (!TextUtils.isEmpty(responsecontent)) {
			MyApplication.getInstance().setUserSystemID(responsecontent);
		}
		return responsecontent;
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static boolean doLogin(String phone, String password)
			throws ClientProtocolException, IOException {
		requestUrl = URL + "/personal/{" + phone + "}/{" + password + "}/login";
		client = new DefaultHttpClient();
		request = new HttpPost(requestUrl);
		/* 发出HTTP request,最后的HTTP.UTF_8最重要,用于模拟UTF8的浏览器数据访问 */
		request.setEntity(new UrlEncodedFormEntity(postParameters, HTTP.UTF_8));
		// 取得HTTP response
		response = client.execute(request);
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		if (!TextUtils.isEmpty(responsecontent)) {
			MyApplication.getInstance().saveBaseInfo(phone, password, "");
		}
		return Boolean.getBoolean(responsecontent);
	}

	/**
	 * 通过手机号获取个人信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws JSONException
	 * 
	 */
	public static String requestPropertyinfo(String phone)
			throws IllegalStateException, IOException, JSONException {
		requestUrl = URL + "/personal/" + phone + "/propertyinfo";
		httpParameters = new BasicHttpParams();
		client = new DefaultHttpClient(httpParameters); // 此时构造DefaultHttpClient时将参数传入
		request = new HttpPost(requestUrl);
		postParameters = new ArrayList<NameValuePair>();
		jsonObject = new JSONObject();
		jsonObject.put("mobileNumber", phone);
		postParameters.add(new BasicNameValuePair("jsonstring", jsonObject
				.toString()));
		StringEntity entity = new StringEntity(jsonObject.toString(),
				HTTP.UTF_8);
		entity.setContentType("application/json");
		request.setEntity(entity);
		response = client.execute(request);
		Log.w("ieauto", response.getStatusLine().getStatusCode() + "");
		in = new BufferedReader(new InputStreamReader(response.getEntity()
				.getContent()));
		responsecontent = in.readLine();
		if (in != null) {
			in.close();
		}
		if (!TextUtils.isEmpty(responsecontent)) {
		}
		return responsecontent;
	}

	/**
	 * 通过手机号获取座驾信息carinfo
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws JSONException
	 */
	public static String requestCarInfo(String phone)
			throws IllegalStateException, IOException, JSONException {
		requestUrl = URL + "personal/carinfo/" + "13918036371" + "/info";
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 200 * 1000);// 设置请求超时10秒
		HttpConnectionParams.setSoTimeout(httpParameters, 200 * 1000); // 设置等待数据超时10秒
		client = new DefaultHttpClient(httpParameters); // 此时构造DefaultHttpClient时将参数传入
		httpGet = new HttpGet(requestUrl);
		response = client.execute(httpGet);
		Log.w("ieauto", response.getStatusLine().getStatusCode() + "");
		responsecontent = EntityUtils
				.toString(response.getEntity(), HTTP.UTF_8);
		return responsecontent;
	}
}
