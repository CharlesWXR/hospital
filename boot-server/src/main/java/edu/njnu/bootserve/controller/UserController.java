package edu.njnu.bootserve.controller;

import edu.njnu.bootserve.pojo.Result;
import edu.njnu.bootserve.service.PatientService;
import edu.njnu.bootserve.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/exclude/user")
public class UserController {
	@Autowired
	private PatientService patientService;

	/*
	请求方式：GET
	请求参数：路径参数 /
	响应数据：response.data {
		code：状态码,
		result：载荷 Patient[]
	}
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result getAllPatients() {
		return Result.success(patientService.getAllPatients());
	}

	/*
	请求方式：GET
	请求参数：路径参数 /用户token
	响应数据：response.data {
		code：状态码,
		result：载荷 user_id
	}
	*/
	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public Result tokenLogin(@PathVariable String token) {
		return Result.success(TokenUtil.getUserID(token));
	}

	/*
	请求方式：POST
	请求参数：param参数 {user_id: int}
	响应数据：response.data {
		code：状态码,
		result：载荷 token
	}
	*/
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result getToken(@RequestParam("user_id")int userID) {
		String token = TokenUtil.sign(userID);
		return Result.success(token);
	}
}
