package com.common.utlis;

import com.common.application.BaseApplication;

public class SizeUtils {
	
	static SizeUtils instance = null;
	
	float factScreenWidth, factScreenHeight ;
	private SizeUtils() {
		factScreenWidth = BaseApplication.pixelWidth ;
		factScreenHeight = BaseApplication.pixelHeight ;
	}
	
	public static SizeUtils getInstance(){
		if(null == instance){
			instance = new SizeUtils();
		}
		return instance;
	}
	
	/**
	 * @param hope
	 * @return textSize
	 */
	public float getTextS(int hope){
		//px => sp
		float ts = BaseApplication.dpiHeight / ((float)1080 / hope); 
		return ts;
	}
	
	private float getTextSize(int pixSize){
		//useless : px => px
		float ts = BaseApplication.pixelHeight / ((float)1080 / pixSize); 
		return ts;
	}
	
	/**
	 * @param hopeWidth
	 * @return screenWidth
	 */
	public int getWid(int hopeWidth){
		//px => dp
		int w = (int) (factScreenWidth / ((float)1920 / (float)hopeWidth));
		return w;
	}

	/**
	 * @param hopeHeight
	 * @return screenHeight
	 */
	public int getHei(int hopeHeight){
		//px => dp
		int h = ((int) (factScreenHeight / ((float)1080 / (float)hopeHeight)));
		return h;
	}
	
}
