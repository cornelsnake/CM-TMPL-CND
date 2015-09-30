package com.splwg.cm.domain.admin.serviceAgreementType;

import com.splwg.base.api.datatypes.Bool;
import com.splwg.base.api.lookup.CriteriaPriorityLookup;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.domain.common.characteristicType.CharacteristicType;
import com.splwg.base.domain.common.characteristicType.CharacteristicType_Id;
import com.splwg.base.domain.common.characteristicType.CharacteristicValue_Id;
import com.splwg.ccb.domain.admin.collectionEventType.entity.CollectionEventType_Id;
import com.splwg.ccb.domain.admin.collectionProcessTemplate.entity.CollectionProcessTemplate;
import com.splwg.ccb.domain.admin.collectionProcessTemplate.entity.CollectionProcessTemplate_Id;
import com.splwg.ccb.domain.admin.serviceAgreementType.algorithm.severanceCriteria.SaTypeSeveranceCriteriaAlgorithmSpot;
import com.splwg.ccb.domain.admin.serviceAgreementType.entity.ServiceAgreementType;
import com.splwg.ccb.domain.admin.severanceProcessTemplate.entity.SeveranceProcessTemplate;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.entity.ServiceAgreement;
import com.splwg.ccb.domain.customerinfo.servicePoint.entity.ServicePoint;
import com.splwg.cm.domain.admin.MgMd07020MessageRepository;
import com.splwg.cm.domain.common.SessionMethods;
import com.splwg.shared.logging.Logger;
import com.splwg.shared.logging.LoggerFactory;

/**
*
*  @AlgorithmComponent (softParameters = { @AlgorithmSoftParameter (name = typeChar, required = false, type = string)
*            , @AlgorithmSoftParameter (name = charVal, required=false, type = string)
*            , @AlgorithmSoftParameter (name = colEvtTyp, required=false, type = string)
*            , @AlgorithmSoftParameter (name = colProcTm, required=false, type = string)
*            , @AlgorithmSoftParameter (name = cond1, required=true, type = integer)
*            , @AlgorithmSoftParameter (name = cond2, required=true, type = integer)
*            , @AlgorithmSoftParameter (name = cond3, required=true, type = integer)
*         })
*
* @algorithm CM-TMPL-CND SVCR
* @algorithmDescription ENG "Условия для выбора Шаблона" "Планирование проверки ДЗ"
* @algorithmDescription RUS "Планирование проверки ДЗ" "Планирование проверки ДЗ"
* @algorithmDescription ROU "Планирование проверки ДЗ" "Планирование проверки ДЗ"
* 
* @parameter 1 N
* @parameterDescription 1 ENG "Тип характеристики для усл.1"
* @parameterDescription 1 RUS "Тип характеристики для усл.1"
* @parameterDescription 1 ROU "Тип характеристики для усл.1"
* @parameter 2 N
* @parameterDescription 2 ENG "Значение характеристики"
* @parameterDescription 2 RUS "Значение характеристики"
* @parameterDescription 2 ROU "Значение характеристики"
* @parameter 3 N
* @parameterDescription 3 ENG "Тип события взыскания ДЗ усл.2"
* @parameterDescription 3 RUS "Тип события взыскания ДЗ усл.2"
* @parameterDescription 3 ROU "Тип события взыскания ДЗ усл.2"
* @parameter 4 N
* @parameterDescription 4 ENG "Шаблон процесса взыскания усл.3"
* @parameterDescription 4 RUS "Шаблон процесса взыскания усл.3"
* @parameterDescription 4 ROU "Шаблон процесса взыскания усл.3"
* @parameter 5 Y
* @parameterDescription 5 ENG "Условие 1 (1/0)"
* @parameterDescription 5 RUS "Условие 1 (1/0)"
* @parameterDescription 5 ROU "Условие 1 (1/0)"
* @parameter 6 Y
* @parameterDescription 5 ENG "Условие 2 (1/0)"
* @parameterDescription 5 RUS "Условие 2 (1/0)"
* @parameterDescription 5 ROU "Условие 2 (1/0)"
* @parameter 7 Y
* @parameterDescription 5 ENG "Условие 3 (1/0)"
* @parameterDescription 5 RUS "Условие 3 (1/0)"
* @parameterDescription 5 ROU "Условие 3 (1/0)"
* 
* 
*/

