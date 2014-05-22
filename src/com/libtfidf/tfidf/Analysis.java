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
	private TreeMap<Integer,String> histTree[]; // sort by frequency
	private HashMap<String,Integer> histMap[]; // lookup frequency by word
	
	/**
	 * Creates a new Analysis from a collection of documents (corpus).
	 * @param docs
	 */
	public Analysis(Document... corpus) {
		docs = corpus;
		histTree = new TreeMap[corpus.length];
		histMap = new HashMap[corpus.length];
	}
	
	private int numMatches(String word, String[] words) {
		int n = 0;
		
		for (String w : words)
			if (w.equals(word)) n++;
		
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
		String words[] = doc.getWords();
		
		if (histTree[document] == null)
			histTree[document] = new TreeMap<>();
		if (histMap[document] == null)
			histMap[document] = new HashMap<>();
		
		double f = 0, f_max = 0;
		for (String word : words)
			if (!histTree[document].containsValue(word)) {
				int n;
				histTree[document].put(n = numMatches(word,words), word);
			
				if (!histMap[document].containsKey(word))
					histMap[document].put(word, n);
			}
		
		f_max = histTree[document].lastKey(); // get most-prevalent word
		f = histMap[document].get(phrase); // get frequency of phrase
		
		return 0.5 + (0.5 * f) / f_max;
	}
	

	/**
	 * Gets the inverse document frequency for a phrase among all documents in
	 * the corpus.
	 * @param phrase The phrase to look for
	 * @return
	 */
	public double inverseDocumentFrequency(String phrase) {
		double D = 1;
		for (Document d : docs)
			if ((""+d).contains(phrase)) D++;
		
		return Math.log((double) docs.length / D);
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