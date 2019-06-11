package br.ufrn.imd.lp2.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class ConsineSimilarity {
	
    /**
    * @param terms values to analyze
    * @return a map containing unique 
    * terms and their frequency
    */
   public static Map<CharSequence, Integer> getTermFrequencyMap(String[] terms) {
       Map<CharSequence, Integer> termFrequencyMap = new HashMap<>();
       for (String term : terms) {
           Integer n = termFrequencyMap.get(term);
           n = (n == null) ? 1 : ++n;
           termFrequencyMap.put(term, n);
       }
       return termFrequencyMap;
   }

	/**
	 * Calculates the cosine similarity for two given vectors.
	 *
	 * @param leftVector  left vector
	 * @param rightVector right vector
	 * @return cosine similarity between the two vectors
	 */
	public Double cosineSimilarity(final String text1,
			final String text2) {
		
		//Get vectors
        Map<CharSequence, Integer> leftVector = getTermFrequencyMap(text1.split("\\W+"));
        Map<CharSequence, Integer> rightVector = getTermFrequencyMap(text2.split("\\W+"));

		if (leftVector == null || rightVector == null) {
			throw new IllegalArgumentException("Vectors must not be null");
		}

		final Set<CharSequence> intersection = getIntersection(leftVector, rightVector);

		final double dotProduct = dot(leftVector, rightVector, intersection);
		double d1 = 0.0d;
		for (final Integer value : leftVector.values()) {
			d1 += Math.pow(value, 2);
		}
		double d2 = 0.0d;
		for (final Integer value : rightVector.values()) {
			d2 += Math.pow(value, 2);
		}
		double cosineSimilarity;
		if (d1 <= 0.0 || d2 <= 0.0) {
			cosineSimilarity = 0.0;
		} else {
			cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
		}
		return cosineSimilarity;
	}

	/**
	 * Returns a set with strings common to the two given maps.
	 *
	 * @param leftVector  left vector map
	 * @param rightVector right vector map
	 * @return common strings
	 */
	private Set<CharSequence> getIntersection(final Map<CharSequence, Integer> leftVector,
			final Map<CharSequence, Integer> rightVector) {
		final Set<CharSequence> intersection = new HashSet<>(leftVector.keySet());
		intersection.retainAll(rightVector.keySet());
		return intersection;
	}

	/**
	 * Computes the dot product of two vectors. It ignores remaining elements. It
	 * means that if a vector is longer than other, then a smaller part of it will
	 * be used to compute the dot product.
	 *
	 * @param leftVector   left vector
	 * @param rightVector  right vector
	 * @param intersection common elements
	 * @return the dot product
	 */
	private double dot(final Map<CharSequence, Integer> leftVector, final Map<CharSequence, Integer> rightVector,
			final Set<CharSequence> intersection) {
		long dotProduct = 0;
		for (final CharSequence key : intersection) {
			dotProduct += leftVector.get(key) * rightVector.get(key);
		}
		return dotProduct;
	}
}