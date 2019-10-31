import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScroingNBMajor/Occupation PHS Flag/Version 1.0_Check_1.391/ObjectRequest', [('transactionType') : transactionType
            , ('clientType') : clientType, ('role') : role, ('subAlter') : subAlter, ('eviCode') : eviCode, ('riderCode') : riderCode, ('occupationCode') : occupationCode]))

def slurper = new JsonSlurper()

def parsingResponse = slurper.parseText(response.getResponseText())

println(parsingResponse)

def evidenceCode = parsingResponse.request.alterationScoring[0].response.evidenceCode

println(evidenceCode)

def actual = evidenceCode.find({ 
        it.code == expectedEviCode
    }).description == expectedDescription

WS.verifyEqual(actual, true)

WS.verifyResponseStatusCode(response, 200)


