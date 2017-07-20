package com.misipuk.dictionaryServer.model.entity

/**
 * Created by Maks on 13.07.2017.
 */
abstract class BaseEntity(open var id: Long = 0) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this.javaClass !== other.javaClass) return false
        return id == (other as BaseEntity).id
    }

    override fun hashCode() = id.hashCode()

    abstract fun isAvailableFor(id: Long): Boolean
}