package ru.geel.fastest.mvc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.entity.Event;
import ru.geel.fastest.mvc.entity.EventPost;
import ru.geel.fastest.mvc.jdbc.JDBCExample;
import ru.geel.fastest.mvc.orm.ORMService;

@Controller
public class CreateEventController {

    @Autowired
    private ORMService ormService;

    @Autowired
    private JDBCExample jdbcExample;
    private String currentUserName;
    private int userId;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createEventProcessing() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            User user = jdbcExample.queryUser(currentUserName);
            userId = user.getId();
            return new ModelAndView("/event/create", "user", user);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    EventPost createEvent(@RequestBody Event event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            event.setCreator(userId);
            System.out.println(event);
            int result = ormService.createEvent(event);
            if (result > 0) {
                EventPost eventPost = new EventPost();
                eventPost.setEvent("event"+ormService.selectLastEventId());
                return eventPost;
            }
            return null;
        }
        return null;
    }
}