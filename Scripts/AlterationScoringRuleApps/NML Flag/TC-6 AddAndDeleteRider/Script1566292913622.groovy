import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML Flag/TC-6 AddAndDeleteRider', [('transactionType') : 'Major'
            , ('subAlter') : 'AddRider', ('eviCode') : 'IRD', ('riderCode') : 'C1WD', ('subAlter1') : 'DeleteRider', ('eviCode1') : 'ERD'
            , ('riderCode1') : 'C1LD', ('ageNextBirthday') : '16', ('saIncrementccb') : '300000000']))

WS.comment('Verify NML Flags value is equal 6')

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.nmlFlags[0]', 6)

WS.comment('Verify Response Status Code is 200')

WS.verifyResponseStatusCode(response, 200)


