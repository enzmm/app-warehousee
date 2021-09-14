package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/suplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplie(@RequestBody SupplierDto supplierDto){
        Result result = supplierService.addSupplier(supplierDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/bySupplierId/{supplierId}")
    public Result deleteSupplier(@PathVariable Integer supplierId) {
        Result result = supplierService.deleteSupplier(supplierId);
        return result;
    }

    //Update
    @PutMapping (value = "/bySupplierId/{supplierId}")
    public Result editSupplier(@PathVariable Integer supplierId, @RequestBody SupplierDto supplierDto) {
        Result result = supplierService.editSupplier(supplierId, supplierDto);
        return result;
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        List<Supplier> supplierList = supplierService.getSuppliers();
        return supplierList;
    }
}
