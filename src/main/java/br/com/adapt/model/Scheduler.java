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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * @author jainebudke
 *
 */
@Entity
@Table(name = "scheduler")
public class Scheduler implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@OneToMany(mappedBy="scheduler", cascade = CascadeType.ALL)
    List<Task> tasks;

	@OneToMany(mappedBy="scheduler", cascade = CascadeType.ALL)
    List<Freeblock> freeBlocks;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Freeblock> getFreeBlocks() {
		return freeBlocks;
	}

	public void setFreeBlocks(List<Freeblock> freeBlocks) {
		this.freeBlocks = freeBlocks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
		
	
}
