import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

Random random = new Random()

final long compareHighestValueFsaDeathVsAddccMax = 50000000000

final long compareHighestValueFsaDeathVsAddccMin = 15000000001

long compareHighestValueFsaDeathVsAddcc = Math.abs(random.nextLong() % (compareHighestValueFsaDeathVsAddccMax - compareHighestValueFsaDeathVsAddccMin)) + compareHighestValueFsaDeathVsAddccMin

println(compareHighestValueFsaDeathVsAddcc)

WS.comment('Send request')
ResponseObject response =  WS.sendRequest(findTestObject('AlterationScoringRuleApps/Financial Non Employee/Non Ultimate/Object Request', [('transactionType') : transactionType
            , ('role') : role, ('increasingFsaFlag') : increasingFsaFlag, ('pecFlag') : pecFlag, ('occupationCode') : occupationCode, ('currency') : 'IDR'
            , ('compareHighestValueFsaDeathVsAddcc') : compareHighestValueFsaDeathVsAddcc]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())
println parseResponse
def attributes = parseResponse.request.alterationScoring[0].response.evidenceCode

println attributes
def expected = attributes.find({
		it.code == 'FE5'
	}).description=='Butuh LSAR, ABR, BSM, RoC, RNA, Bukti Aset'

WS.comment('Verify evidence code is FE5 and have description "Butuh LSAR, ABR, BSM, RoC, RNA, Bukti Aset"')

WS.verifyEqual(expected,true)

WS.comment('Verify response status code')

WS.verifyResponseStatusCode(response, 200)