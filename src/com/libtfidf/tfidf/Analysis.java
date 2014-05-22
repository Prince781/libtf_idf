package com.libtfidf.tfidf;

import com.libtfidf.doc.Document;

import java.util.*;
import java.util.regex.*;

public class Analysis {
	private Document[] docs;
	
	public Analysis(Document... docs) {
		this.docs = docs;
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
	 * @param phrase
	 * @return A TF coefficient
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
	 * 
	 * @param phrase The phrase to look for.
	 * @param docs
	 * @return
	 */
	public double inverseDocumentFrequency(String phrase) {
		int D = 0;
		for (Document d : docs)
			if ((""+docs).contains(phrase)) D++;
		
		return Math.log(docs.length / (1 + D));
	}
	
	/**
	 * 
	 * @param phrase
	 * @param d The index of the document in the corpus.
	 * @return
	 */
	public double tfIDF(String phrase, int d) {
		return termFrequency(phrase, d) * inverseDocumentFrequency(phrase);
	}
	
	public Document[] getDocuments() {
		return docs;
	}
	
	public Document getDocument(int i) {
		return docs[i];
	}
}