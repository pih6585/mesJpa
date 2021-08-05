package com.mes.jpa.repository;

import static org.assertj.core.api.Assertions.*;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.mes.jpa.entity.Address;
import com.mes.jpa.entity.Customer;


@Transactional
@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@DisplayName("거래처정보를 생성한다.")
	public void createCustomer(){
		Customer customer = Customer.createCustomer("네이버", "홍길동", new Address("서울", "천중로", "11-1"));

		Customer saveCustomer = customerRepository.save(customer);

		entityManager.clear();
		entityManager.flush();

		entityManager.find(Customer.class,saveCustomer.getId());

		assertThat(saveCustomer.getCustomerName()).isEqualTo(customer.getCustomerName());
		assertThat(saveCustomer.getDirector()).isEqualTo(customer.getDirector());
		assertThat(saveCustomer.getAddress()).isEqualTo(customer.getAddress());

	}

	@Test
	@DisplayName("거래처 정보를 수정하면 수정된다.")
	public void updateCustomer(){
		Customer customer = Customer.createCustomer("네이버", "홍길동", new Address("서울", "천중로", "11-1"));

		Customer saveCustomer = customerRepository.save(customer);

		Customer updateCustomer = Customer.updateCustomer(saveCustomer.getId(), "카카오", "손오공",
			new Address("서울", "천중로", "11-1"));
		entityManager.find(Customer.class,saveCustomer.getId());

		assertThat(updateCustomer.getCustomerName()).isEqualTo("카카오");
		assertThat(updateCustomer.getDirector()).isEqualTo("손오공");
		assertThat(updateCustomer.getAddress()).isEqualTo(saveCustomer.getAddress());
	}
}