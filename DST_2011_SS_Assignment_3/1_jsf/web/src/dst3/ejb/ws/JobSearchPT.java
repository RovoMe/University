
package dst3.ejb.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "JobSearchPT", targetNamespace = "ws.ejb.dst3")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface JobSearchPT {


    /**
     * 
     * @param searchString
     * @return
     *     returns java.util.List<dst3.ejb.ws.JobDTO>
     */
    @WebMethod(action = "urn:Search")
    @WebResult(name = "searchResult", targetNamespace = "")
    @RequestWrapper(localName = "search", targetNamespace = "ws.ejb.dst3", className = "dst3.ejb.ws.Search")
    @ResponseWrapper(localName = "searchResponse", targetNamespace = "ws.ejb.dst3", className = "dst3.ejb.ws.SearchResponse")
    public List<JobDTO> search(
        @WebParam(name = "searchString", targetNamespace = "")
        String searchString);

}
