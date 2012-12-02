package edu.cmu.lti.se.ner;

import java.io.File;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.lti.se.ner.util.StringUtils;

/**
 * This annotator uses LingPipe Hidden Markov Model method to tag gene mentions.
 * 
 * @author Yibin Lin
 * 
 */
public class GeneMentionAnnotator extends JCasAnnotator_ImplBase {

  /**
   * instance of Chunker object from LingPipe.
   */
  Chunker chunker;

  /**
   * Code to initialize LingPipe and the ne-en-bio-genetag.HmmChunker model.
   * 
   * @see JCasAnnotator_ImplBase#initialize(JCas)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);

    try {
      chunker = (Chunker) AbstractExternalizable.readObject(new File(
              "model/ne-en-bio-genetag.HmmChunker"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The process uses LingPipe to get the gene mentions, then creates NERAnnotation for each of the
   * gene mentions. The NER Annotation's are added into the corresponding JCas. There is also a
   * little big of filtering: the single lower case letter that are matched in LingPipe was deleted.
   * 
   * @param aJCas
   *          the JCas object corresponding to one line in hw1.in
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    // get document text
    String docText = aJCas.getDocumentText();
    String[] parts = docText.split(" ", 2);
    // int len0 = parts[0].length();

    Chunking chunking = chunker.chunk(parts[1]);
    Set<Chunk> chunkRes = chunking.chunkSet();
    for (Chunk c : chunkRes) {
      // filtering
      String possibleGene = parts[1].substring(c.start(), c.end());
      if (possibleGene.length() == 1 && !possibleGene.equals(possibleGene.toUpperCase()))
        continue;
      // System.out.println(parts[1].substring(c.start(), c.end()));
      NERAnnotation annotation = new NERAnnotation(aJCas);
      annotation.setBegin(StringUtils.countNonWhitespace(parts[1], c.start()));
      annotation.setEnd(StringUtils.countNonWhitespace(parts[1], (c.end() - 1)));
      annotation.setOutputId(parts[0]);
      annotation.setGeneName(possibleGene);
      annotation.addToIndexes();
    }

  }

}
