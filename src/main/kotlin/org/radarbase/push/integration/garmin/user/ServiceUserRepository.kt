package org.radarbase.push.integration.garmin.user

import org.radarbase.push.integration.common.user.User
import org.radarbase.push.integration.common.user.UserRepository
import java.io.IOException
import java.time.Instant
import java.util.stream.Stream
import javax.ws.rs.NotAuthorizedException

class ServiceUserRepository : UserRepository {

    // TODO: Index by externalId for quick lookup
    private val cachedMap: Map<String, User> = mapOf(
        "1" to GarminUser(
            id = "1",
            projectId = "test",
            userId = "test",
            sourceId = "test",
            externalUserId = "a0015b7d-8904-40d7-8852-815cb7ad7a0b",
            isAuthorized = true,
            startDate = Instant.ofEpochSecond(1590324060),
            endDate = Instant.ofEpochSecond(1590418800),
        )
    )

    @Throws(IOException::class)
    override fun get(key: String): User? = cachedMap[key]

    @Throws(IOException::class)
    override fun stream(): Stream<out User> = cachedMap.values.stream()

    @Throws(IOException::class, NotAuthorizedException::class)
    override fun getAccessToken(user: User): String {
        return ""
    }

    /**
     * Garmin uses Oauth 1.0 and hence has a user access
     * token secret instead of a refresh token. This should
     * not be required in most cases anyways since only the access token
     * is required.
     */
    override fun getRefreshToken(user: User): String {
        return getUserAccessTokenSecret(user)
    }

    @Throws(IOException::class, NotAuthorizedException::class)
    fun getUserAccessTokenSecret(user: User): String {
        return ""
    }

    @Throws(IOException::class)
    override fun reportDeregistration(user: User) {
    }

    override fun findByExternalId(externalId: String): User {
        return super.findByExternalId(externalId)
    }
}