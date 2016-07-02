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

	/**
	 * Return the id.
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Convert the instance to a String.
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " #" + id;
	}

	/**
	 * Check if object is unequal null.
	 */
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
