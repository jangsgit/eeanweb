package com.dae.eean.util;

import com.dae.eean.DTO.UserFormDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

public class UIUtils {

    UserFormDto userformDto = new UserFormDto();
    public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
                                          @RequestParam(value = "redirectUri", required = false) String redirectUri,
                                          @RequestParam(value = "method", required = false) Method method,
                                          Model model) {

        model.addAttribute("message", message);
        model.addAttribute("redirectUri", redirectUri);
        model.addAttribute("method", method);
//        model.addAttribute("params", params);

        return "utils/message-redirect";
    }

    public static String getElvDataSourceNm(){
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
        UserFormDto userformDto = (UserFormDto) httpSession.getAttribute("userformDto");
        return ((UserFormDto) httpSession.getAttribute("userformDto")).getDbnm();
    }
}
