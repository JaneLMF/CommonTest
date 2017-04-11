package com.common.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import android.content.Context;
import android.os.AsyncTask;

import com.common.utlis.ULog;

import org.apache.http.protocol.HTTP;


public abstract class AssertStringFactoryBase<T> {

	private static final String TAG = AssertStringFactoryBase.class.getSimpleName();
	private Context context;
	private HttpEventHandler<T> httpEventHandler;
	
	public void setHttpEventHandler(HttpEventHandler<T> httpEventHandler) {
		this.httpEventHandler = httpEventHandler;
	}
	
	public AssertStringFactoryBase(Context context) {
		this.context = context;
	}
	
	public void DownloadDatas() {
		LocalDownloadTask downloadTask = new LocalDownloadTask();
		downloadTask.execute();
	}
	
	protected abstract String getFileName();
	protected abstract T AnalysisData(String reader) throws IOException;
	
	protected T AnalysisContent() throws IOException {
		InputStream stream = context.getResources().getAssets().open(getFileName());;
		String json = "";
		InputStreamReader reader = null;
		ByteArrayOutputStream os = null;
		OutputStreamWriter osw = null;
		try {
			reader = new InputStreamReader(stream, HTTP.UTF_8);
			os = new ByteArrayOutputStream();
			osw = new OutputStreamWriter(os);
			char[] bs = new char[1024];
			int len = 0;
			while ((len = reader.read(bs)) != -1) {   
				osw.write(bs, 0, len);
			}
			osw.flush();
			os.flush();
			json = os.toString();
		} finally {
			if(osw != null)osw.close();
			if(os != null)os.close();
			if(reader != null)reader.close();
			if(stream != null)stream.close();			
		}
		ULog.d(TAG, json);
		return AnalysisData(json);
	}
	
	private class LocalDownloadTask extends AsyncTask<Object, Integer, T> {

		@Override
		protected T doInBackground(Object... params) {
			try {
				return AnalysisContent();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(T result) {
			if (result == null) {
				if (httpEventHandler != null) {
					httpEventHandler.HttpFailHandler();
				}
			} else {
				if (httpEventHandler != null) {
					httpEventHandler.HttpSucessHandler(result);
				}
			}
		}
	}
}
