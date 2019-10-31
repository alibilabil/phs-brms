import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/V.166/Magnum Pure Result/Accept With Evidence Code', [('transactionType') : transactionType
            , ('productCode') : productCode, ('alterType') : alterType, ('magnumDecision') : magnumDecision, ('code') : code]))

def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({
	it == 'MEC'
})

WS.comment('Verify evidence code is MEC')
WS.verifyEqual(expected, 'MEC')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)