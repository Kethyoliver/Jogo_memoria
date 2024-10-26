package pro.mongocrud;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class conectaMongo {
    public void getValues() {
    System.out.println("Método getValues()");
    MongoClient mongo = new MongoClient("localhost", 27017);
    MongoDatabase db = mongo.getDatabase("usuario");
    MongoCollection<Document> docs = db.getCollection("usuario");
    for (Document doc : docs.find()) {
        System.out.println("item: " + doc);
    }
    System.out.println("getValues() - ok - finalizou");
   }     
    
   public void insertValues(String nome, String prof, int id, boolean trab) {
        System.out.println("Método insertValues()");
        //conexão mongo
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("usuario");
        MongoCollection<Document> docs = db.getCollection("usuario");
        Entrada user = createUser(nome);
//cria um user obj da classe conectar, 
//chamando o método createUser() - logo abaixo
        Document doc = createDocument(user);
//cria um doc que referencia o conteúdo de user do createDocument()
//obs, o Banco só entende objetos do tipo Document, 
        docs.insertOne(doc);//insere no mongo o conteúdo de doc
        getValues();
        System.out.println("insertValues ok");
    }

   public Entrada createUser(String nome) {
        //esse método deve ser uma entrada 
        //externa (interface, scanner ou JOptionPanel
        Entrada u = new Entrada();
        u.setNome(nome);
        return u;
    }

   public Document createDocument(Entrada user) {
        Document docBuilder = new Document();
        docBuilder.append("nome", user.getNome());

        return docBuilder;
    }

   

    public void updateValues() {

        System.out.println("updateValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("usuario");
        MongoCollection<Document> docs = db.getCollection("usuario");

        docs.updateOne(Filters.eq("nome", "Crishna"), Updates.set("nome", "andre"));
        System.out.println("Documento teve sucesso no update...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }

    public  void deleteValues() {
        System.out.println("deleteValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("usuario");
        MongoCollection<Document> docs = db.getCollection("usuario");

        docs.deleteOne(Filters.eq("nome", "Maria"));
        System.out.println("Documento teve sucesso no delete...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }

}