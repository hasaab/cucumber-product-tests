#Author: hiqatech@gmail.com
#Keywords Summary : Jsonplaceholder fake REST Service Test

Feature:  Jsonplaceholder fake REST Service Test

  @YouTubeRESTSmoke
  Scenario:YoutubeREST -  Getting simple Post by random user and validate details
    When Sending next REST request with next data
      |service  |FakeREST					    |
      |request  |posts?userId=random_number   |
      |type	    |GET						    |
    Then Verifying next REST response details
      |expected1   |userId   |
      |expected2   |id       |
      |expected3   |title    |
      |expected4   |body     |

  @YouTubeRESTRegression
  Scenario: YoutubeREST - Getting simple Comment by random user and validate email address
    When Sending next REST request with next data
      |service  |FakeREST 		                  |
      |request  |posts/random_number/comments     |
      |type	    |GET				              |
    Then Verifying next REST response details
      |validate1  |email_address   |

  @YouTubeRESTRegression
  Scenario:YoutubeREST -  Sending simple Post by random user and validate response 200 OK
    When Sending next REST request with next data
      |service|FakeREST 		                |
      |request|posts/                           |
      |type	  |POST				                |
      |postId |1                      |
      |Id     |1                      |
      |name   |zoltan                 |
      |email  |kiszols@yahoo.com      |
      |body   | I am testing :)       |
    Then Verifying next REST response details
      |expected1   |name      |
      |expected2   |postId    |
      |expected3   |Id        |
      |expected4   |id        |
      |expected5   |body      |
      |expected6   |email     |