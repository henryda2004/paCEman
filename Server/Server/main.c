#include <winsock2.h>
#include <stdio.h>
#include <string.h>
#include <process.h>
#include "constants.h"
/**
 * Estrcutura que representa un jugador
 */
struct Jugador {
    int ID;
    SOCKET clientSocket;
    int puntos;

};
/**
 * Estructura que representa un observador
 */
struct Observador {
    int ID;
    SOCKET clientSocket;
};

struct Jugador jugadores[MAX_JUGADORES];
struct Observador observadores[MAX_OBSERVADORES]; 

int numJugadores = 0;
int numObservadores = 0;
/**
 * Nombre: sendMessageToPlayerID
 * Descripcion: Permite enviar instrucciones al jugador segun lo ingresado por el usuario administrador en la consola
 * @param targetID
 * @param message
 */
void sendMessageToPlayerID(int targetID, const char* message) {
    char modifiedMessagePlayer[1024];
    if (strncmp(message, "CrearFantasma\n", 13) == 0) {
        snprintf(modifiedMessagePlayer, sizeof(modifiedMessagePlayer), "Fantasma%s", message + 13);
        strcpy(message, modifiedMessagePlayer); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "CrearPastilla\n", 13) == 0) {
        snprintf(modifiedMessagePlayer, sizeof(modifiedMessagePlayer), "Pastilla%s", message + 13);
        strcpy(message, modifiedMessagePlayer); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "CrearFruta\n", 10) == 0) {
        snprintf(modifiedMessagePlayer, sizeof(modifiedMessagePlayer), "Fruta%s", message + 10);
        strcpy(message, modifiedMessagePlayer); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "Velocidad\n",9) == 0) {
        snprintf(modifiedMessagePlayer, sizeof(modifiedMessagePlayer), "Velocidad%s", message + 9);
        strcpy(message, modifiedMessagePlayer); // Copia el nuevo mensaje a 'message'
    }
    for (int i = 0; i < numJugadores; i++) {
        if (jugadores[i].ID == targetID) {
            send(jugadores[i].clientSocket, message, strlen(message), 0);
        }
    }
}
/**
 * Nombre: updateObserver
 * Descripcion: Permite actualizar a los observadores asociados a un jugador sobre lo que ocurre en una partida
 * @param targetID
 * @param message
 */
void updateObserver(int targetID, const char* message) {
    char modifiedMessageObserver[1024];
    if (strncmp(message, "CrearFantasma\n", 13) == 0) {
        snprintf(modifiedMessageObserver, sizeof(modifiedMessageObserver), "Fantasma%s", message + 13);
        strcpy(message, modifiedMessageObserver); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "CrearPastilla\n", 13) == 0) {
        snprintf(modifiedMessageObserver, sizeof(modifiedMessageObserver), "Pastilla%s", message + 13);
        strcpy(message, modifiedMessageObserver); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "CrearFruta\n", 10) == 0) {
        snprintf(modifiedMessageObserver, sizeof(modifiedMessageObserver), "Fruta%s", message + 10);
        strcpy(message, modifiedMessageObserver); // Copia el nuevo mensaje a 'message'
    }
    else if (strncmp(message, "Velocidad\n",9) == 0) {
        snprintf(modifiedMessageObserver, sizeof(modifiedMessageObserver), "Velocidad%s", message + 9);
        strcpy(message, modifiedMessageObserver); // Copia el nuevo mensaje a 'message'
    }
    for (int i = 0; i < numObservadores; i++) {
        if (observadores[i].ID == targetID) {
            send(observadores[i].clientSocket, message, strlen(message), 0);
        }
    }
}
/**
 * Nombre: selectPlayerByID
 * Descripcion: Permite obtener todos los sockets asociados a un ID
 * @param playerID
 * @return
 */
SOCKET selectPlayerByID(int playerID) {
    for (int i = 0; i < numJugadores; i++) {
        if (jugadores[i].ID == playerID) {
            return jugadores[i].clientSocket;
        }
    }
    return INVALID_SOCKET; // Devuelve INVALID_SOCKET si el jugador no se encuentra
}
/**
 * Nombre: clearInputBuffer
 * Descripcion: Permite limpiar la lectura de consola para evitar errores de lectura
 */
void clearInputBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}
/**
 * Nombre: clientHandler
 * Descripcion: Permite interpretar y reaccionar ante los avisos del jugador sobre hechos ocurridos en la partida
 * @param clientSocket
 */
