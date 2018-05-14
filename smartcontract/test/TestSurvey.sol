pragma solidity ^0.4.17;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";

import "../contracts/Survey.sol";

contract TestSurvey {

	// Survey survey = Survey(DeployedAddresses.Survey());
	Survey survey = new Survey();

	function testPlaceResponse() public {

		survey.makeQuestion(3);
		Assert.equal(survey.getQuestionResponse(0, 2), 0, "not initilizing question");

		// survey.makeRespondent("0xe99356bde974bbe08721d77712168fa070aa8da4");

		// survey.placeResponse(0, 0);
		// Assert.equal(survey.getQuestionResponse(0, 0), 1, "not placing responses");
		// Assert.equal(survey.getQuestionResponse(0, 1), 0, "responses not originally zero");

		// survey.placeResponse(0, 1);
		// survey.placeResponse(0, 0);
		// Assert.equal(survey.getQuestionResponse(0, 0), 2, "not adding to responses");
		// Assert.equal(survey.getQuestionResponse(0, 1), 1, "not placing additional responses");
		// Assert.equal(survey.getQuestionResponse(0, 2), 0, "responses not originally zero");

		// survey.makeQuestion(4);
		// survey.placeResponse(1, 0);
		// Assert.equal(survey.getQuestionResponse(1, 0), 1, "not adding new questions");
		// Assert.equal(survey.getQuestionResponse(0, 1), 1, "not saving past responses");
		// Assert.equal(survey.getQuestionResponse(0, 0), 2, "not saving past responses");
	}

	// function testPlaceResponse() public {
	// 	survey.placeResponse(0, 0);
	// }
}