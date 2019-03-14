package vip.codemonkey.security.simple.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang zhengtao
 */
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("me")
    @PreAuthorize("hasAuthority('user:me')")
    public Object me(){return SecurityContextHolder.getContext().getAuthentication(); }
}
