package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode("1");//todo generatsiya qilishi kerak
        userRepository.save(user);
        return new Result("Foydalanuvchi", true);
    }

    @PutMapping("/byUserId/{userId}")
    public Result editUser(@PathVariable Integer userId , @RequestBody UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setCode(userDto.getCode());
            userRepository.save(user);
        }
        return new Result("edit qilindi", true);
    }

    @DeleteMapping(value = "/byUserId/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        userRepository.deleteById(userId);
        return new Result("delete",true);
    }


    public List<User> getUsers()
    {
        return  userRepository.findAll();
    }
}

