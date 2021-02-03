package com.walab.coding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.walab.coding.model.GoalDTO;
import com.walab.coding.model.UserDTO;
import com.walab.coding.service.UserService;

/**
 * Handles requests for the application RecommendProblems page.
 */

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/registerUserinfo", method = RequestMethod.POST)
	@ResponseBody
	public void registerUserinfo(HttpServletRequest httpServeletRequest) {
		
		String name = httpServeletRequest.getParameter("name");
		String email = httpServeletRequest.getParameter("email");
		String nickName = httpServeletRequest.getParameter("nickName");
		String intro = httpServeletRequest.getParameter("intro");
		
		UserDTO u = new UserDTO();
		u.setName(name);
		u.setEmail(email);
		u.setNickName(nickName);
		u.setIntro(intro);
		
		int result = userService.createUserinfo(u);

	}
	
	@RequestMapping(value = "/registerUsergoal", method = RequestMethod.POST)
	@ResponseBody
	public void registerUsergoal(HttpServletRequest request, HttpServletRequest httpServeletRequest) throws ParseException {
		
		HttpSession session = request.getSession();
		UserDTO ud = (UserDTO) session.getAttribute("user");
		int userID = 0;
		userID = userService.readUserIDByEmail(ud.getEmail());
		session.setAttribute("user", ud);
		System.out.println(userID);
		if(userID > 0) {
			ud.setId(userID);
			session.setAttribute("user", ud);
//			mv.setView(new RedirectView("home",true));
		}
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String goal = httpServeletRequest.getParameter("goal");
		Date startDate = transFormat.parse(httpServeletRequest.getParameter("startDate"));
		Date endDate = transFormat.parse(httpServeletRequest.getParameter("endDate"));
		int goalNum = Integer.parseInt(httpServeletRequest.getParameter("goalNum"));
				
		GoalDTO g = new GoalDTO();
		g.setUserID(userID);
		g.setGoalNum(goalNum);
		g.setGoal(goal);
		g.setStartDate(startDate);
		g.setEndDate(endDate);

		int result = userService.createUsergoal(g);
				
	}
	@RequestMapping(value = "/dupCheck", method = RequestMethod.POST)
	@ResponseBody
	public int dupCheck(HttpServletRequest httpServeletRequest) throws ParseException {
		
		String nickName = httpServeletRequest.getParameter("nickName");
		int result = userService.readUserCountByNickname(nickName);

		return result;
	}
}