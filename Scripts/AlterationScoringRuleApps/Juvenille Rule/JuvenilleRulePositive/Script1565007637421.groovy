import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Juvenille Rules/PositiveCase', [('transactionType') : transactionType
            , ('role') : role, ('owner') : owner, ('ageNextBirthday') : ageNextBirthday, ('newMsaAdditionalcc') : newMsaAdditionalcc, ('newMsaDeath') : newMsaDeath
            , ('subAlter') : subAlter, ('riderCode') : riderCode]))

def jsonSlurper = new JsonSlurper()

def response_body = jsonSlurper.parseText(response.getResponseText())

String[] attributes = response_body.request.alterationScoring[0].response.evidenceCode.code

def name2EqValue2 = attributes.find({ 
        it == 'JVR'
    })

WS.verifyEqual(name2EqValue2, 'JVR')

WS.verifyResponseStatusCode(response, 200)