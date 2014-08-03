package com.smbc.android;

import android.graphics.Bitmap;

public class SMBCComicContents {

	private int comicCount;
	private Bitmap comicBitmap;
	private Bitmap afterComicBitmap;
	private String comicPath;
	private String afterComicPath;

	public SMBCComicContents(int comicCount, Bitmap comicBitmap,
			Bitmap afterComicBitmap, String comicPath, String afterComicPath) {
		this.comicCount = comicCount;
		this.comicBitmap = comicBitmap;
		this.afterComicBitmap = afterComicBitmap;
		this.comicPath = comicPath;
		this.afterComicPath = afterComicPath;
	}

	public int getComicCount() {
		return comicCount;
	}

	public Bitmap getComicBitmap() {
		return comicBitmap;
	}

	public Bitmap getAfterComicBitmap() {
		return afterComicBitmap;
	}

	public String getComicPath() {
		return comicPath;
	}

	public String getAfterComicPath() {
		return afterComicPath;
	}

}
