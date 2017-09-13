package caf.admin.fongo;


import com.github.fakemongo.Fongo;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class InMemoryDataSource {

    private final Datastore dataStore;
    private final Fongo fongo;
    private final String dbName;

    public InMemoryDataSource(String dbName) {
        this.dbName = dbName;
        fongo = new Fongo("inMemoryServer");
        Morphia morphia = new Morphia();
        dataStore = morphia.createDatastore(fongo.getMongo(), dbName);
    }

    public Datastore getDataStore() {
        return dataStore;
    }

    public void dropDataBase(){
        this.fongo.dropDatabase(dbName);
    }
}
