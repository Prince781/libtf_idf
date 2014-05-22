package com.libtfidf.doc;

import java.io.*;

public class PlainTextDocument extends Document {
	private File file;
	private String text = "";
	
	public PlainTextDocument(String fname) throws IOException {
		file = new File(fname);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		char c;
		while ((c = (char) br.read()) != -1)
			text += c;
		
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
