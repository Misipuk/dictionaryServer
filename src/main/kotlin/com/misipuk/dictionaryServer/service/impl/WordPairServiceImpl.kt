package com.misipuk.dictionaryServer.service.impl

import com.misipuk.dictionaryServer.model.entity.WordPair
import com.misipuk.dictionaryServer.repository.WordPairRepository
import com.misipuk.dictionaryServer.service.WordPairService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Maks on 17.07.2017.
 */
class WordPairServiceImpl @Autowired constructor(repo: WordPairRepository)
    : AbstractLongService<WordPair>(repo), WordPairService {

    override fun add(item: WordPair): WordPair = edit(item)

    override fun add(items: Collection<WordPair>) = edit(items)
}