import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

WS.comment("Send request")
ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/Occupation Need Questionnaire/Version 1.0_Check_1.391/Occupation Need Questionnaire', 
        [('clientType') : clientType, ('role') : role, ('alterType') : alterType, ('occupationCode') : occupationCode]))


def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode

println(evidenceCode)

def actual = evidenceCode.find({
		it.code == expectedEviCode
	}).description == expectedDescription

WS.verifyEqual(actual, true)

WS.verifyResponseStatusCode(response, 200)