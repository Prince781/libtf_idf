package com.libtfidf.doc;

import java.io.*;

/**
 * A specific Document from a plain-text file.
 * @author Princeton Ferro
 */
public class PlainTextDocument extends Document {
	private File file;
	private String text = "";
	
	public PlainTextDocument(String fname) throws IOException {
		file = new File(fname);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		for (int c; (c = br.read()) != -1;)
			text += (char) c;
		
		br.close();
	}
	@Override
	public String[] getWords() {
		return text.split("\\W+");
	}

	@Override
	public String toString() {
		return text;
	}

}
