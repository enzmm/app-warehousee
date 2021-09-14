package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        Result result = outputService.addOutput(outputDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byOutputId/{outputId}")
    public Result deleteOutput(@PathVariable Integer outputId) {
        Result result = outputService.deleteOutput(outputId);
        return result;
    }

    //Update
    @PutMapping (value = "/byOutputId/{outputId}")
    public Result editOutput(@PathVariable Integer outputId, @RequestBody OutputDto outputDto) {
        Result result = outputService.editOutput(outputId,outputDto);
        return result;
    }

    @GetMapping
    public List<Output> getOutputs() {
        List<Output> outputList = outputService.getOutputs();
        return outputList;
    }
}
