/**
 * 
 */
package br.com.adapt.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author mayra
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

	/**
	 * Serial version uid (default)
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	@NotEmpty(message = "*Nome é um campo obrigatório")
	private String name;

	@Column(name = "email")
	@Email(message = "*Email inválido ")
	private String email;

	@Column(name = "password")
	private String password;

	
	@OneToOne(mappedBy="user",cascade = CascadeType.ALL)
	private Scheduler scheduler;
	
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
	 * Get the email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	
	/**
	 * Update the email 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * Get the password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	
	/**
	 * Update the password 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
