package pl.studia.bank.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        log.info("Custom Error");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}