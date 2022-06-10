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
@Table(name = "keyword_details")
public class KeywordDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "keyword", columnDefinition = "varchar(20)")
	private String keyword;

	@Column(name = "game_name", columnDefinition = "varchar(200)")
	private String gameName;

	@Column(name = "sms_spliter", columnDefinition = "varchar(1)")
	private String smsSpliter;

	@Column(name = "unlockurl", columnDefinition = "varchar(1000)")
	private String unlockUrl;

	@Column(name = "unlockurl_response_splitter", columnDefinition = "varchar(1)")
	private String unlockUrlResponseSplitter;

	@Column(name = "unlock_sms", columnDefinition = "varchar(160)")
	private String unlockSms;

	@Column(name = "chargeurl", columnDefinition = "varchar(1000)")
	private String chargeUrl;

	@Column(name = "ins_date")
	private Date insDate = new Date();

}
