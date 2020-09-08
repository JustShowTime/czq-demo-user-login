package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.example.demo.controller.other.ParameterValidator;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.vo.req.UserCreateReqVo;

import tk.mybatis.mapper.entity.Example;

/**
 * @author czq
 * @Date 2020-09-08 10:19:39
 */
@Service
public class UserService {

	@Autowired
	public UserDao userDao;

	public void createUser(UserCreateReqVo userCreateReqVo) {
		checkUser(userCreateReqVo);
		User user = JSON.parseObject(JSON.toJSONString(userCreateReqVo), User.class);
		int num = userDao.insertSelective(user);
		Assert.isTrue(num == 1, "插入失败,请重启输入");
	}

	/**
	 * 校验逻辑
	 * 
	 * @param userCreateReqVo
	 */
	public void checkUser(UserCreateReqVo userCreateReqVo) {
		String name = userCreateReqVo.getName();
		String password = userCreateReqVo.getPassword();
		ParameterValidator.validLoginName(name, false);
		ParameterValidator.validPassword(password, false);
		User userDemo = JSON.parseObject(JSON.toJSONString(userCreateReqVo), User.class);
		User user = userDao.selectOne(userDemo);
		Assert.isTrue(user == null, "该用户已存在");
	}

	/**
	 * 校验逻辑
	 * 
	 * @param name
	 */
	public User checkUser4Delete(String name) {
		User userDemo = new User();
		userDemo.setName(name);
		List<User> users = userDao.select(userDemo);
		Assert.isTrue(users != null, "该用户不存在");
		Assert.isTrue(users.size()<2, "该用户出现多个重复，请联系管理员");
		return users.get(0);
	}

	/**
	 * 校验逻辑
	 * 
	 * @param userCreateReqVo
	 */
	public void checkUser4Update(User user) {
		String name = user.getName();
		String password = user.getPassword();
		ParameterValidator.validLoginName(name, false);
		ParameterValidator.validPassword(password, false);
		User userDemo = userDao.selectByPrimaryKey(user.getId());
		Assert.isTrue(userDemo!=null, "该用户不存在");
	}

	public void deleteUser(String name) {

		User user = checkUser4Delete(name);
		int num = userDao.delete(user);
		Assert.isTrue(num == 1, "删除用户失败");
	}

	public void updateUser(User user) {
		checkUser4Update(user);
		int num = userDao.updateByPrimaryKey(user);
		Assert.isTrue(num == 1, "更新失败,请重启输入");
	}

	public List<User> selectUser(String name) {
		Example example = new Example(User.class);
		example.createCriteria().andLike("name", name);
		List<User> users = userDao.selectByExample(example);
		return users;
	}
}
