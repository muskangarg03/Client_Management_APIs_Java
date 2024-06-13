package com.neo.muskan.dao;

import com.neo.muskan.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByExternalId(String externalId);
    List<Client> findByOfficeId(Long officeId);
    List<Client> findByStatus(Client.Status status);
    List<Client> findByOfficeIdAndStatus(Long officeId, Client.Status status);
}





//package com.neo.muskan.dao;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.neo.muskan.entities.Client;
//
//public interface ClientRepo extends JpaRepository<Client, Integer> {
//}
