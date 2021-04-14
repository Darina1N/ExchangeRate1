package sk.kosickaakademia.kolesarova.exchange1.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    public static void main(String[] args) {

        //database.createCollection("allResult");
    }

    public void insertAllResult(String key, double value, double txt_result ){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");

        Document document = new Document();
        document.append("Currency", key);
        document.append("HowManyEuros", value);
        document.append("Result", txt_result);
        database.getCollection("allResult").insertOne(document);
    }

}
