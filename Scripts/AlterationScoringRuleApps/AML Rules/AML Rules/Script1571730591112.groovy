import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

try {
    ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/AML Rules/Object Request', [('transactionType') : transactionType
                , ('role') : role, ('flagChangePayor') : flagChangePayor, ('paymentMethod') : paymentMethod, ('subAlter') : subAlter
                , ('eviCode') : eviCode, ('payorIncome') : payorIncome, ('otherPolicyTotalPremium') : otherPolicyTotalPremium]))
	
	if(response.getStatusCode() != 200)
	{
		println('Response Code : ' + response.getStatusCode())
		println(response.getResponseBodyContent())
	}
	WS.verifyResponseStatusCode(response, 200)
	def slurper = new JsonSlurper()
	
	def parsingResponse = slurper.parseText(response.getResponseText())
	
	println(parsingResponse)
}
catch (IllegalStateException e) {
    this.println(e)
}