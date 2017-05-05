package ru.geel.fastest.mvc.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.image.ImageUploadController;
import ru.geel.fastest.mvc.jdbc.JDBCExample;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ivangeel on 04.02.17.
 */
@Controller
public class ProfileController {

    @Autowired
    JDBCExample jdbcExample;

    @RequestMapping(value = "/profile{userId}", method = RequestMethod.GET)
    public ModelAndView jdbcUser(@PathVariable Integer userId, ModelMap modelMap)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(!name.equals("anonymousUser")) {
            System.out.println("ProfileController jdbcUser() is called");
            System.out.println("USER id " + userId);

            User user = jdbcExample.queryUser(name);
            User userinfo = jdbcExample.queryUserdyId(userId);
            modelMap.addAttribute("user", user);

            return new ModelAndView("/security/profile", "userinfo", userinfo);
        }
        else return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getUserProfile()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String name = authentication.getName();
            User user = jdbcExample.queryUser(name);
            return new ModelAndView("/security/profile", "userinfo", user);
        }
        else return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public @ResponseBody ModelAndView imageUpload(@RequestParam("file") MultipartFile file)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        ImageUploadController imageUploadController = new ImageUploadController(file, name);

        if(name!="anonymousUser") {

            User user = jdbcExample.queryUser(name);
            String filePath = imageUploadController.setPhotoIntoDB();
            jdbcExample.updatePhoto(name, filePath);
            return new ModelAndView("/security/profile", "userinfo", user);
        }
        return new ModelAndView("redirect:/");
    }
}
