package cst8218.sn041068644.bouncer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.PasswordHash;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
/*
@FormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage="/login/login.xhtml",
        errorPage="/login/error.xhtml"
    )
)
*/
@BasicAuthenticationMechanismDefinition
@DatabaseIdentityStoreDefinition(
dataSourceLookup = "${'java:app/HaydenDB'}",
callerQuery = "#{'select password from appuser where userid = ?'}",
groupsQuery = "select `group` from appuser where userid = ?",
hashAlgorithm = PasswordHash.class,
priority = 10
)
@Named
@ApplicationScoped
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    
}
