package ru.geel.fastest.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geel.fastest.mvc.bean.RUser;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivangeel on 30.03.17.
 */

@RestController
public class UsersSearchController {

    @Autowired
    JDBCExample jdbcExample;

    //по-хорошему надо бы User заменить на другую сущность - более легкую (имя, фамилия, город, id)

    @RequestMapping(value = "/all_users", method = RequestMethod.POST)
    public List<User> searchAllUsers(HttpServletRequest httpServletRequest){
        String search_query = httpServletRequest.getParameter("search_query").trim();
        System.out.println(search_query);
        List<User> users = null;
        if(!search_query.equals(""))
        {
            users = jdbcExample.queryUsers(search_query.split(" "));
            System.out.println(search_query.split(" ").toString());
            System.out.println(users);
        }

        return users;
    }
}
