package com.example.pranjul.materialtest;

/**
 * Created by Pranjul on 28-01-2017.
 */
public class Coordinator {
    private String name;
    private String phoneNo;
    private String email;
    public Coordinator(){}

    public Coordinator(String name,String phoneNo,String email){
        this.name=name;
        this.phoneNo=phoneNo;
        this.email=email;
    }

    public String toString(){
        StringBuilder strBuilder=new StringBuilder();
        strBuilder.append(name+"\t"+phoneNo+"\n"+email);
        return strBuilder.toString();
    }
}
