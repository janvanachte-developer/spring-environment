Feature: Load and refresh Spring environment from datasource

  Scenario: Application reads property available in database table from Spring environment
    Given following key-value properties exist in a database table
  | key1 | value1 |
    When an application reads property key1 from Spring environment
    Then this application should get value1

  Scenario: Application reads property available in properties file from Spring environment
    Given following key-value properties exist in a property file
      | key1 | value2 |
      | key2 | value2 |
    When an application reads property key2 from Spring environment
    Then this application should get value2

  Scenario: Application reads property available in database table and properties file from Spring environment
    Given following key-value properties exist in a database table
      | key1 | value1 |
    And following key-value properties exist in a property file
      | key1 | value2 |
      | key2 | value2 |
    When an application reads property key1 from Spring environment
    Then this application should get value1

  Scenario: Service client refreshes Spring environment with property (with different value) available in database table
    Given following key-value properties exist in a database table
      | key1 | value3 |
    And current Spring environment has following key-value properties
      | key1 | value1 |
    When a service client refreshes property key1
    And an application reads property key1 from Spring environment
    Then this application should get value3

#  Comment: Following is more part of a general database managament service
  Scenario: Service client updates property available in database table with a different value then available in Spring environment
    Given following key-value properties exist in a database table
      | key1 | value1 |
    And current Spring environment has following key-value properties
      | key1 | value1 |
    When a service client updates property key1 with value3
    And an application reads property key1 from Spring environment
    Then following key-value properties should exist in a database table
      | key1 | value3 |
    And this application should get value1

  Scenario: Service client updates property available (and sourced from) a properties file with a different value then available in Spring environment
    Given following key-value properties exist in a property file
      | key1 | value2 |
      | key2 | value2 |
    When a service client updates property key2 with value3
    And an application reads property key2 from Spring environment
    Then this application should get value2
    And following key-value properties exist in a property file
      | key1 | value2 |
      | key2 | value2 |
