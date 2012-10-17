package edu.cmu.lti.se.ner.util;

/**
 * Common string utility methods shared across multiple analysis engines.
 * @author yibinl
 *
 */
public class StringUtils {

	/**
	 * This helper method helps to calculate the number of non white space before the start of the gene mention
	 * 
	 * @param text the raw input line from the input file (input id removed)
	 * @param index the index before which we should count the number of non white spaces.
	 * @return the number of non white spaces.
	 */
	public static int countNonWhitespace(String text, int index)
	{
		String sub = text.substring(0, index);
		int counter = 0;
		for(int i = 0; i < sub.length(); i++)
		{
			if(sub.charAt(i) != ' ')
			{
				counter++;
			}
		}
		
		return counter;
	}
}
