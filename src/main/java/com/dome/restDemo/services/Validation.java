package com.dome.restDemo.services;

import com.dome.restDemo.Model.RestDomeException;

public class Validation {
	
	public final static void isValidEmail(String email) throws RestDomeException {
		String subStringOne = "", subStringTwo = "", subStringThree = "", temp = "";
		boolean firstAt = false , secondDot = false;
		
		for(int i = 0 ; i < email.length(); i++) {
			if(email.charAt(i) == '@') {
				firstAt = true;
				subStringOne = temp;
				temp = "";
			}
			else if(email.charAt(i) == '.') {
				secondDot = true;
				subStringTwo = temp;
				temp = "";
			}
			else {
				temp = temp + email.charAt(i);
			}
		}
		
		subStringThree = temp;
		
		if(!(resultOfString(subStringOne) && firstAt && resultOfString(subStringTwo) && secondDot && resultOfString(subStringThree)))
			throw new RestDomeException("Email is Not Valid should be like example@gmail.com or otherwise please try again .. !");
	}

	private static boolean resultOfString(String emailTemp) {
		if(emailTemp.length() == 0)
			return false;
		boolean result = emailTemp.charAt(0) >= 'a' && emailTemp.charAt(0) <= 'z';
		
		result = result || (emailTemp.charAt(0) >= 'A' && emailTemp.charAt(0) <= 'Z') ;
		
		return (!result) ? false : true;
	}
	
	
	
	public final static void isValidPhone(String phone) throws RestDomeException {
		String temp = "";
		
		for(int i = 0 ; i < Math.min(3, phone.length()); i++) {
			temp = temp +  phone.charAt(i);
		}
		if(!(phone.length() == 10 && temp.length() == 3 && (temp.equals("056") || temp.equals("059") )))
			throw new RestDomeException("Phone is Not Valid should be 10 charactars with prefix like (056- or 059-) please try again .. !");
		
	}
	
	public final static void isValidFirstName(String firstName) throws RestDomeException {
		if(!(firstName.length() > 3 && resultOfString(firstName)))
			throw new RestDomeException("First name should start with a-z or A-Z and more than Three Charactars .. !");
			
	}
	
	public final static void isValidID(int ID) throws RestDomeException {
		String id = Integer.toString(ID);
		boolean thereIsNotCharInId = true;
		
		for(int i = 0; i < id.length(); i++) {
			if(!(id.charAt(i) >= '0' && id.charAt(i) <= '9'))
			{
				thereIsNotCharInId = false;
				break;
			}
		}
		
		if(!thereIsNotCharInId)
			throw new RestDomeException("ID is Not Valid should be every char 0-9 please try again .. !");
		
		
	}
	
	public final static void isValidCourseName(String courseName) throws RestDomeException {
		if(!(courseName.length() > 0 && resultOfString(courseName)))
			throw new RestDomeException("Course Name is Not Valid should start with a-z or A-Z and not empty ... !");
	}
	
	
}
