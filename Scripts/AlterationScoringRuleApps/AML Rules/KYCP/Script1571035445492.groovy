import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.testng.Assert as Assert

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/AML Rules/KYCP Rules/Object Request', [('transactionType') : transactionType
            , ('role') : role, ('subAlter') : subAlter, ('eviCode') : eviCode, ('payorIncome') : payorIncome, ('otherPolicyTotalPremium') : otherPolicyTotalPremium]))

if (response.getStatusCode() == 500) {
    println('Request bermasalah')
}

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

boolean kycp = parsingResponse.request.alterationScoring[0].response.kycp

println(kycp)

WS.verifyEqual(kycp, expectedKycpValue)

String[] evidence = parsingResponse.request.response.evidenceCode

String[] evidenceCode = parsingResponse.request.response.evidenceCode.code

String[] evidenceDesc = parsingResponse.request.response.evidenceCode.description

println(evidence)

if (evidence != null) {
    for (int i = 0; i < evidence.length; i++) {
        if (evidenceCode[i] == expectedEvidenceCode) {
			println evidenceCode[i]
			WS.verifyEqual(evidenceCode[i], expectedEvidenceCode)
			WS.verifyEqual(evidenceDesc[i], expectedEvidenceDesc)
            println('Expected evidence code dan expected evidence description sesuai dengan actual')
        }
		else{
			println 'Expected evidence code dan expected evidence description tidak sesuai dengan actual'
		}
    }
	} 
else {
    println('EvidenceCode Kosong')
}

WS.verifyResponseStatusCode(response, 200)

println('Sukses')