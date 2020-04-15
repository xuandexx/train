package activity.fragments.cheshenghuo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

@SuppressLint("SdCardPath")
public class CheyouquanActivity extends MyActivity {
	/********************************* 车友圈头部 *********************************/
	private static ImageView imageViewBack, imageViewCamera;
	/********************************* 车友圈顶部 *********************************/
	private static View viewHeadView;
	// 头部控件配置
	private static ImageView imageViewBackground, imageViewFace;
	// 头部昵称
	private static TextView textViewNeckname;

	private static String neckname = null;
	/********************************* 车友圈顶部 *********************************/
	// 拍照后上传时弹出的对话框,添加评论
	private static View viewSendDialog = null;
	// 拍照或者选择图库时的图片
	private static ImageView imagePhoto = null;
	/******************************************************************************/
	// 说说记录baseAdapter承载数据List数据
	private static ArrayList<HashMap<String, Object>> list = null;
	// 车友圈数据承载listView
	private static ListView listView;
	/*********************************** 拍照 **************************************/
	// 获取的拍摄的图片
	private static Bitmap bitmapPhoto = null;
	// 评论文字
	private static EditText editTextComment = null;
	private static String mDialog_ed = null;
	/** 拍摄图片的存储路径 */
	private static String photoFilePath = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_cheyouquan);
		initViews();
	}

	// 初始化控件
	@Override
	protected void initViews() {
		// top
		imageViewBack = (ImageView) findViewById(R.id.view_top_img_back);
		imageViewCamera = (ImageView) findViewById(R.id.view_top_img_right);
		imageViewBack.setBackgroundResource(R.drawable.button_return);
		imageViewCamera.setBackgroundResource(R.drawable.cameras_1);
		// head
		listView = (ListView) findViewById(R.id.lv_cheyouquan_list);
		viewHeadView = LayoutInflater.from(context).inflate(
				R.layout.cheyouquan_head, null);
		listView.addHeaderView(viewHeadView);
		// listView
		textViewNeckname = (TextView) findViewById(R.id.tv_cheyouquan_neckname);
		imageViewBackground = (ImageView) findViewById(R.id.iv_cheyouquan_background);
		imageViewFace = (ImageView) findViewById(R.id.tv_cheyouquan_face);
		list = new ArrayList<HashMap<String, Object>>();
		LayoutInflater factorys = LayoutInflater.from(CheyouquanActivity.this);
		viewSendDialog = factorys.inflate(R.layout.photodialog, null);
		editTextComment = (EditText) viewSendDialog
				.findViewById(R.id.et_photodialog_comment);
		imagePhoto = (ImageView) viewSendDialog
				.findViewById(R.id.et_photodialog_photo);
	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.cheyouquan);
		neckname = MyApplication.getInstance().getNickName();
		imageViewCamera.setOnClickListener(ocl);
		imageViewBack.setOnClickListener(ocl);
		textViewNeckname.setText(neckname);
		imageViewBackground.setBackgroundResource(R.drawable.wx_3);
		imageViewFace.setBackgroundResource(R.drawable.wx_2);
		// TODO
		listView.setOnItemClickListener(null);
	}

	/**
	 * 图片的存储与调用,用于接收onPositiveClickListener点击事件的结果,即拍摄的图片结果Bitmap
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
				Log.v("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}

			Bundle bundle = data.getExtras();
			bitmapPhoto = (Bitmap) bundle.get("data");

			FileOutputStream b = null;
			File file = new File("/sdcard/myImage/");
			file.mkdirs();

			Date date = new Date();
			photoFilePath = "/sdcard/myImage/" + date.getTime() + ".jpg";
			try {
				b = new FileOutputStream(photoFilePath);
				bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 100, b);
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
			/*
			 * builder = new CustomDialog.Builder(CarCircleActivity.this);
			 * builder.setTitle("车友圈").setContentView(view)
			 * .setPositiveButton("确定", onPostedRidersCircle)
			 * .setNegativeButton("取消", onPostedRidersCircle).create() .show();
			 */
			showAlertDialog("车友圈", viewSendDialog, "确定", onPostedRidersCircle,
					"取消", onPostedRidersCircle);
			imagePhoto.setImageBitmap(bitmapPhoto);
		}
	}

	private DialogInterface.OnClickListener onPostedRidersCircle = new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				mDialog_ed = editTextComment.getText().toString().trim();
				addTextToList(R.drawable.of_line_face, neckname, mDialog_ed,
						bitmapPhoto);
				System.out.println("bitmap1=" + bitmapPhoto);
				String from[] = new String[] { "face", "name", "description",
						"picture" };
				int to[] = new int[] { R.id.item_carcirc_listview_face,
						R.id.item_carcirc_listview_name,
						R.id.item_carcirc_listview_description,
						R.id.item_carcirc_listview_picture };
				CarCircleAdapter adapter1 = new CarCircleAdapter(
						CheyouquanActivity.this, list, from, to);
				listView.setAdapter(adapter1);

				break;

			case DialogInterface.BUTTON_NEGATIVE:
				/*
				 * showAlertDialog("车友圈", "退出此次编辑?", "退出", onWhetherToExit,
				 * "取消", onWhetherToExit);
				 */
				break;
			}
		}
	};

	protected void addTextToList(int icLauncher, String name,
			String description, Bitmap bitmap) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("face", icLauncher);
		map.put("name", name);
		map.put("description", description);
		System.out.println("bitmap2=" + bitmap);
		map.put("picture", bitmap);
		list.add(map);
	}

	private class CarCircleAdapter extends BaseAdapter {

		Context context = null;
		ArrayList<HashMap<String, Object>> CarList = null;
		String[] from;
		int[] to;

		public CarCircleAdapter(Context context,
				ArrayList<HashMap<String, Object>> CarList, String[] from,
				int[] to) {
			super();
			this.context = context;
			this.CarList = CarList;
			this.from = from;
			this.to = to;
		}

		public int getCount() {
			return CarList.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		class ViewHolder {
			// "face", "name", "description","picture"
			public ImageView image_face = null;
			public TextView textV_name = null;
			public TextView textV_description = null;
			public ImageView image_picture = null;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			/*
			 * factorys = LayoutInflater.from(CarCircleActivity.this); view =
			 * factorys.inflate(R.layout.dialogview, null);
			 * R.id.item_carcirc_listview_face, R.id.item_carcirc_listview_name,
			 * R.id.item_carcirc_listview_description,
			 * R.id.item_carcirc_listview_picture
			 */
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_carcirc_listview, null);
			holder = new ViewHolder();
			holder.image_face = (ImageView) convertView.findViewById(to[0]);
			holder.textV_name = (TextView) convertView.findViewById(to[1]);
			holder.textV_description = (TextView) convertView
					.findViewById(to[2]);
			holder.image_picture = (ImageView) convertView.findViewById(to[3]);

			holder.image_face.setBackgroundResource((Integer) CarList.get(
					position).get(from[0]));
			holder.textV_name.setText((CharSequence) CarList.get(position).get(
					from[1]));
			holder.textV_description.setText((CharSequence) CarList.get(
					position).get(from[2]));
			holder.image_picture.setImageBitmap((Bitmap) CarList.get(position)
					.get(from[3]));
			return convertView;
		}
	}

	private OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.view_top_img_back:
				defaultFinish();
				break;
			case R.id.view_top_img_right:
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 1);
				showAlertDialog(null, null, "拍照", onCalllocal, "本地图片",
						onCalllocal);
				break;
			}
		}
	};

	private DialogInterface.OnClickListener onCalllocal = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			switch (arg1) {
			/** 调用照相机 */
			case DialogInterface.BUTTON_POSITIVE:
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 1);
				break;
			/** 调用本地图片 */
			case DialogInterface.BUTTON_NEGATIVE:
				break;
			}
		}
	};

}
