import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


Random random = new Random()

final long compareHighestValueFsaDeathVsAddccMax = 8000000000

final long compareHighestValueFsaDeathVsAddccMin = 5000000001

long compareHighestValueFsaDeathVsAddcc = Math.abs(random.nextLong() % (compareHighestValueFsaDeathVsAddccMax - compareHighestValueFsaDeathVsAddccMin)) + compareHighestValueFsaDeathVsAddccMin
println compareHighestValueFsaDeathVsAddcc

WS.comment('Send request')
ResponseObject response =  WS.sendRequest(findTestObject('AlterationScoringRuleApps/Financial Non Employee/Ultimate/Object Request', [('transactionType') : transactionType
            , ('role') : role, ('increasingFsaFlag') : increasingFsaFlag, ('pecFlag') : pecFlag, ('occupationCode') : occupationCode, ('currency') : 'IDR'
            , ('compareHighestValueFsaDeathVsAddcc') : compareHighestValueFsaDeathVsAddcc]))

WS.comment('Verify response status code')

WS.verifyResponseStatusCode(response, 200)
