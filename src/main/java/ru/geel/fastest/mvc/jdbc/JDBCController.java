package ru.geel.fastest.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;

import java.util.List;

/**
 * Created by ivangeel on 24.01.17.
 */
@Controller
public class JDBCController {

    @Autowired JDBCExample jdbcExample;

    @RequestMapping(value = "/jdbc", method = RequestMethod.GET)
    public ModelAndView jdbcAllUsers()
    {
        System.out.println("JDBCController jdbcAllUsers() is called");
        List<User> users = jdbcExample.queryAllUsers();
        System.out.println(users+"IUYGVFCFYGU");
        return new ModelAndView("/jdbc/jdbc", "resultUser", users);
    }
}
