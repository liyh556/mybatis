package tk.mybatis.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.simple.model.SysUser;
import tk.mybatis.springboot.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("users/{id}")
	SysUser user(@PathVariable("id") Long id){
		return userService.findById(id);
	}
	
	@RequestMapping("users")
	List<SysUser> users(){
		return userService.findAll();
	}

	@RequestMapping("usersInterceptor")
	List<Map> selectAllUserTestInterceptor(){
		List<Map> mapList = userService.selectAllUserTestInterceptor();
		return mapList;
	}
}
