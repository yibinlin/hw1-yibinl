

/* First created by JCasGen Wed Oct 17 13:46:02 EDT 2012 */
package edu.cmu.lti.se.ner;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 17 20:01:34 EDT 2012
 * XML source: /usr0/home/yibinl/workspace/hw1-yibinl/src/main/resources/NERTypeSystem.xml
 * @generated */
public class POSAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(POSAnnotation.class);
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
  protected POSAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public POSAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public POSAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public POSAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: name

  /** getter for name - gets Building containing this room
   * @generated */
  public String getName() {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "edu.cmu.lti.se.ner.POSAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets Building containing this room 
   * @generated */
  public void setName(String v) {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "edu.cmu.lti.se.ner.POSAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: outputId

  /** getter for outputId - gets 
   * @generated */
  public String getOutputId() {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_outputId == null)
      jcasType.jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.POSAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_outputId);}
    
  /** setter for outputId - sets  
   * @generated */
  public void setOutputId(String v) {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_outputId == null)
      jcasType.jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.POSAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_outputId, v);}    
   
    
  //*--------------*
  //* Feature: orthVote

  /** getter for orthVote - gets 
   * @generated */
  public double getOrthVote() {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_orthVote == null)
      jcasType.jcas.throwFeatMissing("orthVote", "edu.cmu.lti.se.ner.POSAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_orthVote);}
    
  /** setter for orthVote - sets  
   * @generated */
  public void setOrthVote(double v) {
    if (POSAnnotation_Type.featOkTst && ((POSAnnotation_Type)jcasType).casFeat_orthVote == null)
      jcasType.jcas.throwFeatMissing("orthVote", "edu.cmu.lti.se.ner.POSAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((POSAnnotation_Type)jcasType).casFeatCode_orthVote, v);}    
  }

    