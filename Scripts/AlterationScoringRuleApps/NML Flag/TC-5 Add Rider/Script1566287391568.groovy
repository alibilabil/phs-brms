import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML Flag/TC-5 AddRider', [('transactionType') : transactionType
            , ('subAlter') : subAlter, ('eviCode') : eviCode, ('riderCode') : riderCode]))

WS.comment('Verify NML Flags value is equal 5')

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.nmlFlags[0]', 5)

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)

