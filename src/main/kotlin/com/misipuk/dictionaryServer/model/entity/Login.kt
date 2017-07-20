package com.misipuk.dictionaryServer.model.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Transient

/**
 * Created by Maks on 13.07.2017.
 */
@Entity
class Login(@Id @Column(name = "id") val device: String = "",
            val UserId:Long = -1) : BaseEntity(UserId), Serializable {
    override fun isAvailableFor(id: Long) = this.UserId == id
}