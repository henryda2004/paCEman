#include <stdio.h>
#include <winsock2.h>
#include <string.h>
#include <process.h>
#include "constants.h"

#define MAX_CLIENTS 10

unsigned __stdcall ClientHandler(void* data) {
    struct ClientInfo* client = (struct ClientInfo*)data;

    while (1) {
        char message[1000];
        int result = recv(client->clientSocket, message, sizeof(message), 0);

        if (result <= 0) {
            printf("Cliente %d desconectado.\n", client->clientID);
            closesocket(client->clientSocket);
            _endthreadex(0);
            return 0;
        }

        message[result] = '\0';
        printf("Cliente %d dice: %s", client->clientID, message);
    }

    return 0;
}

int main() {
    WSADATA wsaData;
    SOCKET serverSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);
    int clientCount = 0;
    struct ClientInfo clients[MAX_CLIENTS];

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

    if (listen(serverSocket, MAX_CLIENTS) == SOCKET_ERROR) {
        printf("Error al poner el socket en modo escucha\n");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    printf("Esperando conexiones entrantes...\n");


    while (1) {
        if (clientCount < MAX_CLIENTS) {
            clients[clientCount].clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &clientAddrLen);
            if (clients[clientCount].clientSocket == INVALID_SOCKET) {
                printf("Error al aceptar la conexión entrante\n");
            } else {
                clients[clientCount].clientID = clientCount;
                printf("Cliente conectado con ID %d\n", clientCount);

                unsigned threadID;
                _beginthreadex(NULL, 0, &ClientHandler, (void*)&clients[clientCount], 0, &threadID);

                clientCount++;

                // Interacción con el cliente
                while (1) {
                    int clientChoice;
                    printf("Selecciona el cliente con el que deseas hablar (0-%d) o -1 para salir: ", clientCount - 1);
                    scanf("%d", &clientChoice);

                    if (clientChoice == -1) {
                        break; // Salir del bucle de interacción
                    } else if (clientChoice >= 0 && clientChoice < clientCount) {
                        char message[1000];
                        printf("Escribe un mensaje para el cliente %d: ", clientChoice);
                        scanf(" ");
                        gets(message);

                        strcat(message, "\n");
                        send(clients[clientChoice].clientSocket, message, strlen(message), 0);
                    } else {
                        printf("Selección no válida.\n");
                    }
                }
            }
        } else {
            printf("Límite de clientes alcanzado. No se aceptarán más conexiones.\n");
        }
    }

    for (int i = 0; i < clientCount; i++) {
        closesocket(clients[i].clientSocket);
    }

    closesocket(serverSocket);
    WSACleanup();

    return 0;
}
