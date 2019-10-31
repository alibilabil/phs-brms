import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/V.166/Magnum Pure Result/Accept Without Evidence Code', 
        [('transactionType') : transactionType, ('productCode') : productCode, ('alterType') : alterType, ('magnumDecision') : magnumDecision
            , ('code') : code]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)



