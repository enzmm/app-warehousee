package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.addOutputProduct(outputProductDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byOutputProductId/{outputProductId}")
    public Result deleteOutputProduct(@PathVariable Integer outputProductId) {
        Result result = outputProductService.deleteOutputProduct(outputProductId);
        return result;
    }

    //Update
    @PutMapping (value = "/byOutputProductId/{outputProductId}")
    public Result editOutputProduct(@PathVariable Integer outputProductId, @RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.editOutputProduct(outputProductId, outputProductDto);
        return result;
    }

    @GetMapping
    public List<OutputProduct> getOutputProducts() {
        List<OutputProduct> OutputProductList = outputProductService.getOutputProducts();
        return OutputProductList;
    }
}
