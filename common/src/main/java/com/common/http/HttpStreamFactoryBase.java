package com.common.http;

import java.io.IOException;
import java.io.InputStream;

public abstract class HttpStreamFactoryBase<T> extends HttpFactoryBase<T> {
	
	@Override
	protected T AnalysisContent(InputStream stream) throws IOException {
		try {
			return AnalysisData(stream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}

	protected abstract T AnalysisData(InputStream reader) throws Exception;
}