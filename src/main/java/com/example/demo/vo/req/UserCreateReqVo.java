package com.example.demo.vo.req;

import com.alibaba.fastjson.JSON;

/**
 * @author   czq
 * @Date 2020-09-08 10:33:23    
 */
public class UserCreateReqVo {

    private String name;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
