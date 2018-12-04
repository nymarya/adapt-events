package br.com.adapt.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.adapt.application.domain.Category;
import br.com.adapt.framework.model.Resource;

@Entity
@Table(name = "events")
public class Event extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "category")
	private Category category;

	@Column(name = "speaker")
	private String speaker;

	@Column(name = "order")
	private Integer order;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the speaker
	 */
	public String getSpeaker() {
		return speaker;
	}

	/**
	 * @param speaker the speaker to set
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
