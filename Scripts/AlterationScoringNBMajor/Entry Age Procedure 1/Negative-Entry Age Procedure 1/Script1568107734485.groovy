import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/Entry Age Procedure 1/Version 1.0_Check_1.391/Object Request', 
        [('transactionType') : transactionType, ('productCode') : productCode, ('role') : role, ('age') : age]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

String[] evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode.code

println(evidenceCode)
boolean isContain = evidenceCode.contains(expectedEviCode)
if(isContain==false){
	println 'Expected code tidak ada'
}
WS.verifyEqual(isContain, false)
WS.verifyResponseStatusCode(response, 200)
