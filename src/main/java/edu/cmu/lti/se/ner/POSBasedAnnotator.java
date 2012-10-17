package edu.cmu.lti.se.ner;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

public class POSBasedAnnotator extends JCasAnnotator_ImplBase {

	PosTagNamedEntityRecognizer sNER;	
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    
	    try {
	    	sNER = new PosTagNamedEntityRecognizer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) {
		// get document text
		String docText = aJCas.getDocumentText();
		String[] parts = docText.split(" ", 2);
		int len0 = parts[0].length();
		
		Map<Integer, Integer> begin2End = sNER.getGeneSpans(parts[1]);
		
		Iterator it = begin2End.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>)it.next();
			POSAnnotation annotation = new POSAnnotation(aJCas);
			annotation.setBegin(pairs.getKey() + len0);
			annotation.setEnd(pairs.getValue() + len0);
			annotation.setOutputId(parts[0]);
			annotation.setName(parts[1].substring(pairs.getKey(), pairs.getValue()));
			annotation.addToIndexes();
			
	        //System.out.println(pairs.getKey() + " , " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
		}
		
	}

}
