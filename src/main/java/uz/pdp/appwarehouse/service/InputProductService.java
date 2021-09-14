package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;
    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        //Input tekshirish
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("BUnday ombor mavjud emas", false);
        //productni tekshirish
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Xato", false);

        //Saqlash
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());//todo generatsiya qilishi kerak
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Kiritildi", true);
    }

    @DeleteMapping(value = "/byInputProductId/{inputProductId}")
    public Result deleteInputProduct(@PathVariable Integer inputProductId){
        inputProductRepository.deleteById(inputProductId);
        return new Result("delete",true);
    }

    @PutMapping("/byInputProductId/{inputProductId}")
    public Result editInputProduct(@PathVariable Integer inputProductId , @RequestBody InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductDto.getInputProductId());
        if (optionalInputProduct.isPresent()) {
            InputProduct inputProduct = optionalInputProduct.get();
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setInput(inputRepository.getById(inputProductDto.getInputId()));
            inputProduct.setProduct(productRepository.getById(inputProductDto.getProductId()));
            inputProductRepository.save(inputProduct);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<InputProduct> getInputProducts()
    {
        return  inputProductRepository.findAll();
    }
}
