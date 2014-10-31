package com.libtfidf.analysis;

import java.util.*;

import com.libtfidf.doc.*;

/**
 * Useful in analyzing documents.
 * @author Princeton Ferro
 */
public class Analysis {
	private Document[] corpus;
	public ArrayList<String> ignore;
	
	/**
	 * Creates a new corpus from documents.
	 * @param corpus A collection of documents.
	 */
	public Analysis(Document... corpus) {
		this.corpus = corpus;
		ignore = new ArrayList<>();
		
		// populate
		// ignore.addAll(Arrays.asList("the","a","of"));
	}
	
	/**
	 * Performs term frequency (with correction) on a document within our corpus.
	 * @param d Document index
	 * @return A tf value (adjusted)
	 */
	public double tf(String phrase, int d) {
		HashMap<String,Integer> terms = corpus[d].getTerms();
		
		for (String word : ignore)
			terms.remove(word);
		
		double f = terms.containsKey(phrase) ? terms.get(phrase) : 0,
				f_max = (double) Collections.max(terms.values());
		return 0.5*f / f_max;
	}
	
	/**
	 * Performs inverse document frequency on the corpus.
	 * @param phrase The phrase to look for.
	 * @return An idf value
	 */
	public double idf(String phrase) {
		double D = 0;
		for (Document doc : corpus)
			if (doc.getTerms().containsKey(phrase)) D++;
		
		if (D == 0)
			return 0;
		return Math.log(corpus.length / D);
	}
	
	/**
	 * Performs tfidf on a document.
	 * @param phrase The phrase to search for.
	 * @param d The document to look at
	 * @return A tfidf value
	 */
	public double tfidf(String phrase, int d) {
		double tf = tf(phrase,d);
		
		if (tf != 0)
			return tf * idf(phrase);
		return 0;
	}
}
