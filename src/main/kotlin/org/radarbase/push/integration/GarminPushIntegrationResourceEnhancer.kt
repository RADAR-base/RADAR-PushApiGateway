package org.radarbase.push.integration

import com.fasterxml.jackson.databind.JsonNode
import org.glassfish.jersey.internal.inject.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import org.radarbase.gateway.Config
import org.radarbase.jersey.auth.AuthValidator
import org.radarbase.jersey.config.JerseyResourceEnhancer
import org.radarbase.push.integration.common.auth.DelegatedAuthValidator.Companion.GARMIN_QUALIFIER
import org.radarbase.push.integration.common.user.User
import org.radarbase.push.integration.common.user.UserRepository
import org.radarbase.push.integration.garmin.auth.GarminAuthValidator
import org.radarbase.push.integration.garmin.factory.GarminJsonNodeFactory
import org.radarbase.push.integration.garmin.factory.GarminUserFactory
import org.radarbase.push.integration.garmin.service.GarminHealthApiService
import javax.inject.Singleton

class GarminPushIntegrationResourceEnhancer(private val config: Config) :
    JerseyResourceEnhancer {

    override fun ResourceConfig.enhance() {
        packages(
            "org.radarbase.push.integration.garmin.resource",
            "org.radarbase.push.integration.common.filter"
        )
    }

    override fun AbstractBinder.enhance() {

        bind(config.pushIntegration.garmin.userRepository)
            .to(UserRepository::class.java)
            .named(GARMIN_QUALIFIER)
            .`in`(Singleton::class.java)

        bind(GarminHealthApiService::class.java)
            .to(GarminHealthApiService::class.java)
            .`in`(Singleton::class.java)

        bind(GarminAuthValidator::class.java)
            .to(AuthValidator::class.java)
            .named(GARMIN_QUALIFIER)
            .`in`(Singleton::class.java)

        bindFactory(GarminJsonNodeFactory::class.java)
            .to(JsonNode::class.java)
            .named(GARMIN_QUALIFIER)

        bindFactory(GarminUserFactory::class.java)
            .to(User::class.java)
            .named(GARMIN_QUALIFIER)
    }
}