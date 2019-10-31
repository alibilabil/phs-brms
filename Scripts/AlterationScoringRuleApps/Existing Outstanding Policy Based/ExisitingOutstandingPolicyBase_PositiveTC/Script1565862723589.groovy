import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Existing Outstanding Policy Based/Existing Outstanding Policy Based Positive', 
        [('transactionType') : transactionType, ('role') : role, ('docId') : docId, ('subAlter') : subAlter, ('riderCode') : riderCode]))

def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

def attributes = parseResponse.request.alterationScoring[0].response.evidenceCode
//def expected = attributes.find({
//	it.code == 'NLG'
//})
def expectedOutput = attributes.find({
	it.code == 'EOP'
}).description == 'Ada pengajuan perubahan minor di polis ini '

WS.comment('Verify evidence code is EOP and description of evidence code is equal "Ada pengajuan perubahan minor di polis ini"')
WS.verifyEqual(expectedOutput, true)
WS.comment('Verify evidence code is NLG')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)