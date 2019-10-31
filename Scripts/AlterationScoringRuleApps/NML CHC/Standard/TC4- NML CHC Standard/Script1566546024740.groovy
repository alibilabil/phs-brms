import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

'Set age wtih use radnom method'
Random random = new Random()

int maxAge = 50

int minAge = 41

int age = random.nextInt(maxAge - minAge) + minAge

println(age)

int maxSumAssuredMedicalLimit = 750000

int minSumAssuredMedicalLimit = 0

int sumAssuredMedicalLimit = random.nextInt(maxSumAssuredMedicalLimit - minSumAssuredMedicalLimit) + minSumAssuredMedicalLimit

println(sumAssuredMedicalLimit)

ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/NML CHC/NML CHC', [('transactionType') : transactionType
            , ('nmlFlag') : nmlFlag, ('currency') : currency, ('age') : age, ('sumAssuredMedicalLimit') : sumAssuredMedicalLimit]))

WS.comment('Verify response status code')

WS.verifyResponseStatusCode(response, 200)

