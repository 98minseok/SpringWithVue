package backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {
	private Long id;
	private String name;
	private String nickname;
	
	@JsonProperty("login_id")
	private String loginId;
	
	private String password;
	private String email;
	
	public Member() {
	}
	public Member(String name, String nickname, String loginId, String password, String email) {
		this.name = name;
		this.nickname = nickname;
		this.loginId = loginId;
		this.password = password;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
