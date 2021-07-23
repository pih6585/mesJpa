package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AddressTest {

	@DisplayName("주소정보를 입력하면 주소가 생성된다.")
	@ParameterizedTest
	@CsvSource(value = {"서울,천중로,11-11","서울,암사동,22-2"})
	public void create_address(String city, String street, String zipCode){
		Address address = new Address(city,street,zipCode);

		assertThat(address.getCity()).isEqualTo(city);
		assertThat(address.getStreet()).isEqualTo(street);
		assertThat(address.getZipcode()).isEqualTo(zipCode);

	}
}