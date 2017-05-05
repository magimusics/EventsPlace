package ru.geel.fastest.mvc.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.RUser;
import ru.geel.fastest.mvc.bean.SUser;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by ivangeel on 01.03.17.
 */
@RestController
public class SettingsController {

    @Autowired
    JDBCExample jdbcExample;

    private String currentUserName;

    @RequestMapping(value = "/sendajaxdata", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String ajaxResponse(@RequestBody SUser sUser)
    {
        Date bDate = new Date(sUser.getbYear(), sUser.getbMonth()-1, sUser.getbDay());
        jdbcExample.updateSettings(sUser, currentUserName, bDate);
        System.out.println(currentUserName);
        System.out.println(sUser);

        return "OK!";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView getUserSettings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            User user = new User();
            user = jdbcExample.queryUser(currentUserName);
            return new ModelAndView("/settings/settings", "user", user);
        }
        return new ModelAndView("redirect:/");
    }
}
