package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currrency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        Result result = currencyService.addCurrencyService(currency);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byCurrencyId/{currencyId}")
    public Result deleteCurrency(@PathVariable Integer currencyId) {
        Result result = currencyService.deleteCurrency(currencyId);
        return result;
    }

    //Update
    @PutMapping (value = "/byCurrencyId/{currencyId}")
    public Result editCurrency(@PathVariable Integer currencyId, @RequestBody Currency currency) {
        Result result = currencyService.editCurrency(currencyId, currency);
        return result;
    }

    @GetMapping
    public List<Currency> getCurrencys() {
        List<Currency> currencyList = currencyService.getCurrencys();
        return currencyList;
    }
}