public class PlanningInspectionAlgComp_Impl extends PlanningInspectionAlgComp_Gen implements SaTypeSeveranceCriteriaAlgorithmSpot
{
	private Bool Result;
	ServiceAgreement H1;
	CollectionProcessTemplate findedTemplate;
	Logger logger = LoggerFactory.getLogger(PlanningInspectionAlgComp_Impl.class);
	
	String S1;
	String S2;
	String S3;
	String S4;
	int S5;
	int S6;
	int S7;
	
	int iS5;
	int iS6;
	int iS7;
	
	@Override
	public void invoke() {

		logger.info("Entering invoke ...");
		iS5 = 0;
		iS6 = 0;
		iS7 = 0;
		Result = Bool.FALSE;
		checkSoftParams();
		
		
		
		if(isBlankOrNull(S1)&&isBlankOrNull(S3)&&isBlankOrNull(S4)){
			Result = Bool.TRUE;
			return;
		}
		
		if(isBlankOrNull(S1)){
			iS5 = 1;
		}else checkS5();
		
		if(isBlankOrNull(S3)){
			iS6 = 1;
		}else checkS6();
		
		if(isBlankOrNull(S4)){
			iS7 = 1;
		}else checkS7();
		
		if((S5 == iS5)&&(S6 == iS6)&&(S7 == iS7))
			Result = Bool.TRUE; else
				Result = Bool.FALSE;
		
		logger.info("Result: " + Result.toString());
		logger.info("Finish invoke ...");
		
	}
	
	private void checkS7(){
	  if(findedTemplate != null)
		  if(findedTemplate.getId().getIdValue().trim().equals(S4.trim()))
			  iS7 = 1;
	}
	
	private void checkS6(){
		PreparedStatement stm = null;
		
		try{
			stm = SessionMethods.createPreparedStatement("select COLL_PROC_TMPL_CD from CI_COLL_PROC ccp "
					+ "left join ci_coll_evt cce on ccp.coll_proc_id = cce.coll_proc_id "
					+ "where COLL_STATUS_FLG = '10' and coll_evt_typ_cd = :S3 and "
					+ "COLL_CL_CNTL_CD in( select COLL_CL_CNTL_CD from CI_COLL_CL_CNTL ccc "
					  + "left join ci_sa_type cst on ccc.debt_cl_cd = cst.debt_cl_cd and ccc.cis_division = cst.cis_division "
					  + "left join ci_gl_division cgd on cgd.currency_cd = ccc.currency_cd "
					  + "left join ci_sa sa on sa.sa_type_cd = cst.sa_type_cd "
					  + "left join ci_acct ca on ca.acct_id = sa.acct_id  where sa.sa_id = :sa)");
			stm.bindId("S3", new CollectionEventType_Id(S3));
			stm.bindEntity("sa", H1);
			stm.firstRow();
			if (stm.list().size() != 0) 
			{
				iS6 = 1;
				findedTemplate = stm.firstRow().getEntity("COLL_PROC_TMPL_CD", CollectionProcessTemplate.class);
				logger.info("findedTemplate: " + findedTemplate.getId().toString());
			}
		}
		finally{
			if(stm != null)stm.close();
		}
	}
	
