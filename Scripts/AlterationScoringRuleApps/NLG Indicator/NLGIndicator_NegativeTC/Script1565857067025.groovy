import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

ResponseObject response =WS.sendRequest(findTestObject('AlterationScoringRuleApps/NLG Indicator/Indicator NLG Negative', [('transactionType') : transactionType
            , ('alterType') : alterType, ('clientType') : clientType, ('totalcoi') : totalcoi, ('debtAmount') : debtAmount
            , ('nlgIndicator') : nlgIndicator, ('cashValue') : cashValue]))


WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)