package com.shopping.ecommerce.service;


import com.shopping.ecommerce.Exceptions.InvalidUserNameException;
import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.Role;
import com.shopping.ecommerce.repository.CustomerRepository;
import com.shopping.ecommerce.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    @Autowired
    CustomerRepository Cr;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Customer registerCustomer(Customer customer) {
        log.info("Saving new Customer");
        return Cr.save(customer);
    }

    @Override
    public boolean loginCustomer(Customer customer) {
        log.info("logging in Customer");

        Customer databaseObject = Cr.findByCustomerUserName(customer.getCustomerUserName());
        if (databaseObject == null) {
            return false;
        }
        if (databaseObject.getCustomerUserName().equals(customer.getCustomerUserName())
                && databaseObject.getCustomerPassword().equals(customer.getCustomerPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getCustomers() {
        log.info("get All Customer");

        return Cr.findAll();

    }

    //
    @Override
    public Customer updateCustomerByUserName(String userName, Customer customer) throws Exception {
        log.info("Updating Customer");

        Customer ans = null;
        Customer databaseObject = Cr.findByCustomerUserName(userName);
        if (databaseObject == null) throw new InvalidUserNameException("username doesnt exist");
        databaseObject.setCustomerUserName(customer.getCustomerUserName());
        databaseObject.setCustomerName(customer.getCustomerName());
        databaseObject.setCustomerPassword(customer.getCustomerPassword());
        databaseObject.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        System.out.println("updated the user name");
        return Cr.save(databaseObject);

    }

    //
    @Override
    public Customer getCustomerById(int id) {
        log.info("getting Customer by id");
        return Cr.findById(id).get();
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        log.info("getting Customer by username");
        return Cr.findByCustomerUserName(userName);
    }

    @Override
    public Customer getCustomerByPhoneNumber(Long phoneNumber) {
        log.info("getting Customer by phone number");
        return Cr.findByCustomerPhoneNumber(phoneNumber);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role");
        return roleRepo.save(role);
    }

    @Override
    public Customer addRoleToUser(String userName, String roleName) throws InvalidUserNameException {
        log.info("adding role to Customer");
        Customer customer = Cr.findByCustomerUserName(userName);
        Role role = roleRepo.findByName(roleName);
        if (customer == null || role == null) throw new InvalidUserNameException("username doesnt exist");

        customer.getRole().add(role);
        return Cr.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("getting Customer by username");
        Customer customer = Cr.findByCustomerUserName(username);
        if (customer == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("UserNot found");
        }
        List<SimpleGrantedAuthority> authorityCollections = new ArrayList<SimpleGrantedAuthority>();
        customer.getRole().forEach(role -> {
            authorityCollections.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(customer.getCustomerUserName(), customer.getCustomerPassword(), authorityCollections);

    }
}
