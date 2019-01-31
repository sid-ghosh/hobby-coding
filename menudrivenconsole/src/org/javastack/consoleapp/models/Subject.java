package org.javastack.consoleapp.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Subject implements Comparable<Subject>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long subjectId;
	private String subtitle;
	private int durationInHours;
	private Set<Book> books;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	public Set<Book> getBooks() {
		if (this.books == null) {
			this.books = new HashSet<Book>();
		}
		return books;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subtitle == null) ? 0 : subtitle.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Subject)) {
			return false;
		}
		Subject other = (Subject) obj;
		if (subtitle == null) {
			if (other.subtitle != null) {
				return false;
			}
		} else if (!subtitle.equals(other.subtitle)) {
			return false;
		}
		return true;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public int compareTo(Subject o) {
		// TODO Auto-generated method stub
		return (getSubtitle()).compareTo(o.getSubtitle());
	}

}
