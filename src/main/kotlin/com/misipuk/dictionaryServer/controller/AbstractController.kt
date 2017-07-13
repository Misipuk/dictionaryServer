package com.misipuk.dictionaryServer.controller

import com.misipuk.dictionaryServer.model.entity.BaseEntity
import com.misipuk.dictionaryServer.service.BaseService

abstract class AbstractController<T : BaseEntity>(
        open val service: BaseService<T, Long>) {}
