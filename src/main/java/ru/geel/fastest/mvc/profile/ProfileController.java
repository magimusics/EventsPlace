package ru.geel.fastest.mvc.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import java.util.List;

/**
 * Created by ivangeel on 04.02.17.
 */
@Controller
public class ProfileController {

    @Autowired
    JDBCExample jdbcExample;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView jdbcUser()
    {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            System.out.println("ProfileController jdbcAllUsers() is called");
            User user = jdbcExample.queryUser(name);
            System.out.println("User" + user + " is active!");
            return new ModelAndView("/security/profile", "userinfo", user);
        }
        catch (org.springframework.dao.EmptyResultDataAccessException e){
            System.out.println("org.springframework.dao.EmptyResultDataAccessException in ProfileController");
            return new ModelAndView("/security/profile", "userinfo", new User());
        }
    }
}
