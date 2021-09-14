package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.UserWarehouse;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.payload.UserWarehouseDto;
import uz.pdp.appwarehouse.service.UserWarehouseService;

import java.util.List;

@RestController
@RequestMapping("userWarehouse")
public class UserWarehouseController {

    @Autowired
    UserWarehouseService userWarehouseService;

    @PostMapping
    public Result addUserWarehouse(@RequestBody UserWarehouseDto userWarehouseDto){
        Result result = userWarehouseService.addUserWarehouse(userWarehouseDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byUserWarehouseId/{userWarehouseId}")
    public Result deleteUserWarehouse(@PathVariable Integer userWarehouseId) {
        Result result = userWarehouseService.deleteUserWarehouse(userWarehouseId);
        return result;
    }

    //Update
    @PutMapping (value = "/byUserWarehouseId/{userWarehouseId}")
    public Result editUserWarehouse(@PathVariable Integer userWarehouseId, @RequestBody UserWarehouseDto userWarehouseDto) {
        Result result = userWarehouseService.editUserWarehouse(userWarehouseId,userWarehouseDto);
        return result;
    }
    @GetMapping
    public List<UserWarehouse> getUserWarehouses() {
        List<UserWarehouse> UserWarehouseList = userWarehouseService.getUserWarehouses();
        return UserWarehouseList;
    }
}
