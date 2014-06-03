package com.libtfidf.doc;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.*;
import java.nio.file.*;

/**
 * A specific Document from a plain-text file.
 * @author Princeton Ferro
 */
public class PlainTextDocument extends Document {
	private File file;
	private String text = "";
	private String[] words;
	
	/**
	 * Creates a new plain text document from a file name.
	 * @param fname File location.
	 */
	public PlainTextDocument(String fname) throws IOException,
			URISyntaxException {
		this(new File(fname));
	}
	
	/**
	 * Creates a new plain text document from a file.
	 * @param docFile
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public PlainTextDocument(File docFile) throws IOException,
			URISyntaxException {
		file = docFile;
		String fname = "file://"+file.getAbsolutePath();
		byte[] data = Files.readAllBytes(Paths.get(new URI(fname)));
		text = new String(data, Charset.defaultCharset());
		words = text.split("\\W+");
	}
	
	public String[] getWords() { return words; }
	
	/**
	 * Gets the file relevant to this document.
	 */
	public File getFile() { return file; }
	
	public String toString() { return text; }
}
