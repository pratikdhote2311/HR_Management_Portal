package com.hr.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee
{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int id;
	

	//Server site Validation
	
	
	
	@Column(name="Employee_Name", length=100)
	@NotNull
	@Size(min = 3, max=100, message = "Employee name must be between 3 to 100 characters ...")
	private String employeeName;
	
	
	@Email(message = "Please provide valide email")
	@NotNull(message = "Email is required")
	@NotBlank(message = "Email is required")
	@Column
	private String email;
	
	
	
	@NotNull(message = "Gender is required .")
	@Column
	private String gender;
	
	
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth required of this format.")
	@NotNull(message = "Date of birth is required .")
	@Column
	private String dateOfBirth;
	
	
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Join date required of this format.")
	@NotNull(message = "Join date is required .")
	@Column
    private String dateofJoin;
	
	
	
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be start with 6 to 9 , and max length should be 10 .")
	@Column
	private String mobileNumber;
	
	@Pattern(regexp = "^\\d{12}$", message = "Aadhar Number must be 12 characters .")
	@NotNull
	@Column
	private String aadhaarNumber;
	
	
	@Pattern(regexp = "^\\d{9,18}$", message = "Account Number must be between 9 to 18 digit .")
	@NotNull
	@Column
	private String accountNumber;
	
	
	@NotNull(message = "Department name is required .")
	@Size(min = 2, max = 100, message = "Deparment name must be between 2 to 100 .")
	@Column
	private String department;
	
	
	
	@Column
	private String designation;
	
	
	@Size(min=2, max=100, message="Name must be between 2 ti 100 digit ")
	@Column
	private String previousCompanyName;
	
	
    @NotNull(message="PF must be exactly 15 charcters")
	@NotBlank
	@Column
	private String pfNumber;
	
	
	@Column
	private double salary;
	
	
	@Size(min=10,max=1000, message = "Current Address must be between 10 to 1000 characters .")
	@Column
	private String currentAddress;
	
	
	@Size(min=10,max=1000,message = "Permanent Address must be between 10 to 1000 characters .")
	@Column
	private String permanentAddress;
	@Column
	private boolean active=true;
	
	
	
	// add this 3 varibale at the time of developing status page .
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	private String password;
	
	
	@NotNull(message = "Role is required .")
	@Size(min = 3,max = 10,message = "Role must be between 3 to 10 .")
	private String role;
	
	
	
	public Employee()
	{
		
	}



	public Employee(int id, String employeeName, String email, String gender, String dateOfBirth, String dateofJoin,
			String mobileNumber, String aadhaarNumber, String accountNumber, String department, String designation,
			String previousCompanyName, String pfNumber, double salary, String currentAddress, String permanentAddress,
			boolean active) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.email = email;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.dateofJoin = dateofJoin;
		this.mobileNumber = mobileNumber;
		this.aadhaarNumber = aadhaarNumber;
		this.accountNumber = accountNumber;
		this.department = department;
		this.designation = designation;
		this.previousCompanyName = previousCompanyName;
		this.pfNumber = pfNumber;
		this.salary = salary;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.active = active;
	}



	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getDateofJoin() {
		return dateofJoin;
	}



	public void setDateofJoin(String dateofJoin) {
		this.dateofJoin = dateofJoin;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getAadhaarNumber() {
		return aadhaarNumber;
	}



	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}



	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getPreviousCompanyName() {
		return previousCompanyName;
	}



	public void setPreviousCompanyName(String previousCompanyName) {
		this.previousCompanyName = previousCompanyName;
	}



	public String getPfNumber() {
		return pfNumber;
	}



	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}



	public String getCurrentAddress() {
		return currentAddress;
	}



	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}



	public String getPermanentAddress() {
		return permanentAddress;
	}



	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}

	
	


	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", email=" + email + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", dateofJoin=" + dateofJoin + ", mobileNumber=" + mobileNumber
				+ ", aadhaarNumber=" + aadhaarNumber + ", accountNumber=" + accountNumber + ", department=" + department
				+ ", designation=" + designation + ", previousCompanyName=" + previousCompanyName + ", pfNumber="
				+ pfNumber + ", salary=" + salary + ", currentAddress=" + currentAddress + ", permanentAddress="
				+ permanentAddress + ", active=" + active + "]";
	}
	
	
	
	
	
	

}
