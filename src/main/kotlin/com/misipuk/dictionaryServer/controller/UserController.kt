package com.misipuk.dictionaryServer.controller


import com.misipuk.dictionaryServer.model.entity.User
import com.misipuk.dictionaryServer.model.entity.WordPair
import com.misipuk.dictionaryServer.service.LoginService
import com.misipuk.dictionaryServer.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Maks on 20.07.2017.
 */
@RestController @RequestMapping("/user")
class UserController @Autowired constructor(
        override val service: UserService,
        loginService: LoginService) : AbstractController<User>(service, loginService) {

    @GetMapping @RequestMapping("/friends")
    fun friends(@RequestParam(name = "device") device: String): Set<User>
            = service.friends(login(device).id)

    @PostMapping @RequestMapping("/update")
    fun update(@RequestBody words: HashSet<WordPair>, @RequestParam("device") device: String): User =
            service.update(service.get(login(device).id)!!, words)

}