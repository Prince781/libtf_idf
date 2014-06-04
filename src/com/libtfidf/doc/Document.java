package com.libtfidf.doc;

import java.util.*;

/**
 * A document to read from.
 * @author Princeton Ferro
 */
public abstract class Document {
	/**
	 * Gets all terms, mapped to frequency in document.
	 * @return A histogram of terms to frequency.
	 */
	public abstract HashMap<String,Integer> getTerms();
	
	/**
	 * Gets all plain text of this document.
	 * @return Plain text of the document.
	 */
	public abstract String getText();
}
