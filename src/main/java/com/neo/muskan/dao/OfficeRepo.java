package com.neo.muskan.dao;

import com.neo.muskan.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepo extends JpaRepository<Office, Long> {
}
