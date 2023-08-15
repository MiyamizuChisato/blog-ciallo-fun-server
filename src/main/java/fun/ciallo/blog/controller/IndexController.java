package fun.ciallo.blog.controller;

import fun.ciallo.blog.security.Open;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Open
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasAuthority('archive:get')")
    @GetMapping("/get")
    public String get() {
        return "get";
    }

    @PreAuthorize("hasAuthority('all')")
    @GetMapping("/all")
    public String all() {
        return "all";
    }
}
