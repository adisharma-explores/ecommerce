package com.shopping.ecommerce.service;


import com.shopping.ecommerce.Exceptions.InvalidUserNameException;
import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.Role;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface CustomerService {

    Customer registerCustomer(Customer customer);

    boolean loginCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer updateCustomerByUserName(String userName, @Valid Customer customer) throws Exception;

    Customer getCustomerById(int id);

    Customer getCustomerByUserName(String userName);
    Customer getCustomerByPhoneNumber(Long phoneNumber);
Role saveRole(Role role);
Customer addRoleToUser(String userName, String roleName) throws InvalidUserNameException;

}
