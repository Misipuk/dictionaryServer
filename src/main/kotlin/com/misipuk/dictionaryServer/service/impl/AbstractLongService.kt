package com.misipuk.dictionaryServer.service.impl


import com.misipuk.dictionaryServer.model.entity.BaseEntity
import com.misipuk.dictionaryServer.repository.BaseRepository

/**
 * Created by Maks on 17.07.2017.
 */
abstract class AbstractLongService<T : BaseEntity>(override val repo: BaseRepository<T, Long>)
    : AbstractService<T, Long>(repo) {

    override fun add(item: T): T = repo.save(item.apply { id = (repo.findMaxId() ?: -1) + 1 })

    override fun get(id: Long): T? = repo.findById(id)
}