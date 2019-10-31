import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/AnnualPremiumForPNS/Version 1.0_Check_1.391/ObjectRequest', [('transactionType') : transactionType
            , ('clientType') : clientType, ('role') : role, ('occupationCode') : occupationCode, ('subAlter') : subAlter, ('eviCode') : eviCode, ('totalPremiumPerYear') : totalPremiumPerYear]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println parsingResponse

def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode
println evidenceCode
def actual = evidenceCode.find({
	it.code==expectedEviCode
}).description==expectedDescription
WS.verifyEqual(actual, true)

WS.verifyResponseStatusCode(response, 200)