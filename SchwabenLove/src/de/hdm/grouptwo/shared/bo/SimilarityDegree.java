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

	/**
	 * Get the score of the similarity degree.
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the score of the similarity degree.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Get the id of the referenced profile.
	 * @return
	 */
	public int getReferenceProfileId() {
		return referenceProfileId;
	}

	/**
	 * Set an id of the referenced profile.
	 * @param referenceProfileId
	 */
	public void setReferenceProfileId(int referenceProfileId) {
		this.referenceProfileId = referenceProfileId;
	}

	/**
	 * Get the comparison profile id. 
	 * @return
	 */
	public int getComparisonProfileId() {
		return comparisonProfileId;
	}

	/**
	 * Set a comparison profile id.
	 * @param comparisonProfileId
	 */
	public void setComparisonProfileId(int comparisonProfileId) {
		this.comparisonProfileId = comparisonProfileId;
	}

}
