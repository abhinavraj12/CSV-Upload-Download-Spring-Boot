package com.csv.upload.download.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// This is the user details class, the user data is same as the .csv file data
@Entity
public class User {

	@Id
	@Column(name = "id")
	private Integer id;


	private String namePrefix;
	private String firstName;
	private String lastName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User() {
		super();

	}

	public User(Integer id, String namePrefix, String firstName, String lastName) {
		super();
		this.id = id;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", namePrefix=" + namePrefix + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
