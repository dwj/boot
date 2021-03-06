/**
 * @describe 
 * @author duanwj
 * @since 2017年9月29日 上午11:09:58
 */
package com.sunland.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunland.domain.User;
import com.sunland.repository.UserRepository;

@RestController
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/hello")
	public String index() {
		logger.info("请求到了");
		return "Hello Word";

	}

	/**
	 * 查看所有用户信息
	 * 
	 * @return
	 */
	@GetMapping(value = "/users")
	public List<User> usersList() {

		return userRepository.findAll();
	}

	/**
	 * 查看单一用户信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/users/{id}")
	public User getUserFindOne(@PathVariable("id") Integer id) {

		return userRepository.findOne(id);

	}

	/**
	 * 增加一个用户
	 * 
	 * @param name
	 * @param phone
	 * @return
	 */
	@PostMapping(value = "/users")
	public User userAdd(@RequestParam("name") String name, @RequestParam("phone") Integer phone) {
		User user = new User();
		user.setUserName(name);
		user.setPhone(phone);
		return userRepository.save(user);

	}

	/**
	 * 更新一个用户信息
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @return
	 */
	@PutMapping(value = "/users/{id}")
	public User userUpdate(@PathVariable("id") Integer id, @RequestParam("name") String name,
			@RequestParam("phone") Integer phone) {
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setPhone(phone);
		return userRepository.save(user);
	}

	/**
	 * 删除一个用户
	 */
	@DeleteMapping(value = "/users/{id}")
	public void userDelete(@PathVariable("id") Integer id) {
		userRepository.delete(id);
	}
}
