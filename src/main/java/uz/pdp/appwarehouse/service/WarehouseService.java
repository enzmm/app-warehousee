package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;
    @PostMapping
    public Result addWarehouseService(Warehouse warehouse) {
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if(existsByName) {
            return new Result("Bunday Warehouse mavjud", false);
        }
        warehouseRepository.save(warehouse);

         return new Result("Muaffaqiyatli saqlandi", true);
    }

    @DeleteMapping(value = "/byWarehouseId/{warehouseId}")
    public Result deleteWarehouse(@PathVariable Integer warehouseId){
        warehouseRepository.deleteById(warehouseId);
        return new Result("delete",true);
    }

    @PutMapping("/byWarehouseId/{warehouseId}")
    public Result editWarehouse(@PathVariable Integer warehouseId , @RequestBody Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse.getWarehouseId());
        if (optionalWarehouse.isPresent()) {
            Warehouse warehousee = optionalWarehouse.get();
            warehousee.setName(warehouse.getName());
            warehouseRepository.save(warehousee);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Warehouse> getWarehouses()
    {
        return  warehouseRepository.findAll();
    }
}
