import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/Policy in Other Company/Version 1.0_Check_1.391/Object Request', 
        [('transactionType') : transactionType, ('subAlter') : subAlter, ('eviCode') : eviCode, ('riderCode') : riderCode, ('role') : role, ('exsitingPolicyForm') : exsitingPolicyForm
            , ('policySubstandard') : policySubstandard]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode

println(evidenceCode)

def actual = evidenceCode.find({
		it.code == expectedEvidenceCode
	}).description == expectedDescription

WS.verifyEqual(actual, true)

WS.verifyResponseStatusCode(response, 200)