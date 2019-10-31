import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

ResponseObject response =WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML Flag/TC-3 Increase SA', [('transactionType') : transactionType, ('subAlter') : subAlter
            , ('eviCode') : eviCode, ('riderCode') : riderCode]))

WS.comment('Verify NML Flags value is equal 3')
WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.nmlFlags[0]', 3)
WS.comment('Verify Response Status Code is 200')
WS.verifyResponseStatusCode(response, 200)