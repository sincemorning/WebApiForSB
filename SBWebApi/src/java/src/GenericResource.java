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
import com.mongodb.DBCursor;
import javax.ws.rs.HeaderParam;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

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
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getText() {
        AccessMongo mongo;
        try {
            // プロパティファイルから読み込んでmongoのインスタンスを立ち上げる
            //mongo = new AccessMongo(rp.read("db"), Integer.parseInt(rp.read("port")));
            mongo = new AccessMongo();
        } catch (Exception ex) {
            // プロパティファイルからの読み込みに失敗した場合
            ex.printStackTrace();
            mongo = new AccessMongo();
        }
        String lists = "";
        DBCursor cur = mongo.select();
        if (cur != null) {
            // データが取得できた場合だけ
            lists += cur.next();
        }
        return lists;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param id
     * @param nickname
     * @param tlstring
     * @param rank
     */
    @PUT
    @Consumes("text/plain")
    public void putText(@HeaderParam("id") String id, @HeaderParam("nickname") String nickname, @HeaderParam("tlstring") String tlstring, @HeaderParam("rank") int rank) {
        //ReadProperties rp = new ReadProperties();
        AccessMongo mongo;
        try {
            // プロパティファイルから読み込んでmongoのインスタンスを立ち上げる
            //mongo = new AccessMongo(rp.read("db"), Integer.parseInt(rp.read("port")));
            mongo = new AccessMongo();
        } catch (Exception ex) {
            // プロパティファイルからの読み込みに失敗した場合
            ex.printStackTrace();
            mongo = new AccessMongo();
        }

        BasicDBObject bdbobj = new BasicDBObject();
        // いいねなのかツイートなのかによってコールするメソッドをハンドルする
        if (tlstring != null && !tlstring.isEmpty()) {
            // tlstring(ツイート文字列が空じゃない場合はツイートと判断する)
            bdbobj.put("nickname", nickname);
            bdbobj.put("tlstring", tlstring);
            bdbobj.put("rank", rank);
            // いいねは常に0で登録する
            bdbobj.put("sogood", 0);
            mongo.insert(bdbobj);
        } else {
            // tlstringが空の場合はいいね！扱いにする
            bdbobj.append("_id", new ObjectId(id));
            mongo.update(bdbobj);
        }

    } // putText
}
