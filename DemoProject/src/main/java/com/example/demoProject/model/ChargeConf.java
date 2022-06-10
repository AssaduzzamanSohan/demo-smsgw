package com.example.demoProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "charge_conf")
public class ChargeConf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "charge_code", columnDefinition = "varchar(20)")
	private String chargeCode;

	@Column(name = "price")
	private int price;

	@Column(name = "price_with_vat")
	private int priceWithVat;

	@Column(name = "validity")
	private int validity = 0;

}
