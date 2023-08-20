package fun.ciallo.blog.controller;

import fun.ciallo.blog.entity.Category;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.CategoryService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @Open(HttpMethod.GET)
    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.list();
    }
}
