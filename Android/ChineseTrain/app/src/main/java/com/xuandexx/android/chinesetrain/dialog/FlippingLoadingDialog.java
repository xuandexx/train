package dialog;

import android.content.Context;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyDialog;
import cn.ieauto.autogroup.android.widget.FlippingImageView;
import cn.ieauto.autogroup.android.widget.HandyTextView;

public class FlippingLoadingDialog extends MyDialog {

	private FlippingImageView mFivIcon;
	private HandyTextView mHtvText;
	private String mText;

	public FlippingLoadingDialog(Context context, String text) {
		super(context);
		mText = text;
		init();
	}

	private void init() {
		setContentView(R.layout.common_flipping_loading_diloag);
		mFivIcon = (FlippingImageView) findViewById(R.id.loadingdialog_fiv_icon);
		mHtvText = (HandyTextView) findViewById(R.id.loadingdialog_htv_text);
		mFivIcon.startAnimation();
		mHtvText.setText(mText);
	}

	public void setText(String text) {
		mText = text;
		mHtvText.setText(mText);
	}

	public void dismiss() {
		if (isShowing()) {
			super.dismiss();
		}
	}
}
