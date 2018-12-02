package br.com.adapt.framework.util;

import java.util.ArrayList;

import br.com.adapt.framework.model.Resource;


public class CheckinForms {
	private ArrayList<Resource> checkins;

    public CheckinForms() {
    }

    public CheckinForms(ArrayList<Resource> checkins) {
      this.checkins = checkins;
    }

	/**
	 * @return the checkins
	 */
	public ArrayList<Resource> getCheckins() {
		return checkins;
	}

	/**
	 * @param checkins the checkins to set
	 */
	public void setCheckins(ArrayList<Resource> checkins) {
		this.checkins = checkins;
	}
    
}
