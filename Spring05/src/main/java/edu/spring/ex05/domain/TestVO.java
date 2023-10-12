package edu.spring.ex05.domain;

public class TestVO {
	private String userId;
	private String email;
	private String fileName;
	
	public TestVO() {}

	public TestVO(String userId, String email, String fileName) {
		super();
		this.userId = userId;
		this.email = email;
		this.fileName = fileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "TestVO [userId=" + userId + ", email=" + email + ", fileName=" + fileName + "]";
	}
	
	
	
	
}
