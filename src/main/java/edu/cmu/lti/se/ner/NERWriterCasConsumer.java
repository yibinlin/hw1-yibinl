package edu.cmu.lti.se.ner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

import edu.cmu.lti.se.ner.NERAnnotation;

/**
 * A simple CAS consumer that generates XCAS (XML representation of the CAS)
 * files in the filesystem.
 * <p>
 * This CAS Consumer takes one parameters:
 * <ul>
 * <li><code>OutputDirectory</code> - path to directory into which output files
 * will be written</li>
 * </ul>
 * 
 * 
 */
public class NERWriterCasConsumer extends CasConsumer_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a
	 * directory into which the output files will be written.
	 */
	public static final String PARAM_OUTPUTFILE = "OutputFile";

	private File mOutputFile;

	private int mDocNum;

	public void initialize() throws ResourceInitializationException {
		mDocNum = 0;
		mOutputFile = new File(
				(String) getConfigParameterValue(PARAM_OUTPUTFILE));
		/*
		 * if (!mOutputFile.exists()) { mOutputDir.mkdirs(); }
		 */
	}

	/**
	 * Processes the CasContainer which was populated by the
	 * TextAnalysisEngines. <br>
	 * In this case, the CAS is converted to XML and written into the output
	 * file .
	 * 
	 * @param aCAS
	 *            CasContainer which has been populated by the TAEs
	 * 
	 * @throws ResourceProcessException
	 *             if there is an error in processing the Resource
	 * 
	 * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
	 */
	public void processCas(CAS aCAS) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		// retreive the filename of the input file from the CAS
		File outFile = mOutputFile;

		// serialize XCAS and write to output file
		try {
			writeToFile(jcas, outFile);
		} catch (IOException e) {
			throw new ResourceProcessException(e);
		}
	}

	/**
	 * Serialize a CAS to a file in XCAS format
	 * 
	 * @param aCas
	 *            CAS to serialize
	 * @param name
	 *            output file
	 * 
	 * @throws IOException
	 *             if an I/O failure occurs
	 * @throws SAXException
	 *             if an error occurs generating the XML text
	 */
	private void writeToFile(JCas jcas, File name) throws IOException {
		FileWriter fw = new FileWriter(name);
		BufferedWriter out = new BufferedWriter(fw);
		FSIterator<Annotation> it = jcas.getAnnotationIndex().iterator();
		StringBuffer sb = new StringBuffer();

		while (it.hasNext()) {

			Annotation an = (it.next());
			if (an instanceof NERAnnotation) {
				NERAnnotation nan = (NERAnnotation)an;
				
				sb.append(nan.getOutputId());
				sb.append("|");
				sb.append(nan.getBegin());
				sb.append(" ");
				sb.append(nan.getEnd());
				sb.append("|");
				sb.append(nan.getGeneName());
				sb.append("\n");
			}
		}

		try {
			out.write(sb.toString());

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
