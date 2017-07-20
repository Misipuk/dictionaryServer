package com.misipuk.dictionaryServer.service.impl

import com.misipuk.dictionaryServer.model.entity.Login
import com.misipuk.dictionaryServer.model.entity.User
import com.misipuk.dictionaryServer.repository.LoginRepository
import com.misipuk.dictionaryServer.service.LoginService
import com.misipuk.dictionaryServer.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
 * Created by Maks on 17.07.2017.
 */
@Service @Scope("singleton")
class LoginServiceImpl @Autowired constructor(
        override val repo: LoginRepository,
        private val userService: UserService)
    : AbstractService<Login, String>(repo), LoginService {

    private fun checkDevice(device: String): Boolean = !device.isEmpty()

    override fun login(login: Login, user: User): User{
        if (!checkDevice(login.device)) throw SecurityException(login.device)
        val loginDB = get(login.device)
        val userDB = userService.get(user.id)

        userDB?.let {
            if (loginDB != null && loginDB.UserId != it.id) {
                delete(loginDB)
            }
            user.words = it.words
        } ?: loginDB?.let {
            delete(loginDB)
        }

        edit(login)//?
        userService.edit(user)//?
        return user
    }

    override fun add(item: Login): Login = repo.save(item)

    override fun get(id: String): Login? = repo.findByDevice(id)
}
