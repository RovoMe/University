package wfm.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author meyerd
 */
@XmlRootElement 
@Named
@SessionScoped
public class CurrentUser implements Serializable 
{
  private static final long serialVersionUID = 1L;
  private String username = "kermit";

  public void setUsername(String username) 
  {
    this.username = username;
  }

  public String getUsername() 
  {
    return username;
  }
}
