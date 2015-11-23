package com.drz.aniationset.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

public class UIUtils {

	// 根据资源id获取字符串
	public static String getString(Context context, int resId) {
		return context.getResources().getString(resId);
	}

	// 根据资源id获取字符串数组
	public static String[] getStringArray(Context context, int resId) {
		return context.getResources().getStringArray(resId);
	}

	// 根据图片资源ID获取Drawable对象
	public static Drawable getDrawable(Context context, int resId) {
		return context.getResources().getDrawable(resId);
	}

	// 根据颜色资源ID获取颜色的值
	public static int getColor(Context context, int resId) {
		return context.getResources().getColor(resId);
	}

	// 根据资源ID获取像素值
	public static int getDimen(Context context, int resId) {
		return context.getResources().getDimensionPixelSize(resId);
	}

	// 像素DIP转换
	public static int dip2px(Context context, int dip) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dip * density + 0.5f);

	}

	public static int px2dip(Context context, int px) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (px / density);

	}

	// 转换布局文件为View对象的方法
	public static View inflateView(Context context, int layoutId) {
		return View.inflate(context, layoutId, null);
	}

	public static ColorStateList getColorStateList(Context context, int ResId) {
		return context.getResources().getColorStateList(ResId);
	}

}
