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
@Table(name = "outbox")
public class Outbox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sms_id")
	private Long smsId;

	@Column(name = "msisdn", columnDefinition = "varchar(15)")
	private String msisdn;

	@Column(name = "ins_date")
	private Date insDate = new Date();

	@Column(name = "sms_text", columnDefinition = "varchar(160)")
	private String smsText;

	@Column(name = "reply_addr", columnDefinition = "varchar(160)")
	private String replyAddr;

	@Column(name = "status")
	private char status = 'N';

	@Column(name = "send_date")
	private Date sendDate;

}
