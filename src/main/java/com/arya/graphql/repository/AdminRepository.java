package com.arya.graphql.repository;

import com.arya.graphql.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    boolean existsByUsername(String name);
}
