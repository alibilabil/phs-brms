import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NLG Indicator/Indicator NLG Positive', [('transactionType') : transactionType
            , ('alterType') : alterType, ('clientType') : clientType, ('totalcoi') : totalcoi, ('debtAmount') : debtAmount
            , ('nlgIndicator') : nlgIndicator, ('cashValue') : cashValue]))

def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

def attributes = parseResponse.request.alterationScoring[0].response.evidenceCode
//def expected = attributes.find({
//	it.code == 'NLG'
//})
def expectedOutput = attributes.find({
	it.code == 'NLG'
}).description == 'To processor karena NLG indicator 0'

WS.comment('Verify evidence code is NLG and description of evidence code is equal "To processor karena NLG indicator 0"')
WS.verifyEqual(expectedOutput, true)
WS.comment('Verify evidence code is NLG')
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)