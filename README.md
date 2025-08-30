# Azure Entra ID CLI Authentication example (Kotlin, Spring Boot 3)
A command-line Kotlin (Java 21) application that authenticates a user with Microsoft Azure Entra ID. On startup, 
it opens your system browser to the Microsoft login page. After successful sign-in, it retrieves and prints user 
information (display name, email, phone, ...) using Microsoft Graph.

## Status
[![Kotlin CI with Gradle](https://github.com/tardigrada07/azure-entra-cli-auth/actions/workflows/gradle.yml/badge.svg)](https://github.com/tardigrada07/azure-entra-cli-auth/actions/workflows/gradle.yml)
[![Scan with Detekt](https://github.com/tardigrada07/azure-entra-cli-auth/actions/workflows/detekt.yml/badge.svg)](https://github.com/tardigrada07/azure-entra-cli-auth/actions/workflows/detekt.yml)

## Prerequisites
- Java 21 installed
- Gradle wrapper (included) or Gradle installed
- An Azure Entra ID App Registration with:
    - Client ID
    - Tenant ID
    - Redirect URI registered (e.g., [http://127.0.0.1:8400](http://127.0.0.1:8400))
- Permissions consented for Microsoft Graph:
    - User.Read (delegated)

## Configuration
The app reads Azure credentials from environment variables:
- AZURE_CLIENT_ID → mapped to azure.entra.client-id
- AZURE_TENANT_ID → mapped to azure.entra.tenant-id
- AZURE_REDIRECT_URL → mapped to azure.entra.redirect-url

### macOS/Linux (bash/zsh):
```shell
export AZURE_CLIENT_ID="<your-client-id>"
export AZURE_TENANT_ID="<your-tenant-id-or-common>"
export AZURE_REDIRECT_URL="http://127.0.0.1:8400"
```

### Windows (Command Prompt):
```windows-command-prompt
set AZURE_CLIENT_ID=<your-client-id>
set AZURE_TENANT_ID=<your-tenant-id-or-common>
set AZURE_REDIRECT_URL=http://127.0.0.1:8400
```

## Build and Run
### Build
```shell
./gradlew clean build
```

### Run
```shell
./gradlew bootRun
```