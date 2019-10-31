import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Substandard Hospital (Client Based)/Increase Term Positive', [('transactionType') : transactionType
            , ('role') : role, ('subAlter') : subAlter, ('riderCode') : riderCode, ('substandardHospitalFlag') : substandardHospitalFlag]))

def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({
	it == 'ENS'
})
WS.comment('Verify substandardhospital value is equal true')
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.substandardHospital', true)
WS.comment('Verify evidence code is ENS')
WS.verifyEqual(expected, 'ENS')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)