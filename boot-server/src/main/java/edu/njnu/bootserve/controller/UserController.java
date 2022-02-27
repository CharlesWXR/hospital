package edu.njnu.bootserve.controller;

import edu.njnu.bootserve.pojo.Result;
import edu.njnu.bootserve.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/exclude/user")
public class UserController {
	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public Result tokenLogin(@PathVariable String token) {
		return Result.success(TokenUtil.getUserID(token));
	}


	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result getToken(@RequestParam("user_id")int userID) {
		String token = TokenUtil.sign(userID);
		return Result.success(token);
	}
}
