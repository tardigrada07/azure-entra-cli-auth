package hu.cn.azure.login.auth

import hu.cn.azure.login.auth.service.AzureAuthService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import javax.security.sasl.AuthenticationException

@SpringBootApplication
class AzureEntraIDAuthApplication

@Component
class AzureEntraIDAuthCommandLineRunner(
    private val authService: AzureAuthService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("Redirecting to Microsoft login in your browser...")
        println("Please complete the sign-in and consent if prompted.")
        println("====================================================")

        try {
            val userInfo = authService.authenticateAndGetUserInfo()

            println("\nAuthentication successful!\n")
            println("User Information:")
            println("================")
            println("Display Name: ${userInfo.displayName}")
            println("Email: ${userInfo.mail ?: userInfo.userPrincipalName}")
            println("Phone Number: ${userInfo.mobilePhone ?: userInfo.businessPhones?.firstOrNull() ?: "Not provided"}")
            println("Job Title: ${userInfo.jobTitle ?: "Not provided"}")
            println("Department: ${userInfo.department ?: "Not provided"}")
            println("Office Location: ${userInfo.officeLocation ?: "Not provided"}")

        } catch (e: AuthenticationException) {
            println("\nAuthentication failed!")
            println("Error: ${e.message}")
        }
    }
}

fun main() {
    runApplication<AzureEntraIDAuthApplication>()
}
