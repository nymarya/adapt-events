package br.com.adapt.framework.util;

import br.com.adapt.framework.domain.Status;

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
