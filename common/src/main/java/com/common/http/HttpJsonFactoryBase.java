package com.common.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.protocol.HTTP;


import android.util.JsonReader;


public abstract class HttpJsonFactoryBase<T> extends HttpFactoryBase<T> {
	
	@Override
	protected T AnalysisContent(InputStream stream) throws IOException {
		InputStreamReader reader = new InputStreamReader(stream, HTTP.UTF_8);
		//InputStreamReader reader2 = new InputStreamReader(stream, HTTP.UTF_8);
		//char[] bs = new char[1024]; 
		//int len = 0;
		//while ((len = reader2.read(bs)) != -1) {   
		//	LogUtils.i("test", String.valueOf(bs));
        //}  
		JsonReader json = new JsonReader(reader);
		try {
			return AnalysisData(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			reader.close();
			json.close();
		}
	}

	protected abstract T AnalysisData(JsonReader reader) throws IOException;
}
