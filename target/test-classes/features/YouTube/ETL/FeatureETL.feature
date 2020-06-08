#Author: hiqatech@gmail.com
#Keywords Summary : ETL Tests

Feature: YouTubeETL Tests

  Scenario: YouTubeETL - I am comparing result files
  Given I read the "Book1.csv" file from "E:\ZolStaff\HiQATech\"
  And I read the "Book2.csv" file from "E:\ZolStaff\HiQATech\"
  Then I diff "file1-file2" and write into "book1-2.csv" in "E:\ZolStaff\HiQATech\"
  Then I diff "file2-file1" and write into "book2-1.csv" in "E:\ZolStaff\HiQATech\"
