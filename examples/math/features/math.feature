Feature: Do math

	Do some math

@add
Scenario: Add some numbers	
	When 4 and 5 are added
	Then The result is 9
	
@subtract
Scenario: Subtract some numbers
	When 4 and 5 are subtracted from 10
	Then The result is 1
	
@multiply
Scenario: Multiply some numbers
	When 4 and 5 are multiplied
	Then The result is 20