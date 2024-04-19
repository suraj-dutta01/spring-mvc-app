package org.jsp.usermvcapp.controller;

import org.jsp.usermvcapp.dao.userDao;
import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class userController {
	@Autowired
	private userDao userDao;
	
	@RequestMapping(value = "/open-register")
	public ModelAndView openRegister(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	@RequestMapping("/open-updateUser")
	public ModelAndView openUpdateUser(ModelAndView modelAndView) {
		modelAndView.setViewName("updateUser");
		modelAndView.addObject("user",new User() );
		return modelAndView;
	}
	@ResponseBody
	@PostMapping(value = "/updateUser")
	public String updateUser(@ModelAttribute(name = "user") User user) {
		user =userDao.updateUser(user);
		if(user!=null) {
			return "User Updated whose id is:"+user.getId();
		}
		return "User Failed To Update as user id not found";
	}
	@ResponseBody
	@PostMapping(value = "/register")
	public String saveUser(@ModelAttribute(name = "user") User user) {
		user=userDao.saveUser(user);
		return "User saved With id "+user.getId();
	}
	@GetMapping("/open-view")
	public String openView(@RequestParam String view) {
		return view;
	}
	@GetMapping("/find-by-id")
	public ModelAndView findById(@RequestParam(name = "id") int id,ModelAndView modelAndView) {
		User user=userDao.findById(id);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message","invalid user id");
		return modelAndView;
	}
	@GetMapping("/verify-user-by-id-password")
	public ModelAndView findUserByIdAndPass(@RequestParam(name = "id") int id,@RequestParam(name = "passeord") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUser(id, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Wrong User id and password");
		return modelAndView;	
	}
	@GetMapping("/verify-user-by-phone-password")
	public ModelAndView findUserByPhoneAndPass(@RequestParam(name = "phone") long phone,@RequestParam(name = "passeord") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUser(phone, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Wrong User Phone and password");
		return modelAndView;	
	}
	@GetMapping("/verify-user-by-email-password")
	public ModelAndView findUserByPhoneAndPass(@RequestParam(name = "email") String email,@RequestParam(name = "passeord") String password,ModelAndView modelAndView) {
		User user=userDao.verifyUser(email, password);
		if(user!=null) {
			modelAndView.setViewName("display");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Wrong User Email and password");
		return modelAndView;	
	}
	@GetMapping("/delete-user-by-id")
	public ModelAndView deleteUser(@RequestParam(name = "id") int id,ModelAndView modelAndView) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		boolean user=userDao.deleteUser(id);
		if(user) {
			modelAndView.setViewName("error");
			modelAndView.addObject("message", "User deleted successfully");
			return modelAndView;
		}
		modelAndView.setViewName("error");
		modelAndView.addObject("message", "Failed to delete user ");
		return modelAndView;
	}
}
