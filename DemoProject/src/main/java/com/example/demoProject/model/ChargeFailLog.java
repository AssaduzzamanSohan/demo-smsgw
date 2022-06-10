package com.example.demoProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "charge_fail_log")
public class ChargeFailLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sms_id")
	private Long smsId;

	@Column(name = "msisdn", columnDefinition = "varchar(15)")
	private String msisdn;

	@Column(name = "keyword_id")
	private int keywordId;

	@Column(name = "amount")
	private int amount;

	// TODO: just keeping default value -1, what if no charge code not found in DB,
	// this is a business decision
	@Column(name = "charge_id")
	private int charge_id = -1;

	@Column(name = "fail_code")
	private int failCode = -1;

	@Column(name = "ins_date")
	private Date insDate = new Date();

	@Column(name = "tidRef", columnDefinition = "TEXT")
	private String tidRef;

	@Column(name = "response", columnDefinition = "TEXT")
	private String response;

}
