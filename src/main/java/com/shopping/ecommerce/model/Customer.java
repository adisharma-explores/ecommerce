package com.shopping.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Size(min = 3, max = 20, message = "name should be within 3 to 20 characters")
    @Pattern(regexp = "^[A-Za-z]+([ ][a-zA-Z]+)*$", message = "there should be no special characters.")
    private String customerName;

    @Column(name = "phone_number")
    private Long customerPhoneNumber;

    @NotNull
    private Date customerRegisterDate = new Date();


    @Size(min = 3, max = 12, message = "username should be within 3 to 12 characters")
    private String customerUserName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>();

   private String customerPassword;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

}
