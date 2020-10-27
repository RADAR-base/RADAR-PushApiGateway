package org.radarbase.push.integrations.garmin.user

import org.radarbase.push.integrations.common.user.User
import org.radarcns.kafka.ObservationKey
import java.time.Instant

data class GarminUser(
    override val id: String,
    override val projectId: String,
    override val userId: String,
    override val sourceId: String,
    override val externalUserId: String,
    override val isAuthorized: Boolean,
    override val startDate: Instant,
    override val endDate: Instant,
    override val version: String? = null,
    override val observationKey: ObservationKey = ObservationKey(projectId, userId, sourceId)
) : User
