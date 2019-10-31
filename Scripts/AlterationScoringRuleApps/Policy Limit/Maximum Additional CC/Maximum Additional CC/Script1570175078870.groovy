import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/PolicyLimit/Maximum Additional CC/Ver.Check_1.387/Max Add CC', 
        [('transactionType') : transactionType, ('alterType') : alterType, ('role') : role, ('occupationCode') : occupationCode, ('fsaAddCCLimit') : fsaAddCCLimit]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

boolean esl = parsingResponse.request.alterationScoring[0].response.maximumAdditionalcc
println (esl)
def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode
println (evidenceCode)
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.maximumAdditionalcc', expectedMaximumAdditionalcc)

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