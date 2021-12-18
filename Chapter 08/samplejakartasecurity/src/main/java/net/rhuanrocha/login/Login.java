package net.rhuanrocha.login;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Named
@RequestScoped
public class Login {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    public void login(){
        if(username == null || password == null){
            return;
        }

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse  response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        AuthenticationStatus authenticationStatus = securityContext
                .authenticate(request, response,
                        AuthenticationParameters.withParams()
                                .credential(new UsernamePasswordCredential(username, password))
                );

    }
}
