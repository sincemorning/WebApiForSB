/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Mongoへの操作を実装するクラスです
 * @author Takumi
 */
public class AccessMongo {
    /**
     * Mongoへのドキュメントの挿入を行います
     * @param doc
     */
    public void insert(BasicDBObject doc) {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("tllists");
            // コレクションの取得
            DBCollection collection = db.getCollection("tllists");
            
            // ドキュメントの挿入
            collection.insert(doc);
        } // insert()
        catch (UnknownHostException ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // insert
    
    /**
     * Mongoへの更新を行います（いいね！用）
     * @param doc 
     */
    public void update(BasicDBObject doc) {
        
    } // update
    
    /**
     * Mongoからの取得を行います
     * @return 
     */
    public DBCursor select() {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("tllists");
            
            // コレクションの取得
            DBCollection collection = db.getCollection("tllists");
            return collection.find();
        } 
        catch (UnknownHostException ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } // select
}
