package com.libtfidf.tfidf;

import com.libtfidf.doc.Document;

import java.util.*;
import java.util.regex.*;

/**
 * Provides analysis functions for a given corpus.
 * @author Princeton Ferro
 */
public class Analysis {
	private Document[] docs;
	
	/**
	 * Creates a new Analysis from a collection of documents (corpus).
	 * @param docs
	 */
	public Analysis(Document... corpus) {
		docs = corpus;
	}
	
	private int numMatches(String word, String corpus) {
		int n = 0;
		Pattern p = Pattern.compile(word);
		Matcher m = p.matcher(corpus);
		
		while (m.find()) n++;
		
		return n;
	}
	
	/**
	 * Gets the weighted term frequency of the given phrase.
	 * @param phrase The phrase to look for
	 * @param document The index of the document in the corpus
	 * @return A <code>tf</code> coefficient
	 */
	public double termFrequency(String phrase, int document) {
		Document doc = docs[document];
		String words[] = doc.getWords(),
				text = "" + doc;
		TreeMap<Integer,String> hist = new TreeMap<>();
		
		double f = 0, f_max = 0;
		for (String word : words) {
			if (hist.containsValue(word)) continue;
			int n = numMatches(word, text);
			hist.put(n, word);
			if (word.equals(phrase)) f = n;
			if (n > f_max) f_max = n;
		}
		
		return 0.5 + (0.5 * f) / f_max;
	}
	

	/**
	 * Gets the inverse document frequency for a phrase among all documents in
	 * the corpus.
	 * @param phrase The phrase to look for
	 * @return
	 */
	public double inverseDocumentFrequency(String phrase) {
		double D = 0;
		for (Document d : docs)
			if ((""+d).contains(phrase)) D++;
		
		return Math.log((double) docs.length / (1.0 + D));
	}
	
	/**
	 * Analyzes the tf-idf coefficient for a given phrase in a given document,
	 * among all of the documents in the corpus.
	 * @param phrase The phrase to search for
	 * @param d The index of the document in the corpus.
	 * @return A <code>tf-idf</code> value
	 */
	public double tfIDF(String phrase, int d) {
		return termFrequency(phrase, d) * inverseDocumentFrequency(phrase);
	}
	
	/**
	 * Gets all documents in the corpus.
	 * @return
	 */
	public Document[] getCorpus() {
		return docs;
	}
	
	/**
	 * Gets a specific document in the corpus.
	 * @param i The index of the document.
	 * @return
	 */
	public Document getDocument(int i) {
		return docs[i];
	}
}