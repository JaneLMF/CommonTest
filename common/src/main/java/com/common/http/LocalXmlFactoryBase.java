package com.common.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


import android.os.AsyncTask;

import com.common.utlis.ULog;

/**
 * ����xml�ļ��������֧࣬��ҁE���ͬ�������? * 
 * */
public abstract class LocalXmlFactoryBase<T> {

	private static final String TAG = LocalXmlFactoryBase.class.getSimpleName();
	private HttpEventHandler<T> mHandler;
	
	public void parseAsyn(HttpEventHandler<T> handler) {
		mHandler = handler;
		LocalDownloadTask downloadTask = new LocalDownloadTask();
		downloadTask.execute();
	}
	
	public T parseSync(){
		try {
			return AnalysisContent();
		} catch (IOException e) {
			ULog.e(TAG, "LocalXmlParse parse error.", e);
			return null;
		}
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
				if (mHandler != null) {
					mHandler.HttpFailHandler();
				}
			} else {
				if (mHandler != null) {
					mHandler.HttpSucessHandler(result);
				}
			}
		}
	}

	protected T AnalysisContent() throws IOException {
		//InputStream stream = mContext.getResources().getAssets().open(getFileName());;
		InputStream stream = new FileInputStream(new File(getFileName()));
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			MyContentHandler handler = new MyContentHandler();
			xr.setContentHandler(handler);
			InputSource inputSource = new InputSource(stream);
            xr.parse(inputSource);
            return handler.getContent();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	protected abstract String getFileName();
	
	protected abstract void xmlStartElement(String nodeName, T content, Attributes attributes) throws SAXException;
	
	protected abstract void xmlEndElement(String nodeName, String value, T content) throws SAXException;
	
	protected abstract T createContent();
	
	protected void XmlEndDocument() {
	}
	
	private class MyContentHandler extends DefaultHandler {
		
		private StringBuilder mValue;
		private T mContent;
		
		public T getContent() {
			return mContent;
		}

		@Override
		public void startDocument() throws SAXException {
			mContent = createContent();
			mValue = new StringBuilder(20);
		}

		@Override
		public void endDocument() throws SAXException {
			XmlEndDocument();
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			mValue.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			String name = localName;
			if (name.length() <= 0) {
	            name = qName;
	        }
			xmlEndElement(name, mValue.toString(), mContent);
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			String name = localName;
			if (name.length() <= 0) {
	            name = qName;
	        }
			xmlStartElement(name, mContent, attributes);
			mValue.delete(0, mValue.length());
		}
		
	}

}
