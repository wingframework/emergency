package com.example.demo.Cofigs;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionController {
    @ExceptionHandler(value = Exception.class)
    public Rtn<Object> allExpetion(Exception e) {
        Rtn rtn = new Rtn<Object>();
        rtn.setMsg(e.toString());
        rtn.setOk(false);
        return rtn;
    }
}
