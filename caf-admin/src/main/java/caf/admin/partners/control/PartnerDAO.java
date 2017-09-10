package caf.admin.partners.control;

import caf.admin.partners.entity.Partner;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Inject;

public class PartnerDAO extends BasicDAO<Partner, String>{

	@Inject
	public PartnerDAO(Datastore ds) {
		super(Partner.class, ds);
		
	}
	
	public Partner findById(String id){
		ObjectId oid = new ObjectId(id);
	    return getDatastore().find(Partner.class).field("objectId").equal(oid).get();
	}

}
