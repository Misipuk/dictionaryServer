package com.misipuk.dictionaryServer.service

import com.misipuk.dictionaryServer.model.entity.User
import com.misipuk.dictionaryServer.model.entity.WordPair
import java.util.HashSet

/**
 * Created by Maks on 17.07.2017.
 */

interface UserService : BaseService<User, Long> {

    //Get friends list by User's id
    fun friends(id: Long): HashSet<User>

    //Get words list by User's id
    fun words(id: Long): HashSet<WordPair>

    fun update(user: User, words: HashSet<WordPair>): User
}