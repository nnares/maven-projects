package nish.jpmc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/getDateAndTime")
    public ModelAndView getDateAndTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedDate = dtf.format(now);

        ModelAndView mv = new ModelAndView();
        mv.addObject("dateAndTime", formattedDate);
        mv.setViewName("date");
        return mv;
    }
}