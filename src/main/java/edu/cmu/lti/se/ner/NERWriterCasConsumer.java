package edu.cmu.lti.se.ner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

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
	
	public static final String PARAM_GOLDSTANDARD = "GoldStandard";

	private File mOutputFile;
	
	private File mGoldStandard;

	private int mDocNum;
	BufferedWriter out;

	public void initialize() throws ResourceInitializationException {
		mDocNum = 0;
		mOutputFile = new File(
				(String) getConfigParameterValue(PARAM_OUTPUTFILE));
		mGoldStandard = new File(
				(String) getConfigParameterValue(PARAM_GOLDSTANDARD));
		/*
		 * if (!mOutputFile.exists()) { mOutputDir.mkdirs(); }
		 */
		FileWriter fw;
		try {
			fw = new FileWriter(mOutputFile);
			out = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			//System.out.println(sb.toString());
			out.write(sb.toString());
		} finally {
			;
		}
	}
	
	/**
	 * evaluate output file against gold standard provided in this hw.
	 * 
	 * @param goldStandard the sample.out file
	 * @param output the output file of the annotator
	 */
	private void evaluate(File goldStandard, File output)
	{
		if(goldStandard == null)
			return;
		
		int ptG = 0;
		int ptO = 0;
		ArrayList<String> linesG = new ArrayList<String>();
		ArrayList<String> linesO = new ArrayList<String>();
		ArrayList<String> correct = new ArrayList<String>();
		String thisLine;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(goldStandard));
			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.trim();
				thisLine = thisLine.replaceAll("\\r?\\n", "");
				String[] parts = thisLine.split("\\|");
				thisLine = parts[0] + " " + parts[1];
				linesG.add(thisLine);											
			}
			br.close();
			br = new BufferedReader(new FileReader(output));
			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.trim();
				thisLine = thisLine.replaceAll("\\r?\\n", "");
				String[] parts = thisLine.split("\\|");
				thisLine = parts[0] + " " + parts[1];
				//System.out.println(thisLine);
				linesO.add(thisLine);											
			}
			br.close();
			
			for(int i = 0; i < linesO.size(); i++)
			{
				ptO = i;
				for(int j = ptG; j < linesG.size(); j++)
				{
					if(linesG.get(j).equals(linesO.get(i)))
					{
						ptG = j + 1;
						correct.add(linesO.get(i));
						break;
					}
				}
			}
			
			double precision = ((double)(correct.size()))/linesO.size();
			double recall = ((double)(correct.size()))/linesG.size();
			double f1 = 2.0 * (precision * recall) / (precision + recall);
			StringBuffer res = new StringBuffer();
			res.append(String.format("Number of gene names in the output: %d.\n", linesO.size()));
			res.append(String.format("Number of gene names in the gold standard: %d.\n", linesG.size()));
			res.append(String.format("Number of gene names correctly labeled: %d.\n", correct.size()));
			res.append(String.format("Precision: %f.\n", precision));
			res.append(String.format("Recall: %f.\n", recall));
			res.append(String.format("F1 score: %f.\n", f1));
			System.out.println(res.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * @override prints the report of the current run. 
	 * The report reports some common statistics, including F1 measure.
	 * 
	 */
	public void destroy()
	{
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		evaluate(this.mGoldStandard, this.mOutputFile);
	}
	
	

}
