package ru.geel.fastest.mvc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.entity.Event;
import ru.geel.fastest.mvc.entity.EventPost;
import ru.geel.fastest.mvc.jdbc.JDBCExample;
import ru.geel.fastest.mvc.orm.ORMService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivangeel on 25.03.17.
 */

@Controller
public class ShowEventController {

    @Autowired
    private ORMService ormService;
    @Autowired
    private JDBCExample jdbcExample;

    @RequestMapping(value = "/event{eventId}", method = RequestMethod.GET)
    public ModelAndView findEventById(@PathVariable int eventId, ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("ShowEventController: findEventById is called");

            List<User> userArrayList = jdbcExample.showAllParticipants(eventId);
            Event event = ormService.findEventById(eventId);
            String name = authentication.getName();
            User user = jdbcExample.queryUser(name);
            if(userArrayList.contains(user)){
                modelMap.addAttribute("contain", true);
            }
            else modelMap.addAttribute("contain", false);

            modelMap.addAttribute("event", event);
            return new ModelAndView("/event/event", "users", userArrayList);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/addparticipant", method =  RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addParticipant(@RequestBody EventPost event){
        System.out.println("addPArticipant");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = jdbcExample.queryUser(username);
            if(event.getCommand()==1) {
                ormService.addEventParticipant(event.getId(), user.getId());
            }
            if(event.getCommand()==0){
                ormService.deleteEventParticipant(event.getId(), user.getId());
            }
        }

        return "OK";
    }
}
