package br.com.adapt.util;

import java.util.ArrayList;

import br.com.adapt.model.Task;


public class CheckinForms {
	private ArrayList<Task> checkins;

    public CheckinForms() {
    }

    public CheckinForms(ArrayList<Task> checkins) {
      this.checkins = checkins;
    }

	/**
	 * @return the checkins
	 */
	public ArrayList<Task> getCheckins() {
		return checkins;
	}

	/**
	 * @param checkins the checkins to set
	 */
	public void setCheckins(ArrayList<Task> checkins) {
		this.checkins = checkins;
	}
    
}
