package com.shopping.ecommerce.controller;


import com.shopping.ecommerce.Exceptions.InvalidPhoneNumberException;
import com.shopping.ecommerce.Exceptions.InvalidUserNameException;
import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.Role;
import com.shopping.ecommerce.pojo.CustomerRequest;
import com.shopping.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/v2")
public class CustomerController {
    private final UserDetailsService userDetailsService;
    @Autowired
    CustomerService Cs;

    @PostMapping("/registerCustomer")
    public Customer registerCustomer(@Valid @RequestBody Customer customer) throws InvalidPhoneNumberException, InvalidUserNameException {
        if (customer.getCustomerPhoneNumber().toString().length() != 10) {
            throw new InvalidPhoneNumberException("Phone number length should be 10!");
        }


        //checking for special characters in customer's username
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(customer.getCustomerUserName());
        if (matcher.find()) {
            throw new InvalidUserNameException("User name cant have special characters");
        }
        return Cs.registerCustomer(customer);
    }

    @PostMapping("/customerPhone")
    public Customer getCustomerByPhone(@Valid @RequestBody Customer customer) {
        return Cs.getCustomerByPhoneNumber(customer.getCustomerPhoneNumber());
    }

    @GetMapping("/loginCustomer")
    public String loginCustomer(@RequestBody Customer customer) {
        if (Cs.loginCustomer(customer)) {
            return "Logged in successfully";
        }
        return "Cant login! Invalid credentials";
    }

    //  @PreAuthorize("hasAuthority('SCOPE_profile.read')")
    @GetMapping("/getCustomers")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Customer> getCustomer() {

        return Cs.getCustomers();
    }

    @PutMapping("/updateCustomerByUserName/{userName}")
    public Customer updateCustomerByUserName(@PathVariable("userName") String userName, @RequestBody Customer customer)
            throws Exception {
        return Cs.updateCustomerByUserName(userName, customer);
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return Cs.getCustomerById(id);
    }

    @GetMapping("/getCustomerByUserName/{userName}")
    public Customer getCustomerByUserName(@PathVariable("userName") String userName) {
        return Cs.getCustomerByUserName(userName);
    }

    @GetMapping("/")
    public String home() {
        return "welcome home customer!";
    }

    @PostMapping("/addRole")
    public ResponseEntity<Customer> assignRole(@RequestBody CustomerRequest request) throws InvalidUserNameException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/addRole").toUriString());
        return ResponseEntity.ok().body(Cs.addRoleToUser(request.getUserName(), request.getRole()));
    }

    @PostMapping("/insertRole")
    public ResponseEntity<Role> insertRole(@RequestBody Role request) throws InvalidUserNameException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/insertRole").toUriString());
        return ResponseEntity.created(uri).body(Cs.saveRole(request));
    }
}
