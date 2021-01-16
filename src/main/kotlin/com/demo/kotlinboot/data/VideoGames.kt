package com.demo.kotlinboot.data

import com.demo.kotlinboot.models.Developer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDateTime

@Document(collection = "videogames")
data class VideoGames(
    @MongoId
    val id: String = ObjectId.get().toString(),
    val name: String,
    val price: Int,
    val description: String = "",
    val currency: String,
    val developers: MutableList<Developer> = mutableListOf<Developer>(),
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
)
