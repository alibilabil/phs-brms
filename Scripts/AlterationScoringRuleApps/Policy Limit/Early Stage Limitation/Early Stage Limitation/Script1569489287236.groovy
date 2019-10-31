import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/PolicyLimit/Early Stage Limitation/Ver.Check_1.387/Early Stage Limitation', [('transactionType') : transactionType
            , ('role') : role, ('subAlter') : subAlter, ('eviCode') : eviCode, ('riderCode') : riderCode, ('riskClass') : riskClass]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

boolean esl = parsingResponse.request.alterationScoring[0].response.earlyStageLimitation
println (esl)
def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode
println (evidenceCode)
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.earlyStageLimitation', expectedEarlyStageLimitation)

if(esl!=true)
{
	def actual = evidenceCode.find({
		it.code == expectedEvidenceCode
	}).description == expectedDescription

	WS.verifyEqual(actual, true,FailureHandling.CONTINUE_ON_FAILURE)
	if(actual==false)
	{
		println 'Evidence code tidak sesuai dengan expected evidence code'
	}
}
WS.verifyResponseStatusCode(response, 200)