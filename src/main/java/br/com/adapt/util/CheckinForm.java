package br.com.adapt.util;

import br.com.adapt.domain.Status;

public class CheckinForm {
	private long id;
    private Status status;

    public CheckinForm() {
    }

    public CheckinForm(long id, Status status) {
      this.id = id;
      this.status = status;
    }
}
