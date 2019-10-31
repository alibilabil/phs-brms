import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

ResponseObject response =WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML Flag/TC-1 DeleteRider', [('transactionType') : transactionType, ('subAlter') : subAlter
            , ('eviCode') : eviCode, ('riderCode') : riderCode]))

WS.comment('Verify NML Flags value is equal 1')

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.nmlFlags[0]','1')

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)