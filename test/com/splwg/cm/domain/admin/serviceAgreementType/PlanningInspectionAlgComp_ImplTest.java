package com.splwg.cm.domain.admin.serviceAgreementType;

import java.util.ArrayList;
import java.util.List;

import com.splwg.base.api.datatypes.Bool;
import com.splwg.base.api.datatypes.Date;
import com.splwg.ccb.domain.customerinfo.account.entity.Account_Id;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.entity.ServiceAgreement;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.entity.ServiceAgreement_Id;
import com.splwg.ccb.domain.admin.nonBilledBudgetRule.entity.NonBilledBudgetRule_Id;
import com.splwg.shared.logging.Logger;
import com.splwg.shared.logging.LoggerFactory;

import ru.ibs.test.framework.algorithm.AlgorithmTestCase;

public class PlanningInspectionAlgComp_ImplTest extends AlgorithmTestCase<PlanningInspectionAlgComp_Impl> {

	@Override
	protected Class<PlanningInspectionAlgComp_Impl> getAlgorithmImplementationClass() {
		return PlanningInspectionAlgComp_Impl.class;
	}
	
	public void testAlgorithm() {
		Logger logger = LoggerFactory.getLogger(PlanningInspectionAlgComp_Impl.class);
		PlanningInspectionAlgComp_Impl algorithm;
		List<String> parameters = new ArrayList<String>();
		
		parameters.clear();
		parameters.add("PODKL");
		parameters.add("Y");
		parameters.add("UVED");
		parameters.add("BAL-FL");
		parameters.add("1");
		parameters.add("1");
		parameters.add("1");
		logger.info("-----------------------------------START-----------------------------------------");
		algorithm = createAlgorithmCobol(parameters);
		logger.info("setServiceAgreement ...");
		algorithm.setServiceAgreement(new ServiceAgreement_Id("0153125544").getEntity());
		
		
		//algorithm.setDateAccount(H4);
		//algorithm.	
		logger.info("Run invoke ...");
		algorithm.invoke();
		
        logger.info("Alg Result: " + (algorithm.getIsCriteriaSatisfied().equals(Bool.TRUE)?"TRUE":"FALSE"));
		logger.info("-----------------------------------FINISH-----------------------------------------");
	}

}
