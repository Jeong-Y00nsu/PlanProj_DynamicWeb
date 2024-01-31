package com.member;

public class Member {
	String firstName;
	String lastName;
	String birth;
	String telephone_number;
	String id;
	
	public Member(){
		this.firstName=null;
		this.lastName=null;
		this.birth=null;
		this.telephone_number=null;
		this.id=null;
	}
	public Member(String firstName, String lastName, String brith, String telephone_number){
		this.firstName=firstName;
		this.lastName=lastName;
		this.birth=brith;
		this.telephone_number=telephone_number;
		this.id=null;
	}
}
