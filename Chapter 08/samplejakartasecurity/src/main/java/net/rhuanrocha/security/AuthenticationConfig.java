package net.rhuanrocha.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.html",
                errorPage = "/error.html",
                useForwardToLogin = true
        )
)
/*@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/error.html",
                useForwardToLogin = true
))*/
/*@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/ExampleDS",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select groupname from groups where username = ?",
        priority=30)*/
@ApplicationScoped
public class AuthenticationConfig {
}
