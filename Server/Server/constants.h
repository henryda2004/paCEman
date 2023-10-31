//
// Created by henry on 22/10/2023.
//

#ifndef SERVER_CONSTANTS_H
#define SERVER_CONSTANTS_H

#include <winsock.h>

#define MAX_CLIENTS 8
#define MAX_MESSAGE_LENGTH 1000

struct ClientInfo {
    SOCKET clientSocket;
    int clientID;
    char clientType; // 'P' para jugador, 'O' para observador
    int observationTarget; // ID del jugador que el observador está observando
};


#endif //SERVER_CONSTANTS_H
