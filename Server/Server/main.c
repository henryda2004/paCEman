#include <stdio.h>
#include <winsock2.h>

int main() {
    WSADATA wsaData;
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);

    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        printf("Error al inicializar Winsock\n");
        return 1;
    }

    serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket == INVALID_SOCKET) {
        printf("Error al crear el socket del servidor\n");
        return 1;
    }

    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(12345);

    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        printf("Error al vincular el socket del servidor\n");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    if (listen(serverSocket, 5) == SOCKET_ERROR) {
        printf("Error al poner el socket en modo escucha\n");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    printf("Esperando conexiones entrantes...\n");

    clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &clientAddrLen);
    if (clientSocket == INVALID_SOCKET) {
        printf("Error al aceptar la conexión entrante\n");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    printf("Cliente conectado\n");

    // Envía mensajes al cliente de forma autónoma
// Envía mensajes al cliente de forma autónoma
    while (1) {
        char message[1000];
        printf("Escribe un mensaje para el cliente (o 'exit' para salir): ");
        gets(message);

        if (strcmp(message, "exit") == 0) {
            break; // Salir del bucle si se ingresa 'exit'
        }

        strcat(message, "\n"); // Agregar un carácter de nueva línea al final del mensaje
        send(clientSocket, message, strlen(message), 0);
    }

    // Cerrar el socket del cliente
    closesocket(clientSocket);

    // Cerrar el socket del servidor y limpiar Winsock
    closesocket(serverSocket);
    WSACleanup();

    return 0;
}
