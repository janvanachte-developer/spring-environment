Feature: Load Spring environment from datasource

  Scenario: Property available in database table
    Given Following key-value properties exist in a specified database table
  | key | value |
  | key1 | value1 |
    When Application reads key key1 from Spring environment
    Then Application should get value value1

  Scenario: Property available in properties file
    Given Following key-value properties exist in a specified property file
  | key | value |
  | key1 | value2 |
    When Application reads key key1 from Spring environment
    Then Application should get value value2

  Scenario: Property available in database table and in properties file
    Given Following key-value properties exist in a specified database table
  | key | value |
  | key1 | value1 |
    And Following key-value properties exist in a specified property file
  | key | value |
  | key1 | value2 |
    When Application reads key key1 from Spring environment
    Then Application should get value value1
