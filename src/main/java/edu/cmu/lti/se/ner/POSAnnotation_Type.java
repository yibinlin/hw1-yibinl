
/* First created by JCasGen Wed Oct 17 13:46:03 EDT 2012 */
package edu.cmu.lti.se.ner;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Oct 17 17:07:28 EDT 2012
 * @generated */
public class POSAnnotation_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (POSAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = POSAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new POSAnnotation(addr, POSAnnotation_Type.this);
  			   POSAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new POSAnnotation(addr, POSAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = POSAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.se.ner.POSAnnotation");
 
  /** @generated */
  final Feature casFeat_name;
  /** @generated */
  final int     casFeatCode_name;
  /** @generated */ 
  public String getName(int addr) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "edu.cmu.lti.se.ner.POSAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_name);
  }
  /** @generated */    
  public void setName(int addr, String v) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "edu.cmu.lti.se.ner.POSAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_name, v);}
    
  
 
  /** @generated */
  final Feature casFeat_outputId;
  /** @generated */
  final int     casFeatCode_outputId;
  /** @generated */ 
  public String getOutputId(int addr) {
        if (featOkTst && casFeat_outputId == null)
      jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.POSAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_outputId);
  }
  /** @generated */    
  public void setOutputId(int addr, String v) {
        if (featOkTst && casFeat_outputId == null)
      jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.POSAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_outputId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_orthVote;
  /** @generated */
  final int     casFeatCode_orthVote;
  /** @generated */ 
  public int getOrthVote(int addr) {
        if (featOkTst && casFeat_orthVote == null)
      jcas.throwFeatMissing("orthVote", "edu.cmu.lti.se.ner.POSAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_orthVote);
  }
  /** @generated */    
  public void setOrthVote(int addr, int v) {
        if (featOkTst && casFeat_orthVote == null)
      jcas.throwFeatMissing("orthVote", "edu.cmu.lti.se.ner.POSAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_orthVote, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public POSAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
    casFeatCode_name  = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_name).getCode();

 
    casFeat_outputId = jcas.getRequiredFeatureDE(casType, "outputId", "uima.cas.String", featOkTst);
    casFeatCode_outputId  = (null == casFeat_outputId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_outputId).getCode();

 
    casFeat_orthVote = jcas.getRequiredFeatureDE(casType, "orthVote", "uima.cas.Integer", featOkTst);
    casFeatCode_orthVote  = (null == casFeat_orthVote) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_orthVote).getCode();

  }
}



    