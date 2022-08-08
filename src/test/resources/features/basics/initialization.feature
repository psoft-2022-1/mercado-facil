Feature: the initializated products can be retrieved

  Scenario: client makes call to GET /api/produtos
    When the client list produtos by calling /api/produtos
    And the client retrives a list of 5 elements
    Then check if the ID of the first product is 10001
    
  Scenario: client makes call to GET /api/lotes
    When the client list lotes by calling /api/lotes
    And the client retrives a list of 1 elements

  Scenario: client makes call to GET /api/clientes
    When the client list clientes by calling /api/clientes
    And the client retrives a list of 1 elements  