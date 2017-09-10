package caf.admin.partners.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;
import java.util.Set;

@Entity("employee")
public class Partner {

	@Id
	private String id;

	private String name;

	public Partner() {
	}

	public String getAccount() {

		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Set<Adress> getAdresses() {
		return adresses;
	}

	public void setAdresses(Set<Adress> adresses) {
		this.adresses = adresses;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Partner(String name, String account, Set<Adress> adresses) {
		this.name = name;
		this.account = account;
		this.adresses = adresses;

	}

	private String account;

	@Embedded
	private Set<Adress> adresses = new HashSet<>();


}
