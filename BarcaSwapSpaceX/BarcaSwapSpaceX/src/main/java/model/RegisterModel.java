package model;

import java.time.LocalDate;

public class RegisterModel {
	/**
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param phone
	 * @param birthday
	 * @param country
	 * @param email
	 * @param role
	 */
	public RegisterModel(String firstName, String lastName, String userName, String phone, LocalDate birthday,
			String country, String email, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.country = country;
		this.email = email;
		this.role = role;

	}

	private String firstName;
	private String lastName;
	private String userName;
	private String phone;
	private LocalDate birthday;
	private String country;
	private String email;
	private String password;
	private String confirmPassword;
	private String role;

	/**
	 * @param imageUrlFromPart
	 */

	public RegisterModel() {
		super();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param phone
	 * @param birthday
	 * @param country
	 * @param email
	 * @param password
	 * @param confirmPassword
	 * @param role
	 * @param verification
	 */
	public RegisterModel(String firstName, String lastName, String userName, String phone, LocalDate birthday,
			String country, String email, String password, String confirmPassword, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phone = phone;
		this.birthday = birthday;
		this.country = country;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;

	}

}
