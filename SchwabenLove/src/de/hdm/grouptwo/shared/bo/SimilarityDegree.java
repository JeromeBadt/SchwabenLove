package de.hdm.grouptwo.shared.bo;

/**
 * The SimilarityDegree shows the grade of similarity between two profiles
 * 
 * @author joshuahill
 *
 */
public class SimilarityDegree extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	private int score;
	private Profile profile;
	private Profile owner;

	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}
	
	

}
