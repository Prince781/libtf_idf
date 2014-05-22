package com.libtfidf.doc;

public abstract class Document {
	/**
	 * Gets the terms (terms are alphanumeric and separated by spaces) in the
	 * document, without regard to punctuation
	 * @return
	 */
	public abstract String[] getWords();
	
	/**
	 * Converts this document to a String.
	 */
	@Override
	public abstract String toString();
}