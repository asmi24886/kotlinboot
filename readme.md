# Demo Spring boot MongoDb project with Kotlin


Mongo host runs on windows 10 with no authentication and DB - **Products**

Service runs on default port - 8080

API
================================================

URL - http://localhost:8080/api

Methods:-

GET - /test - Test api to check if service is up

GET - /allgames - Gets all available games in DB

GET - /allgames/price/asc | /allgames/price/desc - Gets all games by sorting by price ascending or descending

POST - /savegame - Saves a single video game data

DELETE - /deleteall - Deletes all data from DB

POST - /initdata - Hydrates the db with demo data


**Sample data in resources/testdata.json.**

Sample data for save one game operation
----------------------------------------------
 {
    "name": "Horizon Zero Dawn",
    "price": 3499,
    "description": "Best sci fi game ever",
    "currency": "INR",
    "developers": [
      {
        "name": "Guerilla Games",
        "country": "US",
        "website": "https://www.guerrilla-games.com/",
        "details": "Great development team, generally make games for Playstation"
      }
    ]
  }
  
  Data contains details of the video game and the developer studio
