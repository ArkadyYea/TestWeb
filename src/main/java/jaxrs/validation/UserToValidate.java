package jaxrs.validation;

import javax.validation.constraints.NotEmpty;

public class UserToValidate {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String surname;
	
	public UserToValidate() {}

	public UserToValidate(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "UserToValidate [name=" + name + ", surname=" + surname + "]";
	}
	
	
}
