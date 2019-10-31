import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

'Set age wtih use radnom method'
Random random = new Random()
int maxAge = 150;
int minAge = 51;
int age = random.nextInt(maxAge - minAge) + minAge;
println age
int maxSumAssuredMedicalLimit = 1500000;
int minSumAssuredMedicalLimit = 1000001;
int sumAssuredMedicalLimit = random.nextInt(maxSumAssuredMedicalLimit-minSumAssuredMedicalLimit) + minSumAssuredMedicalLimit;
println sumAssuredMedicalLimit

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML CHC/NML CHC', [('transactionType') : transactionType
            , ('nmlFlag') : nmlFlag, ('currency') : currency, ('age') : age, ('sumAssuredMedicalLimit') : sumAssuredMedicalLimit]))

def slurper = new JsonSlurper()

def parseResponse = slurper.parseText(response.getResponseText())

String[] attributes = parseResponse.request.alterationScoring[0].response.evidenceCode.code

def expectedEvidenceCode = attributes.find({ 
        it == 'T3C'
    })

//WS.verifyEqual(expectedEvidenceCode, 'T3C')

WS.comment('Verify Response Status code is 200')

WS.verifyResponseStatusCode(response, 200)

