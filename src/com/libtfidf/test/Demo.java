package com.libtfidf.test;

import com.libtfidf.doc.*;
import com.libtfidf.analysis.*;
import java.io.*;
import java.util.*;

/**
 * A main class demoing tfidf.
 * @author Princeton Ferro
 */
public class Demo {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("You must enter texts to analyze.");
			System.exit(1);
		}
		
		Document[] docs = new PlainTextDocument[args.length];
		for (int i=0; i<args.length; i++)
			docs[i] = new PlainTextDocument(args[i]);
		
		Analysis an = new Analysis(docs);
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a search term: ");
		String term = in.next("\\w+");
		for (int i=0; i<docs.length; i++)
			System.out.printf("tfidf(\"%s\") = %f\n", args[i], an.tfidf(term, i));
		in.close();
	}

}
