package com.misipuk.dictionaryServer.repository

import com.misipuk.dictionaryServer.controller.AbstractController
import com.misipuk.dictionaryServer.model.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

/**
 * Created by Maks on 17.07.2017.
 */

@NoRepositoryBean
interface BaseRepository<T : BaseEntity, V : Serializable> : JpaRepository<T, V> {

    fun findById(id: V): T?

    @Query("select max(t.id) from #{#entityName} as t")
    fun findMaxId(): V?
}