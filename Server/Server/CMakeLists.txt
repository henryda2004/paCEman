cmake_minimum_required(VERSION 3.26)
project(Server C)

set(CMAKE_C_STANDARD 99)
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -static-libstdc++ -static-libgcc")

add_executable(Server main.c
        constants.h)

target_link_libraries(Server PRIVATE -lws2_32)
