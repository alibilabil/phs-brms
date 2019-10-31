import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper


Random random = new Random()

final long compareHighestValueFsaDeathVsAddccMax = 625000

final long compareHighestValueFsaDeathVsAddccMin = 0

long compareHighestValueFsaDeathVsAddcc =Math.abs(random.nextLong() % (compareHighestValueFsaDeathVsAddccMax - compareHighestValueFsaDeathVsAddccMin)) + compareHighestValueFsaDeathVsAddccMin

println(compareHighestValueFsaDeathVsAddcc)
WS.comment('Send request')
ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Financial Employee/Non Ultimate/Object Request', [('transactionType') : transactionType
            , ('role') : role, ('increasingFsaFlag') : increasingFsaFlag, ('pecFlag') : pecFlag, ('occupationCode') : occupationCode, ('currency') : 'USD'
            , ('compareHighestValueFsaDeathVsAddcc') : compareHighestValueFsaDeathVsAddcc]))

JsonSlurper slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())

def evidenceCode = parseResponse.request.alterationScoring[0].response.evidenceCode

println(evidenceCode)

WS.comment('verify response status code is 200')
WS.verifyResponseStatusCode(response, 200)