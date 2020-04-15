package activity.fragments.wo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.AddressCityActivity;
import cn.ieauto.autogroup.android.adapter.DataFillAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
import cn.ieauto.autogroup.android.dialog.MyDatePickDialog;

/**
 * 个人信息
 * 
 * @author Administrator
 * 
 */

@SuppressLint("SdCardPath")
public class PersonalInfoActivity extends MyActivity implements OnClickListener {
	/** 头像 */
	private static ImageView imageViewFace;
	/** 昵称 */
	private static TextView textViewNickName;
	/** 性别 */
	private static Button buttonSex;
	/** 年龄 */
	private static Button buttonBirthday;
	/** 地区 */
	private static TextView textViewAddress;
	/** 个性签名 */
	private static TextView textViewSignature;

	String mNickName = null, mSex = null, mAge = null, mAddress = null,
			mSignature = null;
	/** 获取的拍摄的图片 */
	private Bitmap bitmap = null;
	/** 拍摄图片的存储路径 */
	private String fileName = null;
	private CustomDialog.Builder builder = new CustomDialog.Builder(this);
	private Intent intent;
	private Calendar calendar = Calendar.getInstance();
	// 年月日
	private String initDate;
	private static String birthday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myself);
		initViews();
		initEvents();
		initmySharedpreferenceText();
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.personal_info);
		imageViewFace = (ImageView) findViewById(R.id.iv_myself_face);
		textViewNickName = (TextView) findViewById(R.id.tv_myself_nickname);
		buttonSex = (Button) findViewById(R.id.btn_myself_sex);
		buttonBirthday = (Button) findViewById(R.id.tv_myself_birthday);
		textViewAddress = (TextView) findViewById(R.id.tv_myself_address);
		textViewSignature = (TextView) findViewById(R.id.tv_myself_signature);
	}

	@Override
	protected void initEvents() {
		initDate = calendar.get(Calendar.YEAR) + "年"
				+ calendar.get(Calendar.MONTH) + "月"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "日";
		textViewNickName.setOnClickListener(this);
	}

	/**
	 * 加载页面时,初始化数据
	 */
	private void initmySharedpreferenceText() {
		textViewNickName.setText(MyApplication.getInstance().getNickName());
		textViewAddress.setText(MyApplication.getInstance().getAddress());
		buttonSex.setText(MyApplication.getInstance().getSex());
		textViewSignature.setText(MyApplication.getInstance().getSignature());
		birthday = MyApplication.getInstance().getBirthday();
		if (!TextUtils.isEmpty(birthday)) {
			buttonBirthday.setText(getAge(birthday));
			buttonBirthday.setText(birthday);
		} else {
			buttonBirthday.setText(initDate);
		}
		if (!TextUtils.isEmpty(MyApplication.getInstance().getNickName())) {
			textViewNickName.setText(MyApplication.getInstance().getNickName());
		} else {
			textViewNickName.setText("请设置你的昵称!");
		}
		if (!TextUtils.isEmpty(MyApplication.getInstance().getSex())) {
			buttonSex.setText(MyApplication.getInstance().getSex());
		} else {
			buttonSex.setText("性别");
		}
		if (!TextUtils.isEmpty(MyApplication.getInstance().getAddress())) {
			textViewAddress.setText(MyApplication.getInstance().getAddress());
		} else {
			textViewAddress.setText("城市" + " \t" + "地区");
		}
		if (!TextUtils.isEmpty(MyApplication.getInstance().getSignature())) {
			textViewSignature.setText(MyApplication.getInstance()
					.getSignature());
		} else {
			textViewSignature.setText("个性签名");
		}
		// TODO:
		// img_Avatar.setImageBitmap(MyApplication.getInstance().getAvatr);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/** 返回 */
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		/** 添加头像: */
		case R.id.rl_myself_face:
			PopupWindow popupWindow = new PopupWindow(this, null, 300, 300);

			break;
		/** 设置昵称 */
		case R.id.tv_myself_nickname:
			intent = new Intent(PersonalInfoActivity.this,
					DataFillAdapter.class);
			intent.putExtra("title", "设置昵称");
			startActivityForResult(intent, 2);
			break;

		/** 选择性别 */
		case R.id.btn_myself_sex:
			Dialog dialog = new Dialog(this);
			builder.setTitle(R.string.cheyouxints)
					.setMessage("请选择性别?")
					.setPositiveButton("男",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									buttonSex.setText("男");
									dialog.dismiss();
								}
							})
					.setNegativeButton("女",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									buttonSex.setText("女");
									dialog.dismiss();
								}
							});
			dialog = builder.create();
			dialog.show();
			break;
		/** 出生日期选择 */
		case R.id.tv_myself_birthday:
			MyDatePickDialog myDatePickDialog = null;
			if ((MyApplication.getInstance().getBirthday().toString()) == null) {
				myDatePickDialog = new MyDatePickDialog(
						PersonalInfoActivity.this, initDate);
			} else {
				myDatePickDialog = new MyDatePickDialog(
						PersonalInfoActivity.this, MyApplication.getInstance()
								.getBirthday().toString());
			}
			myDatePickDialog.datePicKDialog(buttonBirthday);
			myDatePickDialog.toast("请选择正确的出生日期?");
			birthday = buttonBirthday.getText().toString().trim();
			break;

		/** 选择居住地 */
		case R.id.tv_myself_address:
			intent = new Intent(this, AddressCityActivity.class);
			startActivityForResult(intent, ComConfig.INTENT_CITY);
			break;
		/** 个性签名 */
		case R.id.tv_myself_signature:
			intent = new Intent(this, DataFillAdapter.class);
			intent.putExtra("title", "个性签名");
			startActivityForResult(intent, 3);
			break;
		}
	}

	/**
	 * 根据出生日期 得到 年龄
	 * 
	 * @param birthday
	 * @return age
	 */
	private String getAge(String birthday) {
		String date = MyDatePickDialog.spliteString(birthday, "日", "index",
				"front"); // 日期
		String yearStr = MyDatePickDialog.spliteString(date, "年", "index",
				"front"); // 年
		String monthAndDay = MyDatePickDialog.spliteString(date, "年", "index",
				"back"); // 月日
		String monthStr = MyDatePickDialog.spliteString(monthAndDay, "月",
				"index", "front"); // 月
		String dayStr = MyDatePickDialog.spliteString(monthAndDay, "月",
				"index", "back"); // 日
		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue();
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		String Age = String.valueOf((calendar.get(Calendar.YEAR))
				- (currentYear));
		if (currentYear == calendar.get(Calendar.YEAR)) {
			if (currentMonth > (calendar.get(Calendar.MONTH))) {
				toast("出生日期选择错误");
			} else if (currentMonth < (calendar.get(Calendar.MONTH))) {
				Age = String.valueOf(1);
			} else if (currentMonth == (calendar.get(Calendar.MONTH))) {
				if (currentDay <= (calendar.get(Calendar.DAY_OF_MONTH))) {
					Age = String.valueOf(1);
				} else if (currentDay > (calendar.get(Calendar.DAY_OF_MONTH))) {
					toast("出生日期选择错误");
				}
			}
		} else if (currentYear > calendar.get(Calendar.YEAR)) {
			toast("出生日期选择错误");
		} else {
			if (currentMonth > (calendar.get(Calendar.MONTH))) {
				Age = Age + 1;
			} else if (currentMonth == (calendar.get(Calendar.MONTH))) {
				if (currentDay >= (calendar.get(Calendar.DAY_OF_MONTH))) {
					Age = Age + 1;
				}
			}
		}
		return Age;
	}

	/**
	 * 图片的存储与调用,用于接收onPositiveClickListener点击事件的结果,即拍摄的图片结果Bitmap
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("tag=", requestCode + "+" + resultCode + "+" + data);
		switch (requestCode) {
		case ComConfig.INTENT_CITY:
			if (data != null) {
				Log.i("tag4=", requestCode + "+" + resultCode + "+" + data);
				mAddress = data.getStringExtra("province") + " "
						+ data.getStringExtra("city");
				textViewAddress.setText(mAddress);
			}
			break;
		case 2:

			textViewNickName.setText(data.getStringExtra("text"));

			break;

		case ComConfig.INTENT_PHOTOGRAPH:
			if (resultCode == Activity.RESULT_OK) {
				String sdStatus = Environment.getExternalStorageState();
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
					Log.v("TestFile",
							"SD card is not avaiable/writeable right now.");
					return;
				}
				Bundle bundle = data.getExtras();
				bitmap = (Bitmap) bundle.get("data");
				FileOutputStream b = null;
				File file = new File("/sdcard/myImage/");
				file.mkdirs();
				fileName = "/sdcard/myImage/avatar" + ".jpg";
				try {
					b = new FileOutputStream(fileName);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						b.flush();
						b.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				imageViewFace.setImageBitmap(bitmap);
			}
			break;
		case 3:
			textViewSignature.setText(data.getStringExtra("text"));
			break;
		default:
			break;
		}

	}
}
