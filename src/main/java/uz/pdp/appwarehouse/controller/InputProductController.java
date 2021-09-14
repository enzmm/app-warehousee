package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        Result result=inputProductService.addInputProduct(inputProductDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byInputProductId/{inputProductId}")
    public Result deleteInputProduct(@PathVariable Integer inputProductId) {
        Result result = inputProductService.deleteInputProduct(inputProductId);
        return result;
    }

    //Update
    @PutMapping (value = "/byInputProductId/{inputProductId}")
    public Result editInputProduct(@PathVariable Integer inputProductId, @RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.editInputProduct(inputProductId, inputProductDto);
        return result;
    }

    @GetMapping
    public List<InputProduct> getInputProducts() {
        List<InputProduct> inputProductList = inputProductService.getInputProducts();
        return inputProductList;
    }
}
