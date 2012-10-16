package edu.cmu.lti.se.ner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tutorial.RoomNumber;

public class GeneMentionAnnotator extends JCasAnnotator_ImplBase {
	  private Pattern mYorktownPattern = Pattern.compile("\\b[0-4]\\d-[0-2]\\d\\d\\b");

	  private Pattern mHawthornePattern = Pattern.compile("\\b[G1-4][NS]-[A-Z]\\d\\d\\b");

	  /**
	   * @see JCasAnnotator_ImplBase#process(JCas)
	   */
	  public void process(JCas aJCas) {
	    // get document text
	    String docText = aJCas.getDocumentText();
	    // search for Yorktown room numbers
	    Matcher matcher = mYorktownPattern.matcher(docText);
	    while (matcher.find()) {
	      // found one - create annotation
	      RoomNumber annotation = new RoomNumber(aJCas);
	      annotation.setBegin(matcher.start());
	      annotation.setEnd(matcher.end());
	      annotation.setBuilding("Yorktown");
	      annotation.addToIndexes();
	    }
	    // search for Hawthorne room numbers
	    matcher = mHawthornePattern.matcher(docText);
	    while (matcher.find()) {
	      // found one - create annotation
	      RoomNumber annotation = new RoomNumber(aJCas);
	      annotation.setBegin(matcher.start());
	      annotation.setEnd(matcher.end());
	      annotation.setBuilding("Hawthorne");
	      annotation.addToIndexes();
	    }
	  }

	}

