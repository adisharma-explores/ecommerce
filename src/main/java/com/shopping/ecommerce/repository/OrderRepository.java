package com.shopping.ecommerce.repository;


import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer>{
	@Query(value = "select * from order_detail p where p.customer =?1",nativeQuery=true)
	List<OrderDetail> getOrdList(Customer customer);

	

}
