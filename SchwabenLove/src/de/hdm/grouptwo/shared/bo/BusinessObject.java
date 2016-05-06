package de.hdm.grouptwo.shared.bo;

import java.io.Serializable;

/**
 * <code>BusinessObject</code> is the superclass of all other classes in the
 * business object package. It provides fundamential functionality necessary for
 * all classes.
 * 
 * @author Thies
 */

public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " #" + id;
	}

	@Override
	public boolean equals(Object o) {

		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId() == id)
					return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
