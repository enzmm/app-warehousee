package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result("xaridingiz uchun rahmat", true);
    }

    @DeleteMapping(value = "/byClientId/{clientId}")
    public Result deleteClient(@PathVariable Integer clientId){
        clientRepository.deleteById(clientId);
        return new Result("delete",true);
    }

    @PutMapping("/byClientId/{clientId}")
    public Result editClient(@PathVariable Integer clientId , @RequestBody ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(clientDto.getClientId());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setName(clientDto.getName());
            client.setPhoneNumber(clientDto.getPhoneNumber());
            clientRepository.save(client);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Client> getClients()
    {
        return  clientRepository.findAll();
    }
}
