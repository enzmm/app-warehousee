package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.UserWarehouse;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping
    public Result addSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("yetkazib berildi", true);
    }

    @DeleteMapping(value = "/bySupplierId/{supplierId}")
    public Result deleteSupplier(@PathVariable Integer supplierId){
        supplierRepository.deleteById(supplierId);
        return new Result("delete",true);
    }

    @PutMapping("/bySupplierId/{supplierId}")
    public Result editSupplier(@PathVariable Integer supplierId , @RequestBody SupplierDto supplierDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierDto.getSupplierId());
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setName(supplierDto.getName());
            supplier.setPhoneNumber(supplierDto.getPhoneNumber());
            supplierRepository.save(supplier);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Supplier> getSuppliers()
    {
        return  supplierRepository.findAll();
    }
}
