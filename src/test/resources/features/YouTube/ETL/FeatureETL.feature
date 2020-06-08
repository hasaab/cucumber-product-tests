#Author: hiqatech@gmail.com
#Keywords Summary : ETL Tests

Feature: YouTubeETL Tests

  Scenario: YouTubeETL - I am comparing result files
  Given I read the "Book1.csv" file from "E:\ZolStaff\"
  And I read the "Book2.csv" file from "E:\ZolStaff\"
  Then I diff "file1-file2 & file2-file1" and write into "bookDiff.csv" in "E:\ZolStaff\"
