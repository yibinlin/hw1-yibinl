
/* First created by JCasGen Tue Oct 16 22:26:17 EDT 2012 */
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
 * Updated by JCasGen Wed Oct 17 20:01:34 EDT 2012
 * @generated */
public class NERAnnotation_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NERAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NERAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NERAnnotation(addr, NERAnnotation_Type.this);
  			   NERAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NERAnnotation(addr, NERAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NERAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.se.ner.NERAnnotation");
 
  /** @generated */
  final Feature casFeat_geneName;
  /** @generated */
  final int     casFeatCode_geneName;
  /** @generated */ 
  public String getGeneName(int addr) {
        if (featOkTst && casFeat_geneName == null)
      jcas.throwFeatMissing("geneName", "edu.cmu.lti.se.ner.NERAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geneName);
  }
  /** @generated */    
  public void setGeneName(int addr, String v) {
        if (featOkTst && casFeat_geneName == null)
      jcas.throwFeatMissing("geneName", "edu.cmu.lti.se.ner.NERAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_geneName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_outputId;
  /** @generated */
  final int     casFeatCode_outputId;
  /** @generated */ 
  public String getOutputId(int addr) {
        if (featOkTst && casFeat_outputId == null)
      jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.NERAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_outputId);
  }
  /** @generated */    
  public void setOutputId(int addr, String v) {
        if (featOkTst && casFeat_outputId == null)
      jcas.throwFeatMissing("outputId", "edu.cmu.lti.se.ner.NERAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_outputId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NERAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_geneName = jcas.getRequiredFeatureDE(casType, "geneName", "uima.cas.String", featOkTst);
    casFeatCode_geneName  = (null == casFeat_geneName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geneName).getCode();

 
    casFeat_outputId = jcas.getRequiredFeatureDE(casType, "outputId", "uima.cas.String", featOkTst);
    casFeatCode_outputId  = (null == casFeat_outputId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_outputId).getCode();

  }
}



    