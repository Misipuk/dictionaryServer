package com.misipuk.dictionaryServer.controller

import com.misipuk.dictionaryServer.model.entity.BaseEntity
import com.misipuk.dictionaryServer.model.entity.Login
import com.misipuk.dictionaryServer.service.BaseService
import com.misipuk.dictionaryServer.service.LoginService
import org.springframework.web.bind.annotation.*
import java.util.HashSet

/**
 * Created by Maks on 20.07.2017.
 */
abstract class AbstractController<T : BaseEntity>(
        open val service: BaseService<T, Long>,
        open val loginService: LoginService) : BaseController<T> {
    @GetMapping
    override fun get(@RequestParam("ids") ids: HashSet<Long>, @RequestParam("device") device: String): List<T>
            = login(device).let { login -> service.get(ids).filter { it.isAvailableFor(login.id) } }
    //обращаемся к BaseService
    @PostMapping
    override fun post(@RequestBody item: T, @RequestParam("device") device: String): T
            = login(device).let { service.edit(item) }

    @PutMapping
    override fun put(@RequestBody item: T, @RequestParam("device") device: String): T
            = login(device).let { service.add(item) }

    @DeleteMapping
    override fun delete(@RequestBody item: T, @RequestParam("device") device: String): Unit
            = login(device).let { service.delete(item) }


    internal fun login(device: String): Login
            = loginService.get(device) ?: throw SecurityException("unknown device")
}
