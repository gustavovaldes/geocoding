Feature: Geocoding test

  Scenario:Happy path
    Given External services are available
    When I made a GET request to the geocoding endppoint with address Santiago
    Then I receive HTTP status 200
    And The values received are
      | address                                       | latitude    | longitude   |
      | Santiago, Santiago Metropolitan Region, Chile | -33.4488897 | -70.6692655 |

  Scenario:External Service not available
    Given External services are not available
    When I made a GET request to the geocoding endppoint with address China
    Then I receive HTTP status 503
    And The error received is like
      | error                    | errorDescription               | reference |
      | External Service problem | HTTP operation failed invoking | $any      |
