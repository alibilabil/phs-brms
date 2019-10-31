import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/Eligibility TSA Rules/NegativeCase', [('transactionType') : transactionType, ('role') : role
            , ('alterType') : alterType, ('occupation') : occupation, ('vendorName') : vendorName, ('compareNewFsaDeathandTotalccWithoutWop') : compareNewFsaDeathandTotalccWithoutWop]))

def jsonSlurper = new JsonSlurper()
def content_length = response.getResponseBodyContent()

println(content_length)

WS.verifyResponseStatusCode(response, 200)

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.eligibityTsa', false)

WS.verifyElementPropertyValue(response, 'request.alterationScoring[0].response.evidenceCode[0].code', 'ETR')