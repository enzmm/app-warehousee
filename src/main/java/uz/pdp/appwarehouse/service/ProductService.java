package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if(existsByNameAndCategoryId) {
            return new Result("Bunday maxsulot ushbu katigoriyada mavjud", false);
        }
        //Categoriyani tekshirish
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("BUnday katigoriya mavjud emas", false);
        //Photoni tekshirish
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("BUnday rasm mavjud emas", false);

        //Measurmentni tekshirish
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("BUnday o'lchov birligi mavjud emas", false);

        //Saqlash
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");//todo generatsiya qilishi kerak
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Maxsulot saqlandi", true);


    }

    @DeleteMapping(value = "/byProductId/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){
        productRepository.deleteById(productId);
        return new Result("delete",true);
    }

    @PutMapping("/byProductId/{productId}")
    public Result editProduct(@PathVariable Integer productId , @RequestBody ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getProductId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setCode(productDto.getCode());
            product.setMeasurement(measurementRepository.getById( productDto.getMeasurementId()));
            product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
            product.setPhoto(attachmentRepository.getById(productDto.getPhotoId()));
            productRepository.save(product);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Product> getProducts()
    {
        return  productRepository.findAll();
    }
}
