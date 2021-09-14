package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.OutputRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

   @Autowired
   ClientRepository clientRepository;
   @Autowired
   CurrencyRepository currencyRepository;
   @Autowired
   WarehouseRepository warehouseRepository;
   @Autowired
   OutputRepository outputRepository;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
            //Clientni tekshirish
            Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
            if (!optionalClient.isPresent())
                return new Result("Client belgilanmadi", false);
            //currencyni tekshirish
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
            if (!optionalCurrency.isPresent())
                return new Result("Pul birligini kiriting", false);
            //warehouse tekshirish
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
            if (!optionalWarehouse.isPresent())
                return new Result("Warehouse belgilanmadi", false);

            //Saqlash
            Output output = new Output();
            output.setDate(outputDto.getDate());
            output.setFactureNumber(outputDto.getFactureNumber());
            output.setClient(optionalClient.get());
            output.setCurrency(optionalCurrency.get());
            output.setCode("1");//todo generatsiya qilishi kerak
            outputRepository.save(output);
            return new Result("yuborildi", true);
    }

    @DeleteMapping(value = "/byOutputId/{outputId}")
    public Result deleteOutput(@PathVariable Integer outputId){
        outputRepository.deleteById(outputId);
        return new Result("delete",true);
    }

    @PutMapping("/byOutputId/{outputId}")
    public Result editOutput(@PathVariable Integer outputId , @RequestBody OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(outputDto.getOutputId());
        if (optionalOutput.isPresent()) {
            Output output = optionalOutput.get();
            output.setDate(outputDto.getDate());
            output.setCode(outputDto.getCode());
            output.setFactureNumber(outputDto.getFactureNumber());
            output.setClient(clientRepository.getById(outputDto.getClientId()));
            output.setWarehouse(warehouseRepository.getById(outputDto.getWarehouseId()));
            output.setCurrency(currencyRepository.getById(outputDto.getCurrencyId()));
            outputRepository.save(output);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Output> getOutputs()
    {
        return  outputRepository.findAll();
    }
}
