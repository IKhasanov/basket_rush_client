package ru.twoida.basket_rush.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	
	public static final String SECRET_KEY = "secretkey";
	public static final String LOGIN = "login";
	public static final String GENDER = "gender";
	public static final String ID = "_id";
	
	private String secretKey;
	private String login;
	private String gender;
	
	private String id;
	
	public void mapUser(JSONObject obj) throws JSONException {
		this.id = obj.getString(ID);
		this.gender = obj.getString(GENDER);
		this.login = obj.getString(LOGIN);
		this.secretKey = obj.getString(SECRET_KEY);
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
