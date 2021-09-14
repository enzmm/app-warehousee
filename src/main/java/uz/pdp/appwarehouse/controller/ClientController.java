package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDto;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("client")
public class
ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto) {
        Result result = clientService.addClient(clientDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byClientId/{clientId}")
    public Result deleteClient(@PathVariable Integer clientId) {
        Result result = clientService.deleteClient(clientId);
        return result;
    }

    //Update
    @PutMapping (value = "/byClientId/{clientId}")
    public Result editClient(@PathVariable Integer clientId, @RequestBody ClientDto clientDto) {
        Result result = clientService.editClient(clientId, clientDto);
        return result;
    }

    @GetMapping
    public List<Client> getClients() {
        List<Client> clientList = clientService.getClients();
        return clientList;
    }
}
