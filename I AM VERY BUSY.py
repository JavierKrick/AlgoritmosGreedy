#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Aug 29 07:16:14 2024

@author: javier
"""



def algoritmoDeFloyd(arr, n, i):
    maxi = i  # Inicializar el mayor como la raíz
    NodoIzq = 2 * i + 1  # hijo izquierdo
    NodoDer = 2 * i + 2  # hijo derecho

    # Verificar si el hijo izquierdo es más grande que la raíz
    if NodoIzq < n and arr[NodoIzq][1] > arr[maxi][1]:
        maxi = NodoIzq

    # Verificar si el hijo derecho es más grande que el mayor hasta ahora
    if NodoDer < n and arr[NodoDer][1] > arr[maxi][1]:
        maxi = NodoDer

    # Si el mayor no es la raíz
    if maxi != i:
        arr[i], arr[maxi] = arr[maxi], arr[i]  # intercambiar

        # Recursivamente aplicar algoritmoDeFloyd en el subárbol afectado
        algoritmoDeFloyd(arr, n, maxi)

def ordenarConMaxHeapInPlace(arr):
    n = len(arr)

    # Construir un max-heap
    for i in range(n // 2 - 1, -1, -1):
        algoritmoDeFloyd(arr, n, i)

    # Extraer elementos uno por uno
    for i in range(n-1, 0, -1):
        arr[0], arr[i] = arr[i], arr[0]  # intercambiar el mayor elemento con el último
        algoritmoDeFloyd(arr, i, 0)  # heapificar el resto de la lista

    return arr




##Es un algoritmo greedy, arranco ordenandolos en función del que termina antes, y los voy agregando


def maximasActividades(actividades):
    
    #Ordeno sólo en función de cuando termina de manera ascendente
    actividades = ordenarConMaxHeapInPlace(actividades)
    
    print(actividades)
    
    
    ultimaHora = -1
    contador = 0
    
    for act in actividades:
        if (act[0] >= ultimaHora):
            contador = contador+1
            ultimaHora = act[1]
            
            
    
    return contador






