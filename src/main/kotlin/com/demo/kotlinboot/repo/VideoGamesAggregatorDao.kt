package com.demo.kotlinboot.repo

import com.demo.kotlinboot.models.DeveloperGamePriceData
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class VideoGamesAggregatorDao(val mongoTemplate: MongoTemplate) {

    @Value("\${mongodb.collection.name}")
    private lateinit var collectionName:String

    /*
        The query -

         [
          {
            "$match": {
              "$and": [
                {
                  "developers.country": "US"
                },
                {
                  "$expr": {
                    "$lt": [
                      "$price",
                      5000
                    ]
                  }
                }
              ]
            }
          },
          {
            "$group": {
              "_id": "$developers.name",
              "averagePrice": {
                "$avg": "$price"
              }
            }
          },
          {
            "$project": {
              "_id": 0,
              "developer": {
                "$arrayElemAt": [
                  "$_id",
                  0
                ]
              },
              "averagePrice": 1
            }
          }
        ]
     */

    /*
        The result should be -
        [
            /* 1 */
            {
                "averagePrice" : 4149.0,
                "developer" : "Naughty Dog"
            },

            /* 2 */
            {
                "averagePrice" : 3499.0,
                "developer" : "Guerilla Games"
            }
        ]
     */
    fun getAverageGamePricesGroupByDev() : List<DeveloperGamePriceData>? {

        val matchOperation      = Aggregation.match(
                                                Criteria
                                                    .where("developers.country").isEqualTo("US")
                                                    .and("price").lt(5000)
        )

        val groupOperation      = Aggregation.group("developers.name")
                                            .avg("price").`as`("averagePrice")

        val projectOperation    = Aggregation.project()
                                            .and("_id").arrayElementAt(0).`as`("developer")
                                            .andInclude("averagePrice")
                                            .andExclude("_id")

        val aggregation         = Aggregation.newAggregation(
                                            matchOperation,
                                            groupOperation,
                                            projectOperation
        )

        val results = mongoTemplate.aggregate(aggregation, collectionName, DeveloperGamePriceData::class.java)
        return results.toMutableList()
    }

}