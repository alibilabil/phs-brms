import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Increase MSA Flag/Increase MSA Flag Positive', [('increasingMsaDeath') : increasingMsaDeath
            , ('increasingMsaAddcc') : increasingMsaAddcc]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.increasingMsaFlag', 'Y')

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)

