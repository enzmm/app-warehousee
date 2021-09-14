package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        Result result = warehouseService.addWarehouseService(warehouse);
        return result;
    }

    @DeleteMapping
    public Result deleteWarehouse(@PathVariable Integer userId) {
        Result result = warehouseService.deleteWarehouse(userId);
        return result;
    }

    //Update
    @PutMapping (value = "/byWarehouseId/{warehouseId}")
    public Result editWarehouse(@PathVariable Integer warehouseId, @RequestBody Warehouse warehouse) {
        Result result = warehouseService.editWarehouse(warehouseId, warehouse);
        return result;
    }

    @GetMapping
    public List<Warehouse> getWarehouses() {
        List<Warehouse> WarehouseList = warehouseService.getWarehouses();
        return WarehouseList;
    }
}
