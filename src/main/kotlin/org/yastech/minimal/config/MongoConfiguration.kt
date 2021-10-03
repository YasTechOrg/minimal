package org.yastech.minimal.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter


@Configuration
class MongoMapKeyDotReplacementConfiguration
{
    @Autowired
    fun setMapKeyDotReplacement(mongoConverter: MappingMongoConverter)
    {
        mongoConverter.setMapKeyDotReplacement("#")
    }
}