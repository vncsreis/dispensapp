package com.dev.dispensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dispensa.models.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {

}