void clientHandler(SOCKET clientSocket) {
    char buffer[1024];
    int bytesRead;
    int bytesSent;

    while (1) {
        bytesRead = recv(clientSocket, buffer, sizeof(buffer), 0);
        if (bytesRead <= 0) {
            break;
        }
        buffer[bytesRead] = '\0';

        if (strncmp(buffer, "jugador", 7) == 0) {
            if (numJugadores < MAX_JUGADORES) {
                // Crear un nuevo jugador y asignarle un ID
                jugadores[numJugadores].ID = numJugadores + 1;
                jugadores[numJugadores].clientSocket = clientSocket;
                jugadores[numJugadores].puntos = 0;
                printf("Nuevo Jugador ID: %d\n", jugadores[numJugadores].ID);
                // Enviar el ID asignado al nuevo jugador
                char idMessage[1024];
                snprintf(idMessage, sizeof(idMessage), "ID %d\n", jugadores[numJugadores].ID);
                send(clientSocket, idMessage, strlen(idMessage), 0);
                numJugadores++;
            } else {
                printf("Numero maximo de jugadores alcanzado\n");
            }
        } else if (strncmp(buffer, "observador", 10) == 0) {
            if (numObservadores < MAX_OBSERVADORES) {
                int observadorID;
                if (sscanf(buffer, "observador %d", &observadorID) == 1) {
                    // Crear un nuevo observador y asignarle un ID
                    if (observadorID > numJugadores){
                        char errorMessage[1024];
                        snprintf(errorMessage, sizeof(errorMessage), "ID no disponible\n", jugadores[numJugadores].ID);
                        send(clientSocket, errorMessage, strlen(errorMessage), 0);
                    }
                    else {
                        observadores[numObservadores].ID = observadorID;
                        observadores[numObservadores].clientSocket = clientSocket;
                        printf("Nuevo Observador ID: %d\n", observadores[numObservadores].ID);
                        numObservadores++;
                    }
                } else {
                    printf("Formato de observador invalido\n");
                }
            } else {
                printf("Numero maximo de observadores alcanzado\n");
            }
        }

        if (strncmp(buffer, "puntos", 6) == 0) {
            int playerId;
            int puntos;
            if (sscanf(buffer, "puntos %d %d", &playerId, &puntos) == 2) {
                for (int i = 0; i < numJugadores; i++) {
                    if (jugadores[i].ID == playerId) {
                        jugadores[i].puntos += puntos*10; // Añadir los puntos al jugador
                        if (jugadores[i].puntos >= 10000) {
                            jugadores[i].puntos -= 10000;
                            char message[1024];
                            snprintf(message, sizeof(message), "Vida %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        } else {
                            char message[1024];
                            snprintf(message, sizeof(message), "puntaje %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        }
                        break;
                    }
                }
            }
        }
        if (strncmp(buffer, "fantasma", 8) == 0) {
            int playerId;
            if (sscanf(buffer, "fantasma %d", &playerId) == 1) {
                for (int i = 0; i < numJugadores; i++) {
                    if (jugadores[i].ID == playerId) {
                        jugadores[i].puntos += 500; // Añadir los puntos al jugador
                        if (jugadores[i].puntos >= 10000) {
                            jugadores[i].puntos -= 10000;
                            char message[1024];
                            snprintf(message, sizeof(message), "Vida %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        } else {
                            char message[1024];
                            snprintf(message, sizeof(message), "puntaje %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        }
                        break;
                    }
                }
            }
        }
        if (strncmp(buffer, "pastilla", 8) == 0) {
            int playerId;
            if (sscanf(buffer, "pastilla %d", &playerId) == 1) {
                for (int i = 0; i < numJugadores; i++) {
                    if (jugadores[i].ID == playerId) {
                        jugadores[i].puntos += 100; // Añadir los puntos al jugador
                        if (jugadores[i].puntos >= 10000) {
                            jugadores[i].puntos -= 10000;
                            char message[1024];
                            snprintf(message, sizeof(message), "Vida %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        } else {
                            char message[1024];
                            snprintf(message, sizeof(message), "puntaje %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        }
                        break;
                    }
                }
            }
        }
        if (strncmp(buffer, "fruta", 5) == 0) {
            int playerId;
            int puntos;
            if (sscanf(buffer, "fruta %d %d", &playerId, &puntos) == 2) {
                for (int i = 0; i < numJugadores; i++) {
                    if (jugadores[i].ID == playerId) {
                        jugadores[i].puntos += puntos; // Añadir los puntos al jugador
                        if (jugadores[i].puntos >= 10000) {
                            jugadores[i].puntos -= 10000;
                            char message[1024];
                            snprintf(message, sizeof(message), "Vida %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        } else {
                            char message[1024];
                            snprintf(message, sizeof(message), "puntaje %d\n", jugadores[i].puntos);
                            sendMessageToPlayerID(playerId, message);
                            updateObserver(playerId, message);
                        }
                        break;
                    }
                }
            }
        }

        if (strncmp(buffer, "nivel", 5) == 0) {
            sendMessageToPlayerID(1, buffer);
            updateObserver(1, buffer);
        }
        if (strncmp(buffer, "derecha", 7) == 0) {
            int playerId;
            if (sscanf(buffer, "derecha %d", &playerId) == 1) {
                char message[1024];
                snprintf(message, sizeof(message), "derecha\n");
                updateObserver(playerId, message);
            }
        }
        if (strncmp(buffer, "izquierda", 9) == 0) {
            int playerId;
            if (sscanf(buffer, "izquierda %d", &playerId) == 1) {
                char message[1024];
                snprintf(message, sizeof(message), "izquierda\n");
                updateObserver(playerId, message);
            }
        }
        if (strncmp(buffer, "arriba", 6) == 0) {
            int playerId;
            if (sscanf(buffer, "arriba %d", &playerId) == 1) {
                char message[1024];
                snprintf(message, sizeof(message), "arriba\n");
                updateObserver(playerId, message);
            }
        }
        if (strncmp(buffer, "abajo", 5) == 0) {
            int playerId;
            if (sscanf(buffer, "abajo %d", &playerId) == 1) {
                char message[1024];
                snprintf(message, sizeof(message), "abajo\n");
                updateObserver(playerId, message);
            }
        }

    }
    closesocket(clientSocket);
}
/**
 * Nombre: serverInputHandler
 * Descripcion: Permite realizar el protocolo de comunicacion para enviar instrucciones a jugadores a partir de la consola
 */
void serverInputHandler() {
    char message[1024];
    int targetPlayerID;

    while (1) {
        printf("Ingrese el ID del jugador al que quiere enviar el mensaje: \n");
        if (scanf("%d", &targetPlayerID) != 1) {
            clearInputBuffer();
            printf("ID de jugador no valido.\n");
            continue;
        }

        clearInputBuffer(); // Limpiar el búfer del teclado

        if (targetPlayerID == 0) {
            break; // Salir del bucle si se ingresa "0" como ID
        }

        if (targetPlayerID > 0 && targetPlayerID <= numJugadores) {
            printf("Ingrese el mensaje: \n");
            if (fgets(message, sizeof(message), stdin) == NULL) {
                break; // Salir del bucle si la entrada es nula
            }

            SOCKET targetSocket = selectPlayerByID(targetPlayerID);

            if (targetSocket != INVALID_SOCKET) {
                sendMessageToPlayerID(targetPlayerID, message);
                updateObserver(targetPlayerID, message);
            } else {
                printf("ID de jugador no valido.\n");
            }
        } else {
            printf("ID de jugador no valido.\n");
        }
    }
}
/**
 * Nombre: main
 * Descripcion: Metodo que inicializa el socket servido para admitir conexiones
 * @return
 */
int main() {
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        perror("WSAStartup failed");
        return 1;
    }

    SOCKET serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket == INVALID_SOCKET) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in serverAddr;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(12345);  // Use a port of your choice

    if (bind(serverSocket, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        perror("Bind failed");
        return 1;
    }

    if (listen(serverSocket, 5) == SOCKET_ERROR) {
        perror("Listen failed");
        return 1;
    }

    printf("Server listening on port 12345\n");

    // Crear el hilo serverInputHandler antes de entrar en el bucle
    _beginthread((void(*)(void*))serverInputHandler, 0, NULL);

    while (1) {
        SOCKET clientSocket;
        struct sockaddr_in clientAddr;
        int clientAddrLen = sizeof(clientAddr);

        if ((clientSocket = accept(serverSocket, (struct sockaddr *)&clientAddr, &clientAddrLen)) == INVALID_SOCKET) {
            perror("Accept failed");
            return 1;
        }

        printf("Client connected\n");

        // Crear un subproceso para manejar la comunicación con el cliente.
        _beginthread((void(*)(void*))clientHandler, 0, (void*)clientSocket);
    }

    closesocket(serverSocket);
    WSACleanup();

    return 0;
}
