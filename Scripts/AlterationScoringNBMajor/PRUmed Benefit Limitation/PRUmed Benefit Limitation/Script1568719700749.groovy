import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/PRUmed Benefit Limitation/Version 1.0_Check_1.391/Request Object', 
        [('transactionType') : transactionType, ('clientType') : clientType, ('role') : role, ('subAlter') : subAlter, ('eviCode') : eviCode, ('riderCode') : riderCode
            , ('occupationCode') : occupationCode, ('income') : income, ('exsitingClientPrumed') : exsitingClientPrumed, ('totalUnitPrumed') : totalUnitPrumed]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode

println(evidenceCode)
println expectedEvidenceCode
if(expectedEvidenceCode!='STD')
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
WS.comment('Check response status code')
WS.verifyResponseStatusCode(response, 200)