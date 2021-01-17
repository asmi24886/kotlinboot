package com.demo.kotlinboot.repo

import com.demo.kotlinboot.data.VideoGames
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoGamesRepository : MongoRepository<VideoGames, String> {}