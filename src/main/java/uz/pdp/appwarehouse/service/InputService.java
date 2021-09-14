package uz.pdp.appwarehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        //Warehouse tekshirish
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("BUnday ombor mavjud emas", false);
        //Currency tekshirish
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Xato", false);

        //Supplier tekshirish
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Bunday ta'minlovchi mavjud emas", false);
        //Saqlash
        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode("1");//todo generatsiya qilishi kerak
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        inputRepository.save(input);
        return new Result("Kiritildi", true);
    }

    @DeleteMapping(value = "/byInputId/{inputId}")
    public Result deleteInput(@PathVariable Integer inputId){
        inputRepository.deleteById(inputId);
        return new Result("delete",true);
    }

    @PutMapping("/byInputId/{inputId}")
    public Result editInput(@PathVariable Integer inputId , @RequestBody InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(inputDto.getInputId());
        if (optionalInput.isPresent()) {
            Input input = optionalInput.get();
            input.setDate(inputDto.getDate());
            input.setCode(inputDto.getCode());
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setWarehouse(warehouseRepository.getById(inputDto.getWarehouseId()));
            input.setSupplier(supplierRepository.getById(inputDto.getSupplierId()));
            input.setCurrency(currencyRepository.getById(inputDto.getCurrencyId()));
            inputRepository.save(input);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Input> getInputs()
    {
        return  inputRepository.findAll();
    }
}