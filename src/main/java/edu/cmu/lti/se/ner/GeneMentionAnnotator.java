package edu.cmu.lti.se.ner;

import java.util.Iterator;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import edu.cmu.lti.se.NERAnnotation;

public class GeneMentionAnnotator extends JCasAnnotator_ImplBase {

	PosTagNamedEntityRecognizer sNER = new PosTagNamedEntityRecognizer();

	/**
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) {
		// get document text
		String docText = aJCas.getDocumentText();
		String[] parts = docText.split(" ", 2);
		
		Map<Integer, Integer> begin2End = sNER.getGeneSpans(docText);
		Iterator it = begin2End.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>)it.next();
			NERAnnotation annotation = new NERAnnotation(aJCas);
			annotation.setBegin(pairs.getKey());
			annotation.setEnd(pairs.getValue());
			annotation.setOutputId(parts[0]);
			annotation.setGeneName(docText.substring(pairs.getKey(), pairs.getValue()));
			annotation.addToIndexes();
			
	        //System.out.println(pairs.getKey() + " , " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
		}
		
	}

}
