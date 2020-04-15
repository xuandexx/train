package activity.fragments.business;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DisplayUtil {	
	private static int mScreenWidth = 0;
	private static int mScreenHeight = 0;

	public static int getScreenWidth(Context context) {
		initScreenInfo(context);
		return mScreenWidth;
	}

	public static int getScreenHeight(Context context) {
		initScreenInfo(context);
		return mScreenHeight;
	}

	private static void initScreenInfo(Context context) {
		if (mScreenWidth == 0 || mScreenHeight == 0) {
			Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
			DisplayMetrics dm = new DisplayMetrics();
			try {
				if (Build.VERSION.SDK_INT < 13) {
					display.getMetrics(dm);
				} else {
					display.getRealMetrics(dm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			mScreenWidth = dm.widthPixels;
			mScreenHeight = dm.heightPixels;
		}
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            int height = listItem.getMeasuredHeight();
            totalHeight += height;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}