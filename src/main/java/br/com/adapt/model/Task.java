package br.com.adapt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.adapt.domain.Difficulty;


<<<<<<< HEAD
=======

>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107
@Entity
@Table(name = "tasks")
public class Task extends Resource {

<<<<<<< HEAD
	
=======
>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;
	
<<<<<<< HEAD
=======

>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107
	@Column(name = "dificulty")
	private Difficulty dificulty;
	
	@ManyToMany
    @JoinTable(
        name = "tasks_tags",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	private List<Tag> tags;
	
<<<<<<< HEAD
	
=======
>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
<<<<<<< HEAD
	
=======
>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107

	public Difficulty getDificulty() {
		return dificulty;
	}

	public void setDificulty(Difficulty dificulty) {
		this.dificulty = dificulty;
	}
<<<<<<< HEAD
=======


>>>>>>> cc6b259381d782aede01e1ad38dd634ee2aaf107
	
}