	private void checkS5(){
		PreparedStatement stm = null;
		ServicePoint sp = null;
		try{
			stm = SessionMethods.createPreparedStatement("select sp_id from ci_sa_sp sa_sp 	"
					+ "where sa_sp.sa_id = :serviceagreement and NVL(sa_sp.stop_dttm, SYSDATE) >= SYSDATE");
			stm.bindEntity("serviceagreement", H1);
			sp = stm.firstRow().getEntity("SP_ID", ServicePoint.class);
			logger.info("SP: " + (isNull(sp)?"NULL": sp.getId().getIdValue()));
		}
		finally{
			if(stm != null)stm.close();
		}

		if(sp == null){
			iS5 = 1;
		} else
			try
		{
				stm = SessionMethods.createPreparedStatement("select * from ci_sp_char where sp_id = :sp and char_type_cd = :ct and char_val = '" + S2 + "'");
				stm.bindEntity("sp", sp);
				stm.bindId("ct",  new CharacteristicType_Id(S1));
				stm.firstRow();
				if(stm.list().size() != 0)
					iS5 = 1;
		}
		finally{
			if(stm != null)stm.close();
		}
	}
	
	private void checkSoftParams(){
		S1 = this.getTypeChar();
		S2 = this.getCharVal();
		S3 = this.getColEvtTyp();
		S4 = this.getColProcTm();
		S5 = this.getCond1().intValue();
		S6 = this.getCond2().intValue();
		S7 = this.getCond3().intValue();
		
		logger.info("-> checkSoftParams");
		logger.info("S1: " + S1);
		logger.info("S2: " + S2);
		logger.info("S3: " + S3);
		logger.info("S4: " + S4);
		logger.info("S5: " + S5);
		logger.info("S6: " + S6);
		logger.info("S7: " + S7);
		
		CharacteristicType_Id ct = null;
		CharacteristicValue_Id cv = null;
		
		if(!isBlankOrNull(S1)){
			ct = new CharacteristicType_Id(S1);
			if(isNull(ct.getEntity()))
			{
				addError(MgMd07020MessageRepository.spCharTypeNotExist(S1));
			}
			logger.info("CharacteristicType check passed.");

		}
		
		if ((((isBlankOrNull(S1)) && (!isBlankOrNull(S2)))) ||
		(((isBlankOrNull(S2)) && (!isBlankOrNull(S1)))))
		{
			addError(MgMd07020MessageRepository.S1S2Toghetger());
		}
		
		if(!isBlankOrNull(S1)){
			cv = new CharacteristicValue_Id(ct, S2);
			if(isNull(cv.getEntity()))
			{
				addError(MgMd07020MessageRepository.valueForTypeNotFound(S2, S1));
			}
			logger.info("CharacteristicValue check passed.");
		}
		
		if(!isBlankOrNull(S3)){
			CollectionEventType_Id cet = new CollectionEventType_Id(S3);
			if(isNull(cet.getEntity()))
			{
				addError(MgMd07020MessageRepository.collEvtTypeNotFound(S3));
				
			}
			logger.info("CollectionEventType check passed.");
		}
		
		if(!isBlankOrNull(S4)){
			CollectionProcessTemplate_Id cpt = new  CollectionProcessTemplate_Id(S4);
			if(isNull(cpt.getEntity()))
			{
				addError(MgMd07020MessageRepository.collProcTmNotFound(S4));
			}
			logger.info("CollectionProcessTemplate check passed.");
		}
		
		if  (((S5 != 1)&&(S5 != 0))||
			((S6 != 1)&&(S6 != 0))||
			((S7 != 1)&&(S7 != 0))){
			addError(MgMd07020MessageRepository.s5S6OrS7IsInvalid());
		}
	}

	@Override
	public Bool getIsCriteriaSatisfied() {
		// TODO Auto-generated method stub
		return Result;
	}


	@Override
	public void setServiceAgreement(ServiceAgreement paramServiceAgreement) {
		H1 = paramServiceAgreement;
		logger.info("SA: " + H1.getId().getIdValue());
	}

	@Override
	public void setSeveranceProcessTemplate(
			SeveranceProcessTemplate paramSeveranceProcessTemplate) {
		// TODO Auto-generated method stub
	}


	@Override
	public void setCriteriaPriorityLookup(
			CriteriaPriorityLookup paramCriteriaPriorityLookup) {
		// TODO Auto-generated method stub
	}

}