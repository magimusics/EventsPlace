package ru.geel.fastest.mvc.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.Message;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ivangeel on 14.04.17.
 */

@Controller
public class MessageController {

    @Autowired
    JDBCExample jdbcExample;

    private final Map<Integer, DeferredResult<Message>> messageContainer = new ConcurrentHashMap<Integer, DeferredResult<Message>>();

    @RequestMapping(value = "/send{id}", method = RequestMethod.GET)
    public ModelAndView messageExchange(@PathVariable int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(!name.equals("anonymousUser")) {
            User user = jdbcExample.queryUser(name);

            return new ModelAndView("/messages/send", "user", user);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ModelAndView showAllDialogs(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(!name.equals("anonymousUser")) {
            User user = jdbcExample.queryUser(name);

            return new ModelAndView("/messages/messages", "user", user);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public Message sendMessages(@RequestParam String message, @RequestParam String userTo){

        Message userMessage = new Message();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        userMessage.setUsername(user);
        userMessage.setMes(message);

        try {
            messageContainer.get(Integer.parseInt(userTo.substring(5))).setResult(userMessage);
        }
        catch (NullPointerException e){System.err.println("ОШИБОЧКА" + userTo.substring(5));}

        for(Map.Entry<Integer, DeferredResult<Message>> f:messageContainer.entrySet()) {
            System.err.println("Chatrequests     " + f.getKey() + " " + f.getValue().getResult());
        }

        return userMessage;
    }

    @RequestMapping(value = "/mvc/chat", method=RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public DeferredResult<Message> getMessage() {
        final DeferredResult<Message> deferredResult = new DeferredResult<Message>(null, Collections.emptyList());
        Message message = new Message();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        //System.err.println("USERNAME " + user);
        if(!user.equals("anonymousUser")) {
            User username = jdbcExample.queryUser(user);
            messageContainer.put(username.getId(), deferredResult);
        }

        deferredResult.onCompletion(new Runnable() {
			@Override
			public void run() {
				messageContainer.remove(user);
			}
		});
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                deferredResult.setErrorResult("Something went wrong");
            }
        });
        System.err.println("User "+user+" MESSAGE       "+deferredResult.getResult());
        //while (deferredResult.getResult()==null){}
        return deferredResult;
    }
}
