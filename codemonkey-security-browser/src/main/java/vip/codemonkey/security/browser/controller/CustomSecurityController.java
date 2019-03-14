package vip.codemonkey.security.browser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.codemonkey.security.core.constant.SecurityConstant;
import vip.codemonkey.security.core.dto.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wang zhengtao
 */
@RestController
public class CustomSecurityController {
    private Logger logger = LoggerFactory.getLogger(CustomSecurityController.class);

    @RequestMapping(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response){
        return  SimpleResponse.error("请求需要登陆认证,请先登陆再请求", HttpStatus.UNAUTHORIZED.value());
    }
}
