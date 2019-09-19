package com.blurrays.myjobapp.Classes;

public class Company {

    public Company(String ownerId, String name, String email, String phone, String website, String city, String streetname, int housenumber, String subpremise) {
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.city = city;
        this.streetname = streetname;
        this.housenumber = housenumber;
        this.subpremise = subpremise;
    }

    public Company(){}


    private String ownerId;
    private String name;
    private String email;
    private String phone;
    private String website;
    private String city;
    private String streetname;
    private int housenumber;
    private String subpremise;
    private String documentId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }

    public String getSubpremise() {
        return subpremise;
    }

    public void setSubpremise(String subpremise) {
        this.subpremise = subpremise;
    }

    public String getDocumentId(){return documentId;}

    public void setDocumentId(String documentId){this.documentId = documentId;}
}
