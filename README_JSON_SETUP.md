# Configuración del Parser JSON para Niveles

## Resumen
Se ha implementado la funcionalidad para leer niveles desde archivos JSON usando la librería Gson de Google.

## Archivos Modificados/Creados

### ✅ Archivos Creados:
- `pom.xml` - Configuración Maven con dependencia de Gson
- `nivel_simple.json` - Archivo de ejemplo basado en el nivel hardcodeado
- `src/nivel_1.json` - Archivo JSON corregido (sintaxis válida)

### ✅ Archivos Modificados:
- `src/Parser/CreadorDeNivel.java` - Implementación completa del parser JSON

## Cómo Usar

### 1. Descargar Dependencias
Una vez que tengas Maven instalado, ejecuta en la raíz del proyecto:
```bash
mvn clean install
```

### 2. Usar el Parser
```java
CreadorDeNivel creador = new CreadorDeNivel(fabricaSkins);
creador.setFrabricaEntidades(fabricaEntidades);

// Leer nivel desde JSON
Nivel nivel = creador.leerArchivo("nivel_simple.json");
```

## Formato JSON

```json
{
    "nivel": 1,
    "jugador": {
        "x": 10,
        "y": 7650
    },
    "estructuras": [
        {"tipo": "plataforma", "x": 0, "y": 7620},
        {"tipo": "pared", "x": 0, "y": 0}
    ],
    "enemigos": [
        {"tipo": "demonioRojo", "x": 100, "y": 7650},
        {"tipo": "trollAmarillo", "x": 200, "y": 7650}
    ]
}
```

## Tipos Soportados

### Estructuras:
- `plataforma`
- `platmovil`
- `platquebradiza`
- `sueloresbaladizo`
- `pared`
- `pincho`
- `escalera`

### Enemigos:
- `demonioRojo`
- `trollAmarillo`
- `ranadefuego`
- `calabaza`
- `moghera`
- `fantasma`

## Notas
- Los tipos son case-insensitive (se convierten a minúsculas)
- Si un tipo no es reconocido, se muestra un error pero el juego continúa
- El método `crearNivelHarcodeando()` sigue disponible para compatibilidad
