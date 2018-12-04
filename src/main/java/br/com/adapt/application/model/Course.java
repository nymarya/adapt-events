package br.com.adapt.application.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import br.com.adapt.application.domain.Category;
import br.com.adapt.framework.model.Resource;


@Entity
@Table(name = "courses")
public class Course extends Resource {

	
	private static final long serialVersionUID = 1L;

	@Column(name = "teacher")
	private String teacher;
	
	@Column(name = "category")
	private Category category;
	
	
}
