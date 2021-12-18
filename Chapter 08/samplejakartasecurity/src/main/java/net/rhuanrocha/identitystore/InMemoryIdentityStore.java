package net.rhuanrocha.identitystore;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @PostConstruct
    private void init(){
        User user = User.of("admin", "admin123", "admin");
        credentials.put("admin",user);
    }

    // init from a file or harcoded
    private Map<String, User> credentials = new HashMap<>();

    @Override
    public int priority() {
        return 10;
    }

    public CredentialValidationResult validate(
            UsernamePasswordCredential credential) {

        User user = credentials.get(credential.getCaller());
        if (credential.compareTo(user.getUsername(), user.getPassword())) {
            return new CredentialValidationResult(user.getUsername());
        }
        return CredentialValidationResult.INVALID_RESULT;
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        User user = credentials.get(
                validationResult.getCallerPrincipal().getName());
        return new HashSet<>(user.getRoles());
    }
}
