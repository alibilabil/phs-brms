import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

Random random = new Random()

final long compareHighestValueFsaDeathVsAddccMax = 2250000

final long compareHighestValueFsaDeathVsAddccMin = 1000001

long compareHighestValueFsaDeathVsAddcc = Math.abs(random.nextLong() % (compareHighestValueFsaDeathVsAddccMax - compareHighestValueFsaDeathVsAddccMin)) + compareHighestValueFsaDeathVsAddccMin

println(compareHighestValueFsaDeathVsAddcc)

WS.comment('Send request')

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Financial Non Employee/Ultimate/Object Request', 
        [('transactionType') : transactionType, ('role') : role, ('increasingFsaFlag') : increasingFsaFlag, ('pecFlag') : pecFlag, ('occupationCode') : occupationCode
            , ('currency') : 'USD', ('compareHighestValueFsaDeathVsAddcc') : compareHighestValueFsaDeathVsAddcc]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

println(parseResponse)

def attributes = parseResponse.request.alterationScoring[0].response.evidenceCode

println(attributes)

def expected = attributes.find({ 
        it.code == 'FU4'
    }).description == 'Butuh LSAR, ABR'

WS.comment('Verify evidence code is FU4 and have description "Butuh LSAR, ABR"')

WS.verifyEqual(expected, true)

WS.comment('Verify response status code')

WS.verifyResponseStatusCode(response, 200)

