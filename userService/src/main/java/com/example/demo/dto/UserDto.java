package com.example.demo.dto;

import java.io.Serializable;

/**
 * 
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

    /**
     * 登录名
     */
    private String username;
    
    //用户头像
    private String avatar; 
    
    /**
     * 密码
     */
    private String password;

    public UserDto() {
    	
    }
    
	public UserDto(Long id, String username, String avatar, String password) {
		super();
		this.id = id;
		this.username = username;
		this.avatar = avatar;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
