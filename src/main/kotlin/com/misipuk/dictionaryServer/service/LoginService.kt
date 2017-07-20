package com.misipuk.dictionaryServer.service

import com.misipuk.dictionaryServer.model.entity.Login
import com.misipuk.dictionaryServer.model.entity.User

/**
 * Created by Maks on 17.07.2017.
 */
interface LoginService : BaseService<Login, String> {

    fun login(login: Login, user: User): User
}