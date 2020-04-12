package com.example.miaosha;

import com.example.miaosha.dao.UserDOMapper;
import com.example.miaosha.dataobject.UserDO;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages  = {"com.example.miaosha"})
@MapperScan("com.example.miaosha.dao")
public class DemoApplication {

	@Autowired
	private UserDOMapper userDOMapper;

	@RequestMapping(path = "/")
	public String home(){
		UserDO userDO = userDOMapper.selectByPrimaryKey(1);
		return String.format("hello world, %s",userDO.getName());
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
