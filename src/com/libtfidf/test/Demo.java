/**********************************************************
 * libtf_idf
 * Library for performing term frequency - inverse document
 * frequency on large data sets.
 * @author Princeton Ferro
 * May 2014 
***********************************************************/

package com.libtfidf.test;

import java.io.*;
import java.net.URISyntaxException;

import com.libtfidf.doc.*;
import com.libtfidf.tfidf.Analysis;

/**
 * Tests some methods of this library.
 */
public class Demo {

	public static void main(String[] args) throws IOException,
			URISyntaxException {
		PlainTextDocument[] docs = new PlainTextDocument[] {
			new PlainTextDocument(new File("data/apache.txt")),
			new PlainTextDocument(new File("data/gplv2.txt")),
			new PlainTextDocument(new File("data/gplv3.txt")),
			new PlainTextDocument(new File("data/mit.txt")),
			new PlainTextDocument(new File("data/mozilla.txt"))
		};
		
		String w = "mozilla"; // our query
		System.out.println("Query: "+w);
		
		Analysis an = new Analysis(docs);
		for (int i = 0; i<docs.length; i++)
			System.out.printf("tfidf(%s) = %f\n", docs[i].getFile().getName(),
					an.tfIDF(w, i));
	}

}
