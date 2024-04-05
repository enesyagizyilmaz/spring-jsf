package org.xtremebiker.jsfspring.view;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.xtremebiker.jsfspring.model.User;
import org.xtremebiker.jsfspring.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

@Named
@ViewScoped
@Data
public class UserViewController implements Serializable
{
    @Autowired
    UserService userService;
    private User user;
    private String passwordRepeat;

    @PostConstruct
    public void init()
    {
        user = new User();
        passwordRepeat = "";
    }

    public void signup() throws IOException
    {
        if (!Objects.equals(user.getPassword(), passwordRepeat))
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Passwords don't match.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userService.saveUser(user);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User signed up successfully.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/crud/index.xhtml");

        user = new User();
        passwordRepeat = "";
    }

}

