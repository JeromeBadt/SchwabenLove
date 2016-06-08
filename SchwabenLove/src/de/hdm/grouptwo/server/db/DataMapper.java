package de.hdm.grouptwo.server.db;

import java.util.ArrayList;

/**
 * DataMapper interface provides the basic set of methods that need to be
 * implemented by mapper classes.
 * 
 * @author JeromeBadt
 */
public interface DataMapper<T> {
	public T insert(T t);

	public void update(T t);

	public void delete(T t);

	public ArrayList<T> findAll();

	public T findById(int id);
}
