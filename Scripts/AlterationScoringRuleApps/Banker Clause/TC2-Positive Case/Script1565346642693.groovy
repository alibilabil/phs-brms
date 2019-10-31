import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Banker Clause/TC2-PositiveCase', [('transactionType') : transactionType, ('role') : role
            , ('followUpCodeBkcOrLbc') : followUpCodeBkcOrLbc, ('bankersFlag') : bankersFlag]))
def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.bankersClause
String expectedValue = 'STD'
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.bankersClause', expectedValue)
WS.comment('Verify response status code is 200')
WS.verifyResponseStatusCode(response, 200)