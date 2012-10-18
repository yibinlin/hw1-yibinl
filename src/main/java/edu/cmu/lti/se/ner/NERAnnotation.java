

/* First created by JCasGen Tue Oct 16 22:26:17 EDT 2012 */
package edu.cmu.lti.se.ner;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 17 20:01:34 EDT 2012
 * XML source: /usr0/home/yibinl/workspace/hw1-yibinl/src/main/resources/NERTypeSystem.xml
 * @generated */
public class NERAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NERAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NERAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NERAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NERAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NERAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: geneName

  /** getter for geneName - gets Building containing this room
   * @generated */
  public String getGeneName() {
    if (NERAnnotation_Type.featOkTst && ((NERAnnotation_Type)jcasType).casFeat_geneName == null)
      jcasType.jcas.throwFeatMissing("geneName", "edu.cmu.lti.se.ner.NERAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NERAnnotation_Type)jcasType).casFeatCode_geneName);}
    
  /** setter for geneName - sets Building containing this room 
   * @generated */
  public void setGeneName(String v) {
    if (NERAnnotation_Type.featOkTst && ((NERAnnotation_Type)jcasType).casFeat_geneName == null)
      jcasType.jcas.throwFeatMissing("geneName", "edu.cmu.lti.se.ner.NERAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NERAnnotation_Type)jcasType).casFeatCode_geneName, v);}    
   
    
  //*--------------*
  //* Feature: outputId

  /** getter for outputId - gets 
   * @generated */
  public String getOutputId() {
    if (NERAnnotation_Type.featOkTst && ((NERAnnotation_Type)jcasType).casFeat_outputId == null)
      jcasType.jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.NERAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NERAnnotation_Type)jcasType).casFeatCode_outputId);}
    
  /** setter for outputId - sets  
   * @generated */
  public void setOutputId(String v) {
    if (NERAnnotation_Type.featOkTst && ((NERAnnotation_Type)jcasType).casFeat_outputId == null)
      jcasType.jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.NERAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NERAnnotation_Type)jcasType).casFeatCode_outputId, v);}    
  }

    