import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Banker Clause/TC1-PositiveCase', [('transactionType') : transactionType, ('role') : role
            , ('followUpCodeBkcOrLbc') : followUpCodeBkcOrLbc, ('bankersFlag') : bankersFlag]))
def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code
println attributes
def expected = attributes.find({
	it == 'BKC'
})
println expected
WS.comment('Verify evidence code is BKC')
WS.verifyEqual(expected, 'BKC')
WS.comment('Verify response status code is 200')
WS.verifyResponseStatusCode(response, 200)