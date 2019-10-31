import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Agent Validation Rules/TC2-NegativeCase', [('transactionType') : transactionType
            , ('subAlter') : subAlter, ('riderCode') : riderCode, ('flag') : flag, ('riskClass') : riskClass]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expected = attributes.find({ 
        it == 'AVR'
    })

WS.comment('Verify evidence code is AVR')

WS.verifyEqual(expected, 'AVR')

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)



