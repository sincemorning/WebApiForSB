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
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mongoへの操作を実装するクラスです
 *
 * @author Takumi
 */
public class AccessMongo {

    private String db;
    private int port;

    /**
     * デフォルトのコンストラクタ
     * 接続先はlocalhostのポート27017
     */
    public AccessMongo() {
        this.db = "localhost";
        this.port = 27017;
    } // AccessMongo()

    /**
     * 接続先のipとportを指定した形のコンストラクタ
     *
     * @param db 接続先
     * @param port ポート
     */
    public AccessMongo(String db, int port) {
        this.db = db;
        this.port = port;
    } // AccessMongo(String db, int port)

    /**
     * Mongoへのドキュメントの挿入を行います
     *
     * @param doc
     */
    public void insert(BasicDBObject doc) {
        try {
            Mongo mongo = new Mongo(this.db, this.port);
            DB mongoDb = mongo.getDB("tllists");
            // コレクションの取得
            DBCollection collection = mongoDb.getCollection("tllists");
            // ドキュメントの挿入
            collection.insert(doc);
        } // insert()
        catch (UnknownHostException ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }

    } // insert

    /**
     * Mongoへの更新を行います（いいね！用） 引数に渡すBasicDBObjectにはidのみを指定する
     *
     * @param doc
     */
    public void update(BasicDBObject doc) {
        try {
            Mongo mongo = new Mongo(this.db, this.port);

            DB mongoDb = mongo.getDB("tllists");
            // コレクションの取得

            DBCollection collection = mongoDb.getCollection("tllists");
            DBObject found = collection.findOne(doc);
            int sogood = 0;
            sogood = Integer.parseInt(found.get("sogood").toString());
            sogood++;
            found.put("soogood", sogood);
            collection.update(doc, found);
        } // update
        catch (UnknownHostException ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // update(BasicDBObject doc)

    /**
     * Mongoからの取得を行います
     *
     * @return Mongoからの取得データを格納したDBCollection
     */
    public DBCursor select() {
        try {
            Mongo mongo = new Mongo(this.db, this.port);

            DB mongoDb = mongo.getDB("tllists");

            // コレクションの全件取得

            DBCollection collection = mongoDb.getCollection("tllists");
            return collection.find();
        } catch (UnknownHostException ex) {
            Logger.getLogger(AccessMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } // select
}
