/**
 * 
 */
package br.com.adapt.model;

import br.com.adapt.domain.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

/**
 * @author jainebudke
 *
 */

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "title")
	private String title;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "expected_time")
	private int expectedTime;
	
	@Column(name = "transactions", insertable=false, nullable=true)
	private String transactions;
	
	@Column(name = "dificulty")
	private Difficulty dificulty;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priority")
	private Priority priority;
	
	@Column(name = "status", insertable=false, nullable=true)
	private Status status;
	
	@Column(name = "type")
	private Type type;

	@ManyToOne
	@JoinColumn(name = "scheduler_id")
	private Scheduler scheduler;
	
	@ManyToMany
    @JoinTable(
        name = "tasks_tags",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	private List<Tag> tags;
	
	@Nullable
	@Column(name="day")
	private int day;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(int expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getTransactions() {
		return transactions;
	}

	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}

	public Difficulty getDificulty() {
		return dificulty;
	}

	public void setDificulty(Difficulty dificulty) {
		this.dificulty = dificulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	
}
