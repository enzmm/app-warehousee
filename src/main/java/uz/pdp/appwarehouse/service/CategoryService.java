package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas", false);
                category.setParentCategory(optionalParentCategory.get());
            }
            categoryRepository.save(category);
            return new Result("Muaffaqiyatli saqlandi", true);
        }


    @DeleteMapping(value = "/byCategoryId/{categoryId}")
    public Result deleteCategory(@PathVariable Integer categoryId){
        categoryRepository.deleteById(categoryId);
        return new Result("delete",true);
    }

    @PutMapping("/byCategoryId/{categoryId}")
    public Result editCategory(@PathVariable Integer categoryId , @RequestBody CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setParentCategory(optionalCategory.get());
            categoryRepository.save(category);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Category> getCategorys()
    {
        return  categoryRepository.findAll();
    }
}
