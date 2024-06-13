package com.neo.muskan.services;

import com.neo.muskan.dao.ClientRepo;
import com.neo.muskan.dao.OfficeRepo;
import com.neo.muskan.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServices {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private OfficeRepo officeRepo;

    //To Get All Client Details
    public List<Client> getAllClients(Long officeId, String status) {
        if (officeId != null && status != null) {
            return clientRepo.findByOfficeIdAndStatus(officeId, Client.Status.valueOf(status));
        } else if (officeId != null) {
            return clientRepo.findByOfficeId(officeId);
        } else if (status != null) {
            return clientRepo.findByStatus(Client.Status.valueOf(status));
        } else {
            return clientRepo.findAll();
        }
    }

    
    // To Retrieve a Client using ID
    public Optional<Client> getClientById(Long id) {
        return clientRepo.findById(id);
    }

    
    // To create a new client
    public Client createClient(Client client) {
        if (!officeRepo.existsById(client.getOfficeId())) {
            throw new IllegalArgumentException("Office ID does not exist");
        }
        if (clientRepo.findByExternalId(client.getExternalId()).isPresent()) {
            throw new IllegalArgumentException("External ID must be unique");
        }
        return clientRepo.save(client);
    }

    // To update a client using ID
    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> clientOptional = clientRepo.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setFirstname(clientDetails.getFirstname());
            client.setLastname(clientDetails.getLastname());
            client.setExternalId(clientDetails.getExternalId());
            client.setStatus(clientDetails.getStatus());
            client.setActivationDate(clientDetails.getActivationDate());
            client.setOfficeId(clientDetails.getOfficeId());
            if (clientRepo.findByExternalId(clientDetails.getExternalId()).isPresent() && !client.getId().equals(id)) {
                throw new IllegalArgumentException("External ID must be unique");
            }
            return clientRepo.save(client);
        } else {
            throw new IllegalArgumentException("Client ID does not exist");
        }
    }

    // To delete a client using ID
    public void deleteClient(Long id) {
        if (!clientRepo.existsById(id)) {
            throw new IllegalArgumentException("Client ID does not exist");
        }
        clientRepo.deleteById(id);
    }
}
