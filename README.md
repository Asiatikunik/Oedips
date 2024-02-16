# Oedips - Test technique

## Pour commencer
### Pré-requis
Spring Boot
Maven 
Java 17

### Construit avec
Spring Boot 
Maven
IDEA Intellij
Postman

# Auteurs
Duy DANG

## Request
### GetAll()
GET http://localhost:8081/videos

### AddVideo()
POST http://localhost:8081/videos
{
    "title": "Fast and Furious",
    "labels": ["car", "gun", "action"]
}

### GetVideoById()
GET http://localhost:8081/videos/11

### GetVideoByTitle()
GET http://localhost:8081/videos/title/the

### DeleteVideo()
DELETE http://localhost:8081/videos/2

### GetMovies()
GET http://localhost:8081/media/movies

### GetSeries()
GET http://localhost:8081/media/series

### GetSimilarVideos()
POST http://localhost:8081/videos/similar










