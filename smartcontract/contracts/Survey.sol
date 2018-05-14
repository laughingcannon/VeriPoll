pragma solidity ^0.4.17;

contract Survey {

	modifier onlyOwner() {
    	require(msg.sender == owner);
    	_;
	}

	modifier onlyRespondent() {
    	require(validRespondents[msg.sender] != 0 && msg.sender != owner);
    	_;
	}

	modifier onlyCanVote() {
		require(validRespondents[msg.sender] == 1);
		_;
	}

	struct Question {
		uint[] questionResponses;
	}

    address owner;
	Question[] questions;
	mapping (address => uint) validRespondents;

	function Survey() public {
		owner = msg.sender;
	}

	function makeQuestion(uint numResponses) public onlyOwner() {
		uint questionID = questions.length++;
		Question storage q = questions[questionID];
		q.questionResponses = new uint[](numResponses);
	}

	function makeRespondent(address respondentID) public onlyOwner() {
		validRespondents[respondentID] = 1;
	}
	
	function placeResponse(uint questionID, uint responseID) public onlyRespondent() onlyCanVote() {
		Question storage q = questions[questionID];
		q.questionResponses[responseID]++;
		validRespondents[msg.sender] = 2;
	}

	function getQuestionResponse(uint questionID, uint responseID) public view returns (uint) {
		Question storage q = questions[questionID];
		return q.questionResponses[responseID];
	}

}