package org.radarbase.gateway.kafka

import org.radarbase.jersey.service.HealthService
import javax.ws.rs.core.Context

class KafkaHealthMetric(
        @Context private val kafkaAdminService: KafkaAdminService
) : HealthService.Metric("kafka") {
    override val status: HealthService.Status?
        get() = try {
            kafkaAdminService.containsTopic("health")
            HealthService.Status.UP
        } catch (ex: Throwable) {
            HealthService.Status.DOWN
        }

    override val metrics: Map<String, String> = emptyMap()
}
