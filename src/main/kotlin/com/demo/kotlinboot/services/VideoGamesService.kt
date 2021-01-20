package com.demo.kotlinboot.services

import com.demo.kotlinboot.data.VideoGames
import com.demo.kotlinboot.models.DeveloperGamePriceData
import com.demo.kotlinboot.repo.VideoGamesAggregatorDao
import com.demo.kotlinboot.repo.VideoGamesRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils

@Service
class VideoGamesService(val videoGamesRepository: VideoGamesRepository, val videoGamesAggregatorDao: VideoGamesAggregatorDao) {

    fun getAllVideoGames (): List<VideoGames>                               = videoGamesRepository.findAll()
    fun getAllSortByPriceAsc () : List<VideoGames>                          = videoGamesRepository.findAll(Sort.by("price").ascending())
    fun getAllSortByPriceDesc () : List<VideoGames>                         = videoGamesRepository.findAll(Sort.by("price").descending())
    fun getVideoGameAveragePricesByDev () : List<DeveloperGamePriceData>?   = videoGamesAggregatorDao.getAverageGamePricesGroupByDev()
    fun saveOneVideoGame (videoGames: VideoGames)                           = videoGamesRepository.save(videoGames)
    fun deleteAllGames()                                                    = videoGamesRepository.deleteAll()
    fun hydrate(): MutableList<VideoGames>                                  = videoGamesRepository.saveAll(
                                                                                    jacksonObjectMapper().readValue<MutableList<VideoGames>>(
                                                                                        String(
                                                                                                FileCopyUtils.copyToByteArray(ClassPathResource("testdata.json").inputStream ),
                                                                                                Charsets.UTF_8)
                                                                                    )
                                                                                )

}