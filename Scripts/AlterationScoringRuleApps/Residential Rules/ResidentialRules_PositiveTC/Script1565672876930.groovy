import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Residential Rules/TC1-Positive Case', [('transactionType') : transactionType
            , ('role') : role, ('subAlter') : subAlter, ('subAlterPlan') : subAlterPlan, ('riderCode') : riderCode, ('province') : province
            , ('flagEhsOrEsl') : flagEhsOrEsl]))
def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({
	it == 'RER'
})

WS.comment('Verify evidence code is RER')
WS.verifyEqual(expected, 'RER')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)

