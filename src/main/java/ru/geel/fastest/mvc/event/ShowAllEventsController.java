package ru.geel.fastest.mvc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.entity.Event;
import ru.geel.fastest.mvc.orm.ORMService;

import java.util.List;

/**
 * Created by ivangeel on 27.03.17.
 */

@Controller
public class ShowAllEventsController {

    @Autowired
    private ORMService ormService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView findEventById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("ShowEventController: findEventById is called");
            List<Event> eventList = ormService.findAllEvents();

            return new ModelAndView("/event/events", "eventList", eventList);
        }
        return new ModelAndView("redirect:/");
    }
}
