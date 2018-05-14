# VeriPoll: Blockchain for surveys
#### A decentralized solution that promotes transparency, anonymity, and immutability in survey responses.  

Note: For Testing Purposes, please clone this repository to your local computer.

## Blockchain Set Up  | Author : Jasmine Singh

### Truffle installation
Truffle is a framework for blockchain development and testing.  Install Truffle by following the directions here: http://truffleframework.com/.  Make sure to install any relevant dependencies.

### Ganache installation
Ganache is a test ethereum blockchain.  Follow the directions here to install: http://truffleframework.com/ganache/.  

## Running the Website | Author : Sudarshan Gopalakrishnan
To start the website, please follow the instructions below:
- Open Terminal
- Run "brew install gradle" if you do not have gradle installed
- Switch directory to ./VeriPoll/website
- Run gradle run to start the server

Please visit http://localhost:4567/login to get started.

You may use the below credentials for testing purposes:
Public Key: 0x8dacfc01faf352953ff2ca80938647d30bb39b4e
Private Key: 7b172f463cc687dd91fda2ebe6e99b705ae0251d4dd3a920564adc3a4422a67a


## Running the Visualizer | Author : Sudarshan Gopalakrishnan
Unfortunately for the Miminum Viable Product, we were not able to integrate the data visualizer into the Java Spark Web App
However, you can test the "in-development" form of the visualizer by running the visualizer.py file

To run the file on Terminal, please switch directory to ./VeriPoll/visualizer and run python3 visualizer.py to view the data visualizations


## Authors
Blockchain Smart Contract: Jasmine Singh
Java Spark Web App: Sudarshan Gopalakrishnan
Visualizer: Abhinav Kannan
