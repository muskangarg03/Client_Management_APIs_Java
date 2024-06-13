package com.neo.muskan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neo.muskan.entities.Client;
import com.neo.muskan.services.ClientServices;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	

    @Autowired
    private ClientServices clientServices;

    
    //Retrieve all clients API
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(
            @RequestParam(required = false) Long officeId,
            @RequestParam(required = false) String status) {
        List<Client> clients = clientServices.getAllClients(officeId, status);
        return ResponseEntity.ok(clients);
    }
    
    
    //Retrieve Client Using Id
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Optional<Client> client = clientServices.getClientById(clientId);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    // Create a new client
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client createdClient = clientServices.createClient(client);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    //Update a client using Client ID
    @PutMapping("/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable Long clientId, @RequestBody Client clientDetails) {
        try {
            Client updatedClient = clientServices.updateClient(clientId, clientDetails);
            return ResponseEntity.ok(updatedClient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    // Delete a client using ID
    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        try {
            clientServices.deleteClient(clientId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}







//package com.neo.muskan.controllers;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.neo.muskan.entities.Client;
//import com.neo.muskan.services.ClientServices;
//
//@RestController
//@RequestMapping("/api/clients")
//public class ClientController {
//
//    @Autowired
//    private ClientServices clientServices;
//
//    @GetMapping
//    public List<Client> getAllClients() {
//        return clientServices.getAllClients();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
//        Optional<Client> client = clientServices.getClientById(id);
//        if (client.isPresent()) {
//            return ResponseEntity.ok(client.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public Client createClient(@RequestBody Client client) {
//        return clientServices.createClient(client);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client clientDetails) {
//        Client updatedClient = clientServices.updateClient(id, clientDetails);
//        if (updatedClient != null) {
//            return ResponseEntity.ok(updatedClient);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
//        clientServices.deleteClient(id);
//        return ResponseEntity.noContent().build();
//    }
//}
