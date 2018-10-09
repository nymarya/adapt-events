/**
 * 
 */
package br.com.adapt.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author jainebudke
 *
 */
@Entity
@Table(name = "free_blocks")
public class Freeblock implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@DateTimeFormat(pattern = "H:m")
	@Column(name = "start_date")
	private LocalTime startDate;

	@DateTimeFormat(pattern = "H:m")
	@Column(name = "end_date")
	private LocalTime endDate;
	
	@ManyToOne
	@JoinColumn(name = "scheduler_id")
	private Scheduler scheduler;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}

	public LocalTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalTime endDate) {
		this.endDate = endDate;
	}


	
	
}


