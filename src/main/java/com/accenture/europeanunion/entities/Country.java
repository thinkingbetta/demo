package com.accenture.europeanunion.entities;

public class Country {
    private String countryName;
    private String capitalCity;
    private String language;
    private String popularGreetings;
    private int population;
    //TODO di che tipo deve essere la data di entrata? int o meglio usare la classe della data?
    private int entranceYear;

    public Country(String countryName, String capitalCity, String language, String popularGreetings, int population, int entranceYear){
        this.countryName = countryName;
        this.capitalCity = capitalCity;
        this.language = language;
        this.popularGreetings = popularGreetings;
        this.population = population;
        this.entranceYear = entranceYear;
    }

    public String getCountryName(){
        return countryName;
    }
    public String getCapitalCity(){
        return capitalCity;
    }
    public String getLanguage(){
        return language;
    }
    public String getPopularGreetings(){
        return popularGreetings;
    }
    public int getPopulation(){
        return population;
    }
    public int getEntranceYear(){
        return entranceYear;
    }

//TODO come mettere questa toString nella country service?
    public String toString(){
        return "Country name: " + countryName + "\nCapital city: " + capitalCity + "\nLanguage:" + language + "\nPopular Greeting: " + popularGreetings + "\nPopulation: " + population + "\nEntrance year: " + entranceYear + "\n" ;

    }
//    public String toString {
//        getPopularGreetings() + ", welcome to " + getCountryName() + "! " +
//                "\nThe capital is " + getCapitalCity() + ". \nThe official language is " + getLanguage() +
//                " and the population is about " + getPopulation() + " people.\n" + c.getCountryName() +
//                " entered the European Union in " + c.getEntranceYear() + "\n" );
//    }

}
