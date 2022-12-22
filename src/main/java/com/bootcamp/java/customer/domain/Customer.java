package com.bootcamp.java.customer.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = {"identityNumber"})
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "customer")
public class Customer {
	
	@Id
    private String id;
	
	@NotNull
	@Indexed(unique = true)
	private String identityNumber;
	
	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String businessName;
	
	@NotNull
	@Indexed(unique = true)
	private String email;
	
	@NotNull
	@Indexed(unique = true)
	private String phoneNumber;
	
	@NotNull
	private LocalDate birthday;
	
	@NotNull
	private String type;

}
