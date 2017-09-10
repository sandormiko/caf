package caf.admin.partners.control;


import caf.admin.partners.entity.Adress;
import caf.admin.partners.entity.Partner;

public class AdressBuilder {

    private String street;
    private String city;
    private String postCode;
    private String country;

    public static AdressBuilder newBuilder(){
        return new AdressBuilder();
    }

    private AdressBuilder(){}

    public AdressBuilder withStreet(String street){
        this.street = street;
        return this;
    }

    public AdressBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public AdressBuilder withPostCode(String postCode){
        this.postCode = postCode;
        return this;
    }

    public AdressBuilder withCountry(String country){
        this.country = country;
        return this;
    }

    public Adress build(){
        return new Adress(street,city, postCode, country);
    }
}
