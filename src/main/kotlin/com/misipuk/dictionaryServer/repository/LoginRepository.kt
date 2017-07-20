package com.misipuk.dictionaryServer.repository

import com.misipuk.dictionaryServer.model.entity.Login

/**
 * Created by Maks on 17.07.2017.
 */
interface LoginRepository: BaseRepository<Login, String> {
    fun findByDevice(device: String): Login?
}