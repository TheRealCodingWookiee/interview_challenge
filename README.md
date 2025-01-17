How to test this application
=============================

How to get a shorten URL:
* POST a request to localhost:8080/url with a pain/text as body
  * Valid URL example: 
    * https://www.google.com
  * Invalid URL example: 
    * google.com
* The response will be a hash linked to the URL provided if the URL is valid
  * If the URL is not valid, the response will be an error message with BAD_REQUEST status code

How to access the original URL:
* GET request to localhost:8080/url/{hash}
  * The response will be the original URL that was shortened with the POST request
  * If the hash is not valid, the response will be an error message with NOT_FOUND status code



Further ideas for improvements: 
* Improve the validation of URLs to also allow URLs that won't fail if they are not absolute (http/https)
* Another idea would be to validate if the URL exists at all
* Add DTO for the request and response instead of using String. This would help with the seperation of concerns and make the code more readable if classes get more complex
* We could redirect directly to the URL instead of returning the URL in the response
* If any of the endpoints would have in future huge traffic, I would suggest to seperate one of the calls to different microservice to avoid bottleneck (CQRS) which would also help with the scalability but also could become a complex driver