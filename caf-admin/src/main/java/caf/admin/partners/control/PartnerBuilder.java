package caf.admin.partners.control;


import caf.admin.partners.entity.Address;
import caf.admin.partners.entity.Partner;

import java.util.Set;

public class PartnerBuilder {

    private String name;
    private String account;
    private Set<Address> adresses;

    private PartnerBuilder(){}

    public static PartnerBuilder newBuilder(){
        return new PartnerBuilder();
    }

    public PartnerBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public PartnerBuilder withAccount(String account) {
        this.account = account;
        return this;
    }

    public PartnerBuilder withAdresses(Set<Address> adresss) {
        this.adresses = adresss;
        return this;
    }


    public Partner build(){
        return new Partner(name, account, adresses);
    }

}


