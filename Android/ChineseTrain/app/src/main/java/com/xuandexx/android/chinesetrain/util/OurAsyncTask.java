package util;

import android.os.AsyncTask;

public abstract class OurAsyncTask extends AsyncTask<Object, Object, Object> {

	public OurAsyncTask() {

	}

	@Override
	protected abstract String doInBackground(Object... arg0);

	protected abstract void onPostExecute(String result);

	@Override
	protected abstract void onPreExecute();

	protected void onProgressUpdate(Integer... values) {

	}

}
