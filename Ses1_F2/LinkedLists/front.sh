#!/bin/bash

# Cambia al directorio Ses1_F2 (padre de LinkedLists)
cd ../..

# Colores ANSI
GREEN='\033[1;32m'
CYAN='\033[1;36m'
RED='\033[1;31m'
YELLOW='\033[1;33m'
RESET='\033[0m'

# Arte ASCII
ascii_art="${CYAN}

                                                                                                                                           
                 _|                        _|      _|        _|              _|      _|        _|            _|                        _|  
   _|_|_|    _|_|_|  _|    _|  _|    _|  _|_|      _|              _|_|_|  _|_|_|_|  _|            _|_|_|    _|  _|      _|_|      _|_|_|  
 _|    _|  _|    _|  _|    _|    _|_|      _|      _|        _|  _|_|        _|      _|        _|  _|    _|  _|_|      _|_|_|_|  _|    _|  
 _|    _|  _|    _|  _|    _|  _|    _|    _|      _|        _|      _|_|    _|      _|        _|  _|    _|  _|  _|    _|        _|    _|  
   _|_|_|    _|_|_|    _|_|_|  _|    _|    _|  _|  _|_|_|_|  _|  _|_|_|        _|_|  _|_|_|_|  _|  _|    _|  _|    _|    _|_|_|    _|_|_|  
                           _|                                                                                                              
                       _|_|                                                                                                                
${RESET}"

show_menu() {
    clear
    echo -e "$ascii_art"
    echo -e "${GREEN}══════════════════════════════════════════════${RESET}"
    echo -e "${YELLOW}       LISTA CIRCULAR DOBLE by ArelyXl    ${RESET}"
    echo -e "${GREEN}══════════════════════════════════════════════${RESET}"
    echo -e "${CYAN}[1]${RESET} Insertar al inicio"
    echo -e "${CYAN}[2]${RESET} Insertar al final"
    echo -e "${CYAN}[3]${RESET} Insertar"
    echo -e "${CYAN}[4]${RESET} Eliminar elemento"
    echo -e "${CYAN}[5]${RESET} Mostrar lista"
    echo -e "${CYAN}[6]${RESET} Destruir lista"
    echo -e "${CYAN}[7]${RESET} Insertion Sort"
    echo -e "${CYAN}[8]${RESET} Merge Sort"
    echo -e "${RED}[99]${RESET} Salir"
    echo -e "${GREEN}══════════════════════════════════════════════${RESET}"
    echo -ne "${YELLOW}[+] Selecciona opción ➤ ${RESET}"
}

if [ ! -f LinkedLists/Main.class ]; then
    echo -e "${YELLOW}Compilando programa Java...${RESET}"
    javac LinkedLists/*.java
fi

run_java() {
    echo -ne "${GREEN}"
    java -cp . Ses1_F2.LinkedLists.Main "$@" | while read -r line; do
        echo -e "  ${CYAN}»» ${line}${RESET}"
    done
    echo -ne "${RESET}"
}


while true; do
    show_menu
    read -r opcion
    case $opcion in
        1)
            read -p "Valor a insertar al inicio: " val
            run_java insertFirst "$val"
            ;;
        2)
            read -p "Valor a insertar al final: " val
            run_java insertLast "$val"
            ;;
        3)
            read -p "Valor a insertar ordenado: " val
            run_java add "$val"
            ;;
        4)
            read -p "Valor a eliminar: " val
            run_java remove "$val"
            ;;
        5)  run_java display ;;
        6)  run_java destroyList ;;
        7) run_java insertionSort ;;
        8) run_java mergeSort ;;
        99)
            echo -e "\n${RED}[+] Saliendo...${RESET}"
            exit 0
            ;;
        *)
            echo -e "\n${RED}[!] Opción inválida!${RESET}"
            ;;
    esac
    read -n 1 -s -r -p "Presiona cualquier tecla para continuar..."
done
