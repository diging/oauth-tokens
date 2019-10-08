# oauth-tokens
### Plugin providing some base code for Spring OAuth providers

oauth-tokens uses Spring Security OAuth2 to provide oauth provider functionality and Spring Data JPA to store data. More precisely, it provides a manager class and controllers to manage OAuth2 clients.

## Installation

Include the following plugin your pom.xml.

## Usage

To configure oauth-tokens, add a configuration class that implements ```OAuthTokensConfiguration``` and set the properties you want to change from the defaults on ```OAuthTokens```.

```
@Configuration
public class OAuthTokensConfig implements OAuthTokensConfiguration {
    @Override
    public void configure(OAuthTokens arg0) {
        
    }
}
```

Add a view in ```views/admin/apps/add.html``` with a form to add new apps, e.g. using Thymeleaf:
```
<form action="#" th:action="@{/admin/apps/add}" method="POST" th:object="${appForm}">
  <label>App Name: </label>
  <input th:field="*{name}" class="form-control input-sm"></input>
  <p class="text-danger"><th:errors path="name"/></p>
	
  <label>Description: </label>
  <input th:field="*{description}" class="form-control input-sm"></input>
  <p class="text-danger"><th:errors path="description"/></p>
	
  <button type="submit" class="btn btn-primary">Add</button>
</form>
```

After a new oauth client has been created, oauth-tokens will add the new client id and secret as ```clientId``` and ```secret``` into the model.

See [the wiki](https://github.com/diging/oauth-tokens/wiki/Setup) for how to set up the project with Spring OAuth2.
