import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

'Set age wtih use radnom method'
Random random = new Random()
int age = random.nextInt(35-16)+16
println age
ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML PPH/TC-2 NML PPH', [('transactionType') : transactionType, ('nmlFlag') : nmlFlag
            , ('currency') : currency, ('age') : age, ('sumAssuredMedicalLimit') :sumAssuredMedicalLimit]))

def slurper = new JsonSlurper()
def parseResponse = slurper.parseText(response.getResponseText())
String[] attributes =parseResponse.request.alterationScoring[0].response.evidenceCode.code
println attributes
def expectedEvidenceCode = attributes.find{
	it == 'AC2'
}
//WS.verifyEqual(expectedEvidenceCode, 'AC2')
WS.comment('Verify Response Status code is 200')
WS.verifyResponseStatusCode(response, 200)