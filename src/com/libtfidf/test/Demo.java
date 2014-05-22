/**********************************************************
 * libtf_idf
 * Library for performing term frequency - inverse document
 * frequency on large data sets.
 * @author Princeton Ferro
 * May 2014 
***********************************************************/

package com.libtfidf.test;

import java.io.*;

import com.libtfidf.doc.*;
import com.libtfidf.tfidf.Analysis;

public class Demo {

	public static void main(String[] args) throws IOException {
		PlainTextDocument[] docs = new PlainTextDocument[2];
		docs[0] = new PlainTextDocument("test1.txt");
		docs[1] = new PlainTextDocument("test2.txt");
		
		String w = "idea";
		
		Analysis an = new Analysis(docs);
		double tfidf1 = an.tfIDF(w, 0);
		double tfidf2 = an.tfIDF(w, 1);
		
		System.out.printf("Search for \"%s\": doc1 - %f, doc2 - %f\n",
				w, tfidf1, tfidf2);
	}

}
