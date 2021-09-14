package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    //CategoryController CategoryServicni chaqiradi
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byCategoryId/{categoryId}")
    public Result deleteCategory(@PathVariable Integer categoryId) {
        Result result = categoryService.deleteCategory(categoryId);
        return result;
    }

    //Update
    @PutMapping (value = "/byCategoryId/{categoryId}")
    public Result editCategory(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) {
        Result result = categoryService.editCategory(categoryId, categoryDto);
        return result;
    }

    @GetMapping
    public List<Category> getCategorys() {
        List<Category> categoryList = categoryService.getCategorys();
        return categoryList;
    }
}
