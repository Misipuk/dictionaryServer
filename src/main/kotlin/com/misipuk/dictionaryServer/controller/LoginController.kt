package com.misipuk.dictionaryServer.controller


import com.misipuk.dictionaryServer.model.entity.User
import com.misipuk.dictionaryServer.model.entity.Login
import com.misipuk.dictionaryServer.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import util.JsonUtils

/**
 * Created by Maks on 20.07.2017.
 */
@RestController @RequestMapping("/login")
class LoginController @Autowired constructor(private val loginService: LoginService) {

    data class LoginUser(var user: User = User(), var login: Login = Login())

    @PostMapping
    fun login(@RequestBody json: String): User {
        println("LoginController.json: >$json<")
        val loginUser = JsonUtils.readJson(json, LoginUser::class.java)
        return loginService.login(loginUser.login, loginUser.user)
    }
}