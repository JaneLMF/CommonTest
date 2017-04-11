package com.common.utlis;

import android.text.TextUtils;
import android.util.Log;

/**
 *
 * Log工具，类似android.util.Log�?tag自动产生，格�?
 * customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)�?
 * <p/>
 *
 * @author dongfang
 */
public class ULog {

	public static String	customTagPrefix	= "";

	private ULog() {}

	private static String TAG = "Jane";
	private static Boolean	ISDUG		= true;

	public static Boolean	isNetDug	= false;
	//只应单元测试代码中设置为ture，启动单元测试模式�?正常代码中不应操作该�?
	public static boolean	isUnitTest	= false;

	public static boolean	allowD		= true && ISDUG;
	public static boolean	allowE		= true && ISDUG;
	public static boolean	allowI		= true && ISDUG;
	public static boolean	allowV		= true && ISDUG;
	public static boolean	allowW		= true && ISDUG;
	public static boolean	allowWtf	= true && ISDUG;

	public static void init(String tag){
		TAG = tag;
	}

	public static Boolean getISDUG() {
		return ISDUG;
	}

	public static void setISDUG(Boolean iSDUG) {
		ISDUG = iSDUG;

		allowD = true && ISDUG;
		allowE = true && ISDUG;
		allowI = true && ISDUG;
		allowV = true && ISDUG;
		allowW = true && ISDUG;
		allowWtf = true && ISDUG;
	}

	private static String generateTag(StackTraceElement caller) {
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
		return tag;
	}

	private static StackTraceElement getCallerStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];
	}

	public static CustomLogger	customLogger;

	public interface CustomLogger {
		void d(String tag, String content);

		void d(String tag, String content, Throwable tr);

		void e(String tag, String content);

		void e(String tag, String content, Throwable tr);

		void i(String tag, String content);

		void i(String tag, String content, Throwable tr);

		void v(String tag, String content);

		void v(String tag, String content, Throwable tr);

		void w(String tag, String content);

		void w(String tag, String content, Throwable tr);

		void w(String tag, Throwable tr);

		void wtf(String tag, String content);

		void wtf(String tag, String content, Throwable tr);

		void wtf(String tag, Throwable tr);
	}

	private static String getContent(String tagStr, String content){
		return tagStr+": " + content;
	}

	public static void d(String tagStr, String content) {
		if (!allowD)
			return;
		String tag = getTAG();
		if (customLogger != null) {
			customLogger.d(tag, getContent(tagStr, content));
		}
		else {
			Log.d(tag, getContent(tagStr, content));
		}
	}

	private static String getTAG() {
		if(TAG != null){
			return TAG;
		}
		StackTraceElement caller = getCallerStackTraceElement();
		return generateTag(caller);
	}

	public static void d(String tag, String content, Throwable tr) {
		if (!allowD)
			return;
		String TAG = getTAG();

		if (customLogger != null) {
			customLogger.d(TAG, tag+": " + content, tr);
		}
		else {
			Log.d(TAG, tag+": " + content, tr);
		}
	}

	public static void e(String tagStr, String content) {
		if (!allowE)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.e(tag, getContent(tagStr, content));
		}
		else {
			Log.e(tag, getContent(tagStr, content));
		}
	}

	public static void e(String tagStr, String content, Throwable tr) {
		if (!allowE)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.e(tag, getContent(tagStr, content), tr);
		}
		else {
			Log.e(tag, getContent(tagStr, content), tr);
		}
	}

	public static void i(String tagStr, String content) {
		if (!allowI)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.i(tag, getContent(tagStr, content));
		}
		else {
			Log.i(tag, getContent(tagStr, content));
		}
	}

	public static void i(String tagStr, String content, Throwable tr) {
		if (!allowI)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.i(tag, getContent(tagStr, content), tr);
		}
		else {
			Log.i(tag, getContent(tagStr, content), tr);
		}
	}

	public static void v(String tagStr, String content) {
		if (!allowV)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.v(tag, getContent(tagStr, content));
		}
		else {
			Log.v(tag, getContent(tagStr, content));
		}
	}

	public static void v(String tagStr, String content, Throwable tr) {
		if (!allowV)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.v(tag, getContent(tagStr, content), tr);
		}
		else {
			Log.v(tag, getContent(tagStr, content), tr);
		}
	}

	public static void w(String tagStr, String content) {
		if (!allowW)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.w(tag, getContent(tagStr, content));
		}
		else {
			Log.w(tag, getContent(tagStr, content));
		}
	}

	public static void w(String tagStr, String content, Throwable tr) {
		if (!allowW)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.w(tag, getContent(tagStr, content), tr);
		}
		else {
			Log.w(tag, getContent(tagStr, content), tr);
		}
	}

	public static void w(Throwable tr) {
		if (!allowW)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.w(tag, tr);
		}
		else {
			Log.w(tag, tr);
		}
	}

	public static void wtf(String tagStr, String content) {
		if (!allowWtf)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.wtf(tag, getContent(tagStr, content));
		}
		else {
			Log.wtf(tag, getContent(tagStr, content));
		}
	}

	public static void wtf(String tagStr, String content, Throwable tr) {
		if (!allowWtf)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.wtf(tag, getContent(tagStr, content), tr);
		}
		else {
			Log.wtf(tag, getContent(tagStr, content), tr);
		}
	}

	public static void wtf(Throwable tr) {
		if (!allowWtf)
			return;
		String tag = getTAG();

		if (customLogger != null) {
			customLogger.wtf(tag, tr);
		}
		else {
			Log.wtf(tag, tr);
		}
	}

}
