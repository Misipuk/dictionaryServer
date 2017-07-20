package com.misipuk.dictionaryServer.controller

import com.misipuk.dictionaryServer.model.entity.BaseEntity
import java.util.*

/**
 * Created by Maks on 20.07.2017.
 */
interface BaseController<T : BaseEntity> {

    fun get(ids: HashSet<Long>, device: String): Collection<T>

    fun post(item: T, device: String): T

    fun put(item: T, device: String): T

    fun delete(item: T, device: String): Unit
}