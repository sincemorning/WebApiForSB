/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import com.mongodb.BasicDBObject;

/**
 * REST Web Service
 *
 * @author Takumi
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of src.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO Get処理を記載する
        //throw new UnsupportedOperationException();
        return "Hello World!!(Get)";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param id
     * @param name
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(@PathParam("id") int id, @PathParam("tlstring") String tlstring, @PathParam("rank") int rank) {
        //TODO Mongoへのput処理を記載する
        AccessMongo mongo = new AccessMongo();
        BasicDBObject bdbobj = new BasicDBObject();
        // いいねなのかツイートなのかによってコールするメソッドをハンドルする
        if(!tlstring.isEmpty()) {
            // tlstring(ツイート文字列が空じゃない場合はツイートと判断する)
            bdbobj.append("tlstring", tlstring);
            bdbobj.append("rank", rank);
            mongo.insert(bdbobj);
        }
        else {
            // tlstringが空の場合はいいね！扱いにする
            bdbobj.append("id", id);
        }
        
    } // putText
}
