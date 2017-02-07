package ru.geel.fastest.mvc.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

/**
 * Created by ivangeel on 06.02.17.
 */

@Controller
public class SignupController {

    @Autowired
    JDBCExample jdbcExample;

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public ModelAndView greetingForm() {

        return new ModelAndView("/signup/signup", "user", new User());
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createNewUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String description,
                                ModelMap modelMap)
    {
        System.out.println("SignupController is working!");

        jdbcExample.addUser(username,password,description);
        jdbcExample.addUserAuthority(username);
        modelMap.addAttribute("username", username);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("description", description);

        return "/signup/newuser";

    }
}
