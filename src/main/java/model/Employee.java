package model;

import java.io.Serializable;

/**
 * Employee entity.
 *
 * @author A.Shporta
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = -232773389738031756L;

    private Integer userId;

    private Integer countOfViolation;

    private boolean fine;

    private String email;

    private String address;

    private String phone;

	private Integer companyId;

	private Integer verificationId;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(Integer verificationId) {
		this.verificationId = verificationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCountOfViolation() {
		return countOfViolation;
	}

	public void setCountOfViolation(Integer countOfViolation) {
		this.countOfViolation = countOfViolation;
	}

	public boolean isFine() {
		return fine;
	}

	public void setFine(boolean fine) {
		this.fine = fine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
