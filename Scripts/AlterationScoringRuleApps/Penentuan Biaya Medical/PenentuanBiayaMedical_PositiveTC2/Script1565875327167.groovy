import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Penentuan Biaya Medical/Penentuan Biaya Medical Positive 1', 
        [('transactionType') : transactionType, ('policyStatus') : policyStatus, ('subAlter') : subAlter, ('eviCode') : eviCode]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.medicalFee', 'Client')

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)



