import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Agent License for PPH and PPH Plus/PositiveCase', [('transactionType') : transactionType
            , ('subAlter') : subAlter, ('riderCode') : riderCode, ('agentLicensepph') : agentLicensepph]))
def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({
	it == 'ALP'
})

WS.comment('Verify evidence code is ALP')
WS.verifyEqual(expected, 'ALP')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)