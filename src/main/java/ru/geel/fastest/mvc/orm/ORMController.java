package ru.geel.fastest.mvc.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by ivangeel on 25.03.17.
 */

@Controller
public class ORMController {

    @Autowired
    private ORMService ormService;

}
