/**
 * 
 */
package br.com.adapt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author jainebudke
 *
 */
@Entity
@Table(name = "tags")
public class Tag implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	@ManyToMany
    @JoinTable(
        name = "tasks_tags",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "task_id")
    )
	private List<Tag> tasks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheduler_id")
	private Scheduler scheduler;

	
	/**
	 * Get the id
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	
	/**
	 * Update the id 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
	/**
	 * Get the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Update the name 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Get the color
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	
	/**
	 * Update the color 
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	
	/**
	 * Get the tasks
	 * @return the tasks
	 */
	public List<Tag> getTasks() {
		return tasks;
	}

	
	/**
	 * Update the tasks 
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Tag> tasks) {
		this.tasks = tasks;
	}


	/**
	 * @return the scheduler
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}


	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	
}
