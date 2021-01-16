package com.demo.kotlinboot.controllers

import com.demo.kotlinboot.data.VideoGames
import com.demo.kotlinboot.services.VideoGamesService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class RequestController(val videoGamesService: VideoGamesService) {

    @Value("\${server.port}")
    lateinit var port:String

    @GetMapping("/test")
    fun test() = "Demo kotlin-jdk-spring-boot test service is up and running on $port"

    @GetMapping("/allgames")
    fun getAllVideoGames()                                      = videoGamesService.getAllVideoGames();

    @GetMapping("/allgames/price/{sortType}")
    fun getAllSortByPriceAsc(@PathVariable sortType : String)   = if(sortType=="asc")videoGamesService.getAllSortByPriceAsc() else videoGamesService.getAllSortByPriceDesc()

    @PostMapping("/savegame")
    fun saveOneVideoGame(@RequestBody videoGames: VideoGames)   = videoGamesService.saveOneVideoGame(videoGames)

    @DeleteMapping("/deleteall")
    fun deleteAllGames()                                        = videoGamesService.deleteAllGames().let { ResponseEntity.ok("Data was successfully deleted") }

    @PostMapping("/initdata")
    fun initData()                                              = videoGamesService.hydrate()
}