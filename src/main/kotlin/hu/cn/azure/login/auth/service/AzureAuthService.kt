package hu.cn.azure.login.auth.service

import com.azure.identity.InteractiveBrowserCredentialBuilder
import com.microsoft.graph.models.User
import com.microsoft.graph.serviceclient.GraphServiceClient
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class AzureAuthService {

    @Value("\${azure.entra.client-id}")
    private lateinit var clientId: String

    @Value("\${azure.entra.tenant-id}")
    private lateinit var tenantId: String

    @Value("\${azure.entra.redirect-url}")
    private lateinit var redirectUrl: String

    @PostConstruct
    private fun validateConfiguration() {
        require(clientId.isNotBlank()) { "Azure client ID must be configured" }
        require(tenantId.isNotBlank()) { "Azure tenant ID must be configured" }
        require(redirectUrl.isNotBlank()) { "Azure redirect URL must be configured" }
    }

    fun authenticateAndGetUserInfo(): User {
        val credential = InteractiveBrowserCredentialBuilder()
            .clientId(clientId)
            .tenantId(tenantId)
            .redirectUrl(redirectUrl)
            .build()

        val graphClient = GraphServiceClient(credential, USER_READ_SCOPE)

        // Fetch current user's data
        return graphClient
            .me()
            .get() ?: throw RuntimeException("Failed to retrieve user information")
    }

    companion object {
        private const val USER_READ_SCOPE = "User.Read"
    }
}
