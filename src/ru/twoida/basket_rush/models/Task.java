package ru.twoida.basket_rush.models;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;

public class Task {

	public static final String TITLE = "title";
	public static final String PHOTO_NAME = "photo";
	public static final String COUNT = "count";
	
	private String title;
	private String count;
	private String photo_name;
	
	public void mapTask(JSONObject jsn) throws JSONException {
		System.out.println(jsn);
		this.title = jsn.getString(TITLE);
		System.out.println(this.title);
		if (jsn.has(COUNT)) {
		this.count = jsn.getString(COUNT);
		System.out.println(this.count);
		}
		if (jsn.has(PHOTO_NAME)) {
		this.photo_name = jsn.getString(PHOTO_NAME);
		System.out.println(this.photo_name);
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPhotoName() {
		return photo_name;
	}
	public void setPhoto(String photo_name) {
		this.photo_name = photo_name;
	}

}
