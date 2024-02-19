package com.shopping.ecommerce.repository;

import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
Role findByName(String name);
}
