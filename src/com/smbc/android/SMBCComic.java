package com.smbc.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SMBCComic {

    private long id;
    private String comicFilePath;
    private String afterComicFilePath;

    private Bitmap comicBitmap;
    private Bitmap afterComicBitmap;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getComicFilePath()
    {
        return comicFilePath;
    }

    public void setComicFilePath(String comicFilePath)
    {
        this.comicFilePath = comicFilePath;
    }

    public String getAfterComicFilePath()
    {
        return afterComicFilePath;
    }

    public void setAfterComicFilePath(String afterComicFilePath)
    {
        this.afterComicFilePath = afterComicFilePath;
    }

    public Bitmap getComicBitmap()
    {
        return comicBitmap;
    }

    public void setComicBitmap(Bitmap comicBitmap)
    {
        this.comicBitmap = comicBitmap;
    }

    public Bitmap getAfterComicBitmap()
    {
        return afterComicBitmap;
    }

    public void setAfterComicBitmap(Bitmap afterComicBitmap)
    {
        this.afterComicBitmap = afterComicBitmap;
    }

    public ContentValues toContentValues()
    {
        ContentValues values = new ContentValues();

        values.put(SMBCConstants.COLUMN_ID, id);
        values.put(SMBCConstants.COLUMN_COMIC_PATH, comicFilePath);
        values.put(SMBCConstants.COLUMN_AFTER_COMIC_PATH, afterComicFilePath);

        return values;
    }

    public static SMBCComic loadFromCursor(Cursor cursor)
    {
        SMBCComic comic = new SMBCComic();
        comic.id = cursor.getLong(cursor.getColumnIndex(SMBCConstants.COLUMN_ID));
        comic.comicFilePath = cursor.getString(cursor.getColumnIndex(SMBCConstants.COLUMN_COMIC_PATH));
        comic.afterComicFilePath = cursor.getString(cursor.getColumnIndex(SMBCConstants.COLUMN_AFTER_COMIC_PATH));
        comic.comicBitmap = BitmapFactory.decodeFile(comic.comicFilePath);
        comic.afterComicBitmap = BitmapFactory.decodeFile(comic.afterComicFilePath);
        return comic;
    }
}
