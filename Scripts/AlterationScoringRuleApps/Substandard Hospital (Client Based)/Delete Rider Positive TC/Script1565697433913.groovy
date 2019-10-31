import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Substandard Hospital (Client Based)/Delete Rider Positive', [('transactionType') : transactionType
            , ('role') : role, ('subAlter') : subAlter, ('riderCode') : riderCode, ('substandardHospitalFlag') : substandardHospitalFlag]))
def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({
	it == 'RRS'
})
WS.comment('Verify substandardhospital value is equal true')
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.substandardHospital', true)
WS.comment('Verify evidence code is RRS')
WS.verifyEqual(expected, 'RRS')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)
