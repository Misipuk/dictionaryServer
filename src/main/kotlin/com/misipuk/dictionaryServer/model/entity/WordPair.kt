package com.misipuk.dictionaryServer.model.entity

import javax.persistence.*
import java.io.Serializable

/**
 * Created by Maks on 13.07.2017.
 */
@Entity
@Table(name = "words")
class WordPair(@Id override var id: Long = -1,
               var word:String="",
               var trsln:String="",
               val owner:Long = -1): BaseEntity(id), Serializable {

    override fun isAvailableFor(id: Long) = this.id == id
}