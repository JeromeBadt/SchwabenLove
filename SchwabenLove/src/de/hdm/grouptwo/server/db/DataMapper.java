package de.hdm.grouptwo.server.db;

import java.util.ArrayList;

/**
 * DataMapper interface provides the basic set of methods that need to be
 * implemented by mapper classes.
 * <p>
 * 
 * @author JeromeBadt
 */
public interface DataMapper<T> {
	public void insert(T t);

	public void update(T t);

	public void delete(T t);

	public ArrayList<T> findAll();

	public T findById(int id);
}
