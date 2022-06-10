package com.example.demoProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "app_conf")
public class AppConf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "app_name", columnDefinition = "varchar(50)")
	private String appName;

	@Column(name = "number_of_thread")
	private int numberOfThread;

	@Column(name = "number_of_row")
	private int numberOfRow;

	@Column(name = "status")
	private int status;

	@Column(name = "last_start_time")
	private Date lastStartTime;

	@Column(name = "last_stop_time")
	private Date lastStopTime;

	@Column(name = "stop_by", columnDefinition = "varchar(20)")
	private String stopBy;

	@Column(name = "data_reload")
	private int dataReload = 0;

	@Column(name = "last_reload_time")
	private Date lastReloadTime;

	@PrePersist
	public void prePersist() {

	}

}
