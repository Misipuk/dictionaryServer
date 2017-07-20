package com.misipuk.dictionaryServer.model.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * Created by Maks on 13.07.2017.
 */
@Entity
@Table(name = "users")
class User(@Id override var id: Long = -1,
           val name: String = "",
           @ElementCollection var friends: Set<Long> = HashSet(),
           @ElementCollection var words:Set<Long> = HashSet()) : BaseEntity(id), Serializable {
    override fun isAvailableFor(id: Long) = this.id == id
}
