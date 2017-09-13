package caf.admin.users.control;

import caf.admin.partners.entity.Partner;
import caf.admin.users.entity.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Inject;

public class UserDAO extends BasicDAO<User, String> {

    @Inject
    public UserDAO(Datastore ds) {
        super(User.class, ds);

    }

}
