package de.hdm.grouptwo.shared.bo;

/**
 * The <code>SimilarityDegree</code> shows the grade of similarity between two
 * profiles.
 * 
 * @author JoshuaHill, JeromeBadt
 */

public class SimilarityDegree extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int score;
	private int referenceProfileId;
	private int comparisonProfileId;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getReferenceProfileId() {
		return referenceProfileId;
	}

	public void setReferenceProfileId(int referenceProfileId) {
		this.referenceProfileId = referenceProfileId;
	}

	public int getComparisonProfileId() {
		return comparisonProfileId;
	}

	public void setComparisonProfileId(int comparisonProfileId) {
		this.comparisonProfileId = comparisonProfileId;
	}

}
