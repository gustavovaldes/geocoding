Feature: Geocoding test

  Scenario:Happy path
    Given External services are available
    When I made a GET request to the geocoding endppoint with address Santiago
    Then I receive HTTP status 200
    And The values received are
      | address                                       | latitude    | longitude   |
      | Santiago, Santiago Metropolitan Region, Chile | -33.4488897 | -70.6692655 |
