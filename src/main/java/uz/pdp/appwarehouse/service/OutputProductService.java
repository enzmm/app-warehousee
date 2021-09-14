package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        //Output tekshirish
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Bunday ombor mavjud emas", false);
        //productni tekshirish
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Xato", false);

        //Saqlash
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProduct.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Mahsulot yuborildi", true);
    }

    @DeleteMapping(value = "/byOutputProductId/{outputProductId}")
    public Result deleteOutputProduct(@PathVariable Integer outputProductId){
        outputProductRepository.deleteById(outputProductId);
        return new Result("delete",true);
    }

    @PutMapping("/byOutputProductId/{outputProductId}")
    public Result editOutputProduct(@PathVariable Integer outputProductId , @RequestBody OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductDto.getOutputProductId());
        if (optionalOutputProduct.isPresent()) {
            OutputProduct outputProduct = optionalOutputProduct.get();
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProduct.setProduct(productRepository.getById(outputProductDto.getProductId()));
            outputProduct.setOutput(outputRepository.getById(outputProductDto.getOutputId()));
            outputProductRepository.save(outputProduct);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<OutputProduct> getOutputProducts()
    {
        return  outputProductRepository.findAll();
    }
}
