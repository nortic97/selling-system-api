#!/bin/bash

# Definir la carpeta base
base_folder=$(pwd)

# Carpeta donde se buscará recursivamente el archivo main.go
search_folder="$base_folder/Services"

# Carpeta donde se moverá el ejecutable generado
dist_folder="$base_folder/dist"

# Crear la carpeta 'dist' si no existe
mkdir -p "$dist_folder"

# Mostrar mensaje informativo
echo "Buscando archivos main.go en la carpeta 'Services'..."

# Buscar recursivamente el archivo main.go en la carpeta 'Services'
find "$search_folder" -type f -name "main.go" | while read -r file; do
    # Obtener la carpeta que contiene el archivo main.go
    folder=$(dirname "$file")

    # Mostrar mensaje informativo
    echo "Compilando en la carpeta: $folder"

    # Moverse a la carpeta y ejecutar el comando go build
    cd "$folder" || exit
    go build

    # Mostrar mensaje informativo
    echo "Moviendo ejecutable a la carpeta 'dist'..."

    # Mover el ejecutable generado a la carpeta 'dist', reemplazando si ya existe
    mv -f "$(basename "$folder")" "$dist_folder"

    # Mostrar mensaje informativo
    echo "Proceso completado en la carpeta: $folder"
    echo "----------------------------------------"

    # Volver a la carpeta base
    cd "$base_folder" || exit
done

# Mostrar mensaje informativo al finalizar
echo "Proceso finalizado. Los ejecutables se encuentran en la carpeta 'dist'."
