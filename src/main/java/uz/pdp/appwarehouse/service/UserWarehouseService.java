package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserWarehouseDto;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.UserWarehouseRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserWarehouseService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    UserWarehouseRepository userWarehouseRepository;
    @PostMapping
    public Result addUserWarehouse(@RequestBody UserWarehouseDto userWarehouseDto) {
        //user tekshirish
        Optional<User> userOptional = userRepository.findById(userWarehouseDto.getUserId());
        if (!userOptional.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas", false);
        //warehouse tekshirish
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(userWarehouseDto.getWarehouseId());
        if (!warehouseOptional.isPresent()) {
            return new Result("Bnday warehouse mavjud emas", false);
        }
        return new Result("Saqlandi", true);
    }

    @DeleteMapping(value = "/byUserWarehouseId/{userWarehouseId}")
    public Result deleteUserWarehouse(@PathVariable Integer userWarehouseId){
        warehouseRepository.deleteById(userWarehouseId);
        return new Result("delete",true);
    }

    @PutMapping("/byUserWarehouseId/{userWarehouseId}")
    public Result editUserWarehouse(@PathVariable Integer userWarehouseId , @RequestBody UserWarehouseDto userWarehouseDto) {
        Optional<UserWarehouse> optionalUserWarehouse = userWarehouseRepository.findById(userWarehouseDto.getUserWarehouseId());
        if (optionalUserWarehouse.isPresent()) {
            UserWarehouse userWarehouse = optionalUserWarehouse.get();
            userWarehouse.setWarehouseId(userWarehouseDto.getWarehouseId());
            userWarehouse.setUserId(userWarehouseDto.getUserId());
            userWarehouseRepository.save(userWarehouse);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<UserWarehouse> getUserWarehouses()
    {
        return  userWarehouseRepository.findAll();
    }
}
