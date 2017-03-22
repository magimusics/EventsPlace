package ru.geel.fastest.mvc.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ivangeel on 06.02.17.
 */

@Controller
public class SignupController {

    @Autowired
    JDBCExample jdbcExample;

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String greetingForm(Model model)
    {
        System.out.println("SignupController's form is working!");
        model.addAttribute("user", new User());
        return "/signup/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute User user, @RequestParam String year, @RequestParam String month,
                                @RequestParam String day, Model model)
    {
        System.out.println("SignupController is working!");

        if(!user.isFull()) {
            model.addAttribute("user", user);
            model.addAttribute("fillError", 1);
            return "/signup/signup";
        }
        Date bdate = new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));

        user.setBdate(bdate);
        model.addAttribute("user", user);
        System.out.println("User has been set");


        jdbcExample.addUser(user);
        jdbcExample.addUserAuthority(user.getEmail());
        /*
        modelMap.addAttribute("username", fname);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("description", description);*/

        return "/signup/newuser";

    }
}
