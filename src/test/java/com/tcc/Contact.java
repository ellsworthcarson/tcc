package com.tcc;

public class Contact {
	public Contact(
			String firstname,
			String jobtitle,
			String email,
			String phone,
			String company,
			String company_size,
			String industry,
			String state,
			String message) 
	{
		this.firstname = firstname;
		this.jobtitle = jobtitle;
		this.email = email;
		this.phone = phone;
		this.company = company;
		this.company_size = company_size;
		this.industry = industry;
		this.state = state;
		this.message = message;
		
		if (this.email.equals("")) {
			this.email = "test@test.com";
		}
	}

	public String firstname = "";
	public String jobtitle = "";
	public String email = "";
	public String phone = "";
	public String company = "";
	public String company_size = "";
	public String industry = "";
	public String state = "";
	public String message = "";

}