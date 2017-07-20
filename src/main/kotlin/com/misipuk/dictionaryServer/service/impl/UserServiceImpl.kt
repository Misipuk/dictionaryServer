package com.misipuk.dictionaryServer.service.impl


import com.misipuk.dictionaryServer.model.entity.User
import com.misipuk.dictionaryServer.model.entity.WordPair
import com.misipuk.dictionaryServer.repository.UserRepository
import com.misipuk.dictionaryServer.service.UserService
import com.misipuk.dictionaryServer.service.WordPairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by Maks on 17.07.2017.
 */
@Service
class UserServiceImpl @Autowired constructor(repo: UserRepository,
                                             private val wordService: WordPairService)
    : AbstractLongService<User>(repo), UserService {
    //update words
    override fun update(user: User, words: HashSet<WordPair>): User {
        val idSet: MutableSet<Long> = words.onEach { wordService.get(it.id) ?: wordService.add(it) }.map { it.id }.toHashSet()
        val userDB = get(user.id)?.let {
            it.words = idSet
        }
        return user
    }

    override fun words(id: Long): HashSet<WordPair> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun friends(id: Long): HashSet<User> = HashSet<User>().apply { get(id)?.friends?.map { get(it)?.let { add(it) } } }




    override fun edit(item: User): User = super.edit(item.apply {
        get(item.id)?.let {
            words = it.words
        }
    })


    override fun add(item: User): User = edit(item)

    override fun add(items: Collection<User>) = edit(items)
}
