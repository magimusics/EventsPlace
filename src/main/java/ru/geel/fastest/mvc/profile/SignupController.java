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
    public String greetingForm() {

        return "/signup/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createNewUser(@RequestParam String fname, @RequestParam String lastname, @RequestParam int month,
                                @RequestParam String password, @RequestParam String sex, @RequestParam String day,
                                @RequestParam String description, @RequestParam String year, @RequestParam String email,
                                @RequestParam String photo, @RequestParam String country, @RequestParam String city,
                                @RequestParam String occupation, ModelMap modelMap)
    {
        Date bdate = new Date(Integer.parseInt(year), month, Integer.parseInt(day));

        if(photo==null)
            return "/signup/signup";
        System.out.println("SignupController is working!");

        jdbcExample.addUser(fname, lastname, password,description, (sex=="male"?true:false), bdate, email, "", country, city, occupation);
        jdbcExample.addUserAuthority(fname);
        modelMap.addAttribute("username", fname);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("description", description);

        return "/signup/newuser";

    }
}
