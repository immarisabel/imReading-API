# Resources

This document is for me to track links and guides which have useful material, techniques and advise for the building of the API.


## Design

- [Create REST api using Pure Java](https://www.boxuk.com/insight/creating-a-rest-api-quickly-using-pure-java/)
- [REST API Mistakes to avoid](https://www.youtube.com/watch?v=JxeTegu4dD8)
  - catch control header : how woudl this be applied to this project?

## Security

- [Getting API security right - Philippe De Ryck - NDC London 2023](https://www.youtube.com/watch?v=7UBm8QFTaq0)
  - DTO along with Model and Entity classes in order to encapsulate data. DTO ONLY for what we need to use.
  - create an API contract for security, if it does not match, it will stop you from progressing
  - Centralization: `AuthorizationPolicy.canDeleteTask(task,user)` <- simple idea to keep code clean
  - Traditional Web Session: JSESSIONID=... - Cookies Java Sessions - Tomcat Webapp Runner - Redish Cache (login state)
  - oauth
  - JSON web token - RFC 7519 - problems and fixing/recomendations - lots of issues - 7 ways to avoid JWT pitfals
  - 

## Spring

