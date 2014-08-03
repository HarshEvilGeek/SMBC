package com.smbc.android;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

public class FetchComicContentTask extends AsyncTask<String, String, Object>{

	@Override
	protected Object doInBackground(String... params) {

		URL url;
		try {
			url = new URL(params[0]);
			String filesDir = params[1];
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url.toURI());
			HttpResponse resp = client.execute(get);

			String content = EntityUtils.toString(resp.getEntity());
			Document doc = Jsoup.parse(content);
			String comicUrlStr = doc.getElementById(SMBCConstants.COMIC_ID).getElementsByTag("img").attr("src");
			String afterComicUrlStr = doc.getElementById(SMBCConstants.AFTER_COMIC_ID).getElementsByTag("img").attr("src");
			String shareBlock = doc.getElementById("sharebar").getElementById("sharefix").select("script").toString();
			int numStart = shareBlock.lastIndexOf(SMBCConstants.ID_DIVIDER);
			int numEnd = shareBlock.indexOf("\'", numStart);
			String num = shareBlock.substring(numStart + 3, numEnd);
			
			URL comicUrl = new URL(comicUrlStr);
            URL afterComicUrl = new URL(afterComicUrlStr);
			
			File comicFile = new File(filesDir, "comic" + num);
			File afterComicFile = new File(filesDir, "afterComic" + num);
			
			FileUtils.copyURLToFile(comicUrl, comicFile, 50*60000, 5*60000);
			FileUtils.copyURLToFile(afterComicUrl, afterComicFile, 50*60000, 5*60000);
			
			System.out.println("size of files is " + comicFile.length() + " and " + afterComicFile.length());
			
			int comicNum = Integer.parseInt(num);
			
			Bitmap comicBitmap = BitmapFactory.decodeFile(comicFile.getAbsolutePath());
            Bitmap afterComicBitmap = BitmapFactory.decodeFile(afterComicFile.getAbsolutePath());
			SMBCComicContents contents = new SMBCComicContents(comicNum, comicBitmap, afterComicBitmap, "", "");
			
			return contents;
			
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		
	}
	
}
