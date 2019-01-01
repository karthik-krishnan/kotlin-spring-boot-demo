package org.jetbrains.kotlin.demo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties
class ApplicationProperties {
    @Value("\${datafile.path:}")
    lateinit var datafilePath: String
}