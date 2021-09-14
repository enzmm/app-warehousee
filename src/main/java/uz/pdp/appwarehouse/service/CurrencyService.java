package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;
    @PostMapping
    public Result addCurrencyService(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if(existsByName) {
            //Result result = new Result();
           // result.setMessage("Bunday pul birligi mavjud");
           // result.setSuccess(false);
            //bu 3ta qator kod pastagi 1qator kod bilan bir xil........
            return new Result("Bunday o'lchov birligi mavjud", false);
            //return result;
        }
        currencyRepository.save(currency);

      /*  Result result = new Result();
        result.setMessage("Muaffaqiyatli saqlandi");
        result.setSuccess(true);  */
         return new Result("Muaffaqiyatli saqlandi", true);
       // return result;
    }

    @DeleteMapping(value = "/byCurrencyId/{currencyId}")
    public Result deleteCurrency(@PathVariable Integer currencyId){
        currencyRepository.deleteById(currencyId);
        return new Result("delete",true);
    }

    @PutMapping("/byCurrencyId/{currencyId}")
    public Result editCurrency(@PathVariable Integer currencyId , @RequestBody Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currency.getCurrencyId());
        if (optionalCurrency.isPresent()) {
            Currency currency1 = optionalCurrency.get();
            currency1.setName(currency.getName());
            currencyRepository.save(currency1);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Currency> getCurrencys()
    {
        return  currencyRepository.findAll();
    }
}
