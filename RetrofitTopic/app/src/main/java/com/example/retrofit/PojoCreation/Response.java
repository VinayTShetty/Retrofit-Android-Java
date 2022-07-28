package com.example.retrofit.PojoCreation;

import java.util.List;

public class Response{
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}

/**
 *  This is Robo Pojo used to create POJO classes foe JSON response.
 *
 */
