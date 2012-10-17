/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package edu.cmu.lti.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

/**
 * A simple collection reader that reads a file in the
 * filesystem. It can be configured with the following parameters:
 * <ul>
 * <li><code>InputFile</code> - path to the input file (set to hw1.in)</li>
 * <li><code>Language</code> (optional) - language of the input documents</li>
 * </ul>
 * 
 */
public class InputFileCollectionReader extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of the input
	 * file.
	 */
	public static final String PARAM_INPUTFILE = "InputFile";

	/**
	 * Name of optional configuration parameter that contains the language of
	 * the documents in the input directory. If specified this information will
	 * be added to the CAS.
	 */
	public static final String PARAM_LANGUAGE = "Language";

	private ArrayList<String> lines = new ArrayList<String>();

	private String mLanguage;

	private int mCurrentIndex;

	/**
	 * Read the specific input file and store the lines into our memory.
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File inputFile = new File(
				((String) getConfigParameterValue(PARAM_INPUTFILE)).trim());
		mLanguage = (String) getConfigParameterValue(PARAM_LANGUAGE);
		mCurrentIndex = 0;

		// if input directory does not exist or is not a directory, throw
		// exception
		if (!inputFile.exists() || !inputFile.isFile()) {
			throw new ResourceInitializationException(
					ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTFILE,
							this.getMetaData().getName(),
							inputFile.getAbsolutePath() });
		}
		
		String thisLine;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.trim();
				thisLine = thisLine.replaceAll("\\r?\\n", "");
				lines.add(thisLine);											
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#hasNext()
	 */
	public boolean hasNext() {
		return mCurrentIndex < lines.size();
	}

	/**
	 * Get the next line as a CAS object in the input file.
	 * 
	 * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
	 */
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}

		// get the current line.
		String text = (String) lines.get(mCurrentIndex++);

		// put document in CAS
		jcas.setDocumentText(text);

		// set language if it was explicitly specified as a configuration
		// parameter
		if (mLanguage != null) {
			((DocumentAnnotation) jcas.getDocumentAnnotationFs())
					.setLanguage(mLanguage);
		}

		// Also store location of source document in CAS. This information is
		// critical
		// if CAS Consumers will need to know where the original document
		// contents are located.
		// For example, the Semantic Search CAS Indexer writes this information
		// into the
		// search index that it creates, which allows applications that use the
		// search index to
		// locate the documents that satisfy their semantic queries.
		SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(
				jcas);
		//srcDocInfo.setUri(inputFile.getAbsoluteFile().toURL().toString());
		srcDocInfo.setOffsetInSource(0);
		srcDocInfo.setDocumentSize(text.length());
		srcDocInfo.setLastSegment(mCurrentIndex == lines.size());
		srcDocInfo.addToIndexes();
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#close()
	 */
	public void close() throws IOException {
	}

	/**
	 * shows how many lines have been generated compared to the total number of lines in the input file.
	 * 
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#getProgress()
	 */
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(mCurrentIndex, lines.size(),
				Progress.ENTITIES) };
	}


}
