package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        Result result = userService.addUser(userDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byUserId/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        Result result = userService.deleteUser(userId);
        return result;
    }

    //Update
    @PutMapping (value = "/byUserId/{userId}")
    public Result editUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        Result result = userService.editUser(userId,userDto);
        return result;
    }

    @GetMapping
    public List<User> getUsers() {
        List<User> userList = userService.getUsers();
        return userList;
    }
}

