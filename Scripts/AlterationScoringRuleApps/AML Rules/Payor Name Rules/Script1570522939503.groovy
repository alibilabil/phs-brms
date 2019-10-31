import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.testng.Assert

import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

	ResponseObject response = WS.sendRequest(findTestObject('AlterationScoringRuleApps/AML Rules/Payor Name Rules/Object Request', [('transactionType') : transactionType
		, ('role') : role, ('flagChangePayor') : flagChangePayor, ('paymentMethod') : paymentMethod]))
	
	
	if(response.getStatusCode() == 500)
	{
		println "Request bermasalah"
	}
	def slurper = new JsonSlurper()

	def parsingResponse = slurper.parseText(response.getResponseText())

	println(parsingResponse)

	boolean payorName = parsingResponse.request.alterationScoring[0].response.payorName
	
	println(payorName)

	WS.verifyEqual(payorName, expectedPayorNameValue)
	String[] followUp = parsingResponse.request.response.followUp
	String[] followUpCode = parsingResponse.request.response.followUp.code
	String[] followUpDesc = parsingResponse.request.response.followUp.description
	println followUpCode
	
	if(followUp!= null)
	{
		for(int j=0;j<followUp.length;j++)
		{
			if(followUpCode[j]==expectedFollowUpCode){
				Assert.assertEquals(followUpDesc[j], expectedFollowUpDesc)
				println "Expected follow up code dan expected follow up description sesuai dengan actual"
			}
			else{
				println "FollowUp code tidak ada yang sesuai dengan actual followUp Code"
			}
		}
	}
	else{
		println "FollowUp kosong"
	}
	String[] evidence = parsingResponse.request.response.evidenceCode
	String[] evidenceCode = parsingResponse.request.response.evidenceCode.code
	String[] evidenceDesc = parsingResponse.request.response.evidenceCode.description
	println evidence
	if(evidence!= null)
	{
		for(int i=0;i<evidence.length;i++)
		{
			if(evidenceCode[i]==expectedEvidenceCode){
				Assert.assertEquals(evidenceDesc[i], expectedEvidenceDesc)
				println "Expected evidence code dan expected evidence description sesuai dengan actual"
			}
			else{
				println "Evidence code tidak ada yang sesuai dengan actual evidence Code"
			}
		}
	}
	else{
		println "EvidenceCode Kosong"
	}
	WS.verifyResponseStatusCode(response, 200)
	println('Sukses')



