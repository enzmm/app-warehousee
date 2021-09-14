package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        Result result = inputService.addInput(inputDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byInputId/{inputId}")
    public Result deleteInput(@PathVariable Integer inputId) {
        Result result = inputService.deleteInput(inputId);
        return result;
    }

    //Update
    @PutMapping (value = "/byInputId/{inputId}")
    public Result editInput(@PathVariable Integer inputId, @RequestBody InputDto inputDto) {
        Result result = inputService.editInput(inputId, inputDto);
        return result;
    }

    @GetMapping
    public List<Input> getInputs() {
        List<Input> inputList = inputService.getInputs();
        return inputList;
    }
}