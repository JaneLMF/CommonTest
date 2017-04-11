package com.common.http;

import com.common.utlis.ULog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.protocol.HTTP;


public abstract class HttpStringFactoryBase<T> extends HttpFactoryBase<T> {
	
	private final String TAG = "HttpStringFactoryBase";

	@Override
	protected T AnalysisContent(InputStream stream) throws IOException {
		try {
			return AnalysisData(streamToString(stream));
		} catch (Exception e) {
			ULog.e(TAG, "Exception by AnalysisContent .",e);
			return null;
		} finally {
			if(stream != null){
				stream.close();
				stream = null;
			}
		}
	}
	
	public String streamToString(InputStream stream) throws Exception{
		String json = null;
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
			return json;
		}finally{
			if(osw!=null){
				osw.close();
				osw = null;
			}
			
			if(os!=null){
				os.close();
				os = null;
			}
			
			if(reader!=null){
				reader.close();
				reader = null;
			}
		}
	}

	protected abstract T AnalysisData(String content) throws Exception;
}