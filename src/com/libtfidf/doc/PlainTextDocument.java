package com.libtfidf.doc;

import java.util.*;
import java.io.*;

/**
 * A plain text document.
 * @author Princeton Ferro
 */
public class PlainTextDocument extends Document {
	private HashMap<String,Integer> map;
	private String text;
	
	public PlainTextDocument(String path) throws IOException {
		this(new File(path));
	}
	
	public PlainTextDocument(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[(int)f.length()];
		fis.read(data);
		fis.close();
		
		text = new String(data, "UTF-8"); // TODO: tailor charset for file
		String[] wrds = text.split("\\W+");
		map = new HashMap<>();
		
		for (String word : wrds)
			if (!word.isEmpty())
				map.put(word, map.containsKey(word) ? map.get(word)+1 : 1);
	}
	
	@Override
	public HashMap<String, Integer> getTerms() { return map; }

	@Override
	public String getText() { return text; }
}
