package edu.cmu.lti.se.ner;

import java.util.Iterator;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import edu.cmu.lti.se.ner.util.StringUtils;

/**
 * This annotator use orthography and POS tags to indicate whether it is a gene
 * mention.
 * 
 * It adds NERAnnotation if the weight is large enough.
 * 
 * The annotator was proved to perform worse, and therefore was not included in the final version of cpe descriptor.
 * 
 * @author Yibin Lin
 * 
 */
public class POSBasedAnnotator extends JCasAnnotator_ImplBase {
	/**
	 * named entity recognizer using StanfordCoreNLP.
	 */
	PosTagNamedEntityRecognizer sNER;

	double digit = 0.3;
	double digitPlusCap = 1.5;
	double digitPlusLet = 1.0;
	double InitCaps = 1.0;
	double hyphen = 1.0;
	double openSquare = 1.0;
	double backSlaskh = 1.0;
	double colon = 1.0;
	double semicolon = 1.0;

	/**
	 * threshold for the feature weight sum.
	 */
	final double LIMIT = 2.0;

	double[] weights = { 0.3, 1.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
	String[] regexes = { ".*[0-9]+.*",
			"(.*([0-9])+.*([A-Z])+.*|.*([A-Z])+.*([0-9])+.*)",
			"(.*([0-9])+.*([a-zA-Z])+.*|.*([a-zA-Z])+.*([0-9])+.*)", "[A-Z].*",
			".*-.*", ".*\\[.*", "/", ":", ";" };

	/**
	 * Initializes StanfordCoreNLP.
	 * 
	 * @see JCasAnnotator_ImplBase#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		
		try {
			sNER = new PosTagNamedEntityRecognizer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Detect NN inside the sentence, then test some features of orthography.
	 * 
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) {
		
		// get document text
		String docText = aJCas.getDocumentText();
		String[] parts = docText.split(" ", 2);
		int len0 = parts[0].length();

		Map<Integer, Integer> begin2End = sNER.getGeneSpans(parts[1]);

		Iterator it = begin2End.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>) it
					.next();

			String possibleName = parts[1].substring(pairs.getKey(),
					pairs.getValue());

			double weight = calculateWeight(possibleName);
			//ystem.out.println(possibleName + ": " + weight);

			POSAnnotation annotation = new POSAnnotation(aJCas);
			annotation.setBegin(pairs.getKey());
			annotation.setEnd(pairs.getValue());
			annotation.setOutputId(parts[0]);
			annotation.setName(possibleName);
			annotation.setOrthVote(calculateWeight(possibleName));
			annotation.addToIndexes();
			if (weight > LIMIT) {
				AnnotationIndex<Annotation> annotations = aJCas.getAnnotationIndex();
				NERAnnotation nannotation = new NERAnnotation(aJCas);
				nannotation.setBegin(StringUtils.countNonWhitespace(parts[1], pairs.getKey()));
				nannotation.setEnd(StringUtils.countNonWhitespace(parts[1], pairs.getValue()));
				nannotation.setOutputId(parts[0]);
				nannotation.setGeneName(possibleName);
				if(!annotations.contains(nannotation))
				{
					//System.out.println("Entered here!");
					nannotation.addToIndexes();
				}
			}

			// System.out.println(pairs.getKey() + " , " + pairs.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}

	}

	/**
	 * calculate the orthography weight
	 * 
	 * @param gene
	 *            the possible gene mention match.
	 * @return the total weight against the 9 features of orthography.
	 */
	private double calculateWeight(String gene) {
		double weight = 0.0;
		for (int i = 0; i < 9; i++) {
			if (gene.matches(regexes[i])) {
				weight += weights[i];
			}
		}
		return weight;
	}

}
