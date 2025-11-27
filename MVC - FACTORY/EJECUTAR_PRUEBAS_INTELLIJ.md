# Guía de Ejecución de Pruebas en IntelliJ IDEA

## 📋 Pruebas Implementadas

Se han creado **2 suites de pruebas JUnit 5** completas:

### 1. **SingletonVsFactoryTest.java**
Comparativa exhaustiva entre ambos patrones con 10 métricas:
- ✅ Unicidad de instancia (Singleton)
- ✅ Estado compartido entre controladores
- ✅ Performance (1000 operaciones)
- ✅ Consumo de memoria
- ✅ Validación de datos
- ✅ Testabilidad
- ✅ Acoplamiento y cohesión
- 📊 Tabla comparativa final

### 2. **FactoryPatternTest.java**
Pruebas específicas del Factory Pattern con 11 casos:
- ✅ Validación de cédulas ecuatorianas (válidas e inválidas)
- ✅ Validación de edades (válidas e inválidas)
- ✅ Validación de nombres
- ✅ Creación desde archivo CSV
- ✅ Limpieza de espacios (trim)
- ✅ Creación de múltiples instancias

---

## 🚀 Cómo Ejecutar en IntelliJ IDEA

### Opción 1: Ejecutar TODAS las pruebas del proyecto

1. **Abrir el proyecto** en IntelliJ IDEA
2. En el panel izquierdo, navegar a:
   ```
   src/test/java/ec/edu/espe/patrones/
   ```
3. **Clic derecho** sobre la carpeta `patrones`
4. Seleccionar: **"Run 'Tests in 'patrones''"**
5. Ver resultados en la pestaña **Run** (abajo)

### Opción 2: Ejecutar una suite específica

#### A. Ejecutar SingletonVsFactoryTest
1. Abrir el archivo:
   ```
   src/test/java/ec/edu/espe/patrones/SingletonVsFactoryTest.java
   ```
2. **Clic derecho** en el nombre de la clase
3. Seleccionar: **"Run 'SingletonVsFactoryTest'"**

#### B. Ejecutar FactoryPatternTest
1. Abrir el archivo:
   ```
   src/test/java/ec/edu/espe/patrones/FactoryPatternTest.java
   ```
2. **Clic derecho** en el nombre de la clase
3. Seleccionar: **"Run 'FactoryPatternTest'"**

### Opción 3: Ejecutar un test individual

1. Abrir cualquier archivo de test
2. Buscar un método con `@Test`
3. **Clic en el ícono verde** ▶️ al lado del método
4. Seleccionar: **"Run 'nombreDelTest()'"**

### Opción 4: Ejecutar con Maven (Terminal en IntelliJ)

```bash
# Abrir Terminal en IntelliJ (Alt + F12)

# Ejecutar todas las pruebas
mvn test

# Ejecutar solo SingletonVsFactoryTest
mvn test -Dtest=SingletonVsFactoryTest

# Ejecutar solo FactoryPatternTest
mvn test -Dtest=FactoryPatternTest

# Ejecutar con reportes detallados
mvn clean test
```

---

## 📊 Interpretación de Resultados

### Consola de Salida
Verás información detallada como:

```
╔════════════════════════════════════════════════════════════════╗
║     PRUEBAS COMPARATIVAS: SINGLETON VS FACTORY PATTERN        ║
╚════════════════════════════════════════════════════════════════╝

📊 MÉTRICA 1: Unicidad de Instancia
─────────────────────────────────────────────────────────────
✅ Service1 hashCode: 123456789
✅ Service2 hashCode: 123456789
✅ Service3 hashCode: 123456789
✅ RESULTADO: Las 3 instancias son IDÉNTICAS (Singleton correcto)

📊 MÉTRICA 2: Estado Compartido entre Controladores
─────────────────────────────────────────────────────────────
🔹 Controlador 1 agrega: Juan Pérez
🔹 Controlador 2 agrega: María López
🔹 Controlador 3 agrega: Carlos Ruiz

✅ Controlador 1 ve: 3 estudiantes
✅ Controlador 2 ve: 3 estudiantes
✅ Controlador 3 ve: 3 estudiantes
✅ RESULTADO: TODOS comparten la misma lista (Singleton correcto)
```

### Panel de Resultados JUnit

IntelliJ mostrará:
- ✅ **Verde**: Test pasó correctamente
- ❌ **Rojo**: Test falló
- ⏱️ **Tiempo**: Duración de cada test
- 📊 **Estadísticas**: Total pasados/fallados

---

## 🎯 Métricas Clave a Observar

### 1. **Singleton - Unicidad de Instancia**
```java
EstudianteService service1 = EstudianteService.getInstance();
EstudianteService service2 = EstudianteService.getInstance();
// ✅ Ambos deben tener el MISMO hashCode
```

### 2. **Singleton - Estado Compartido** ⭐ **MÉTRICA PRINCIPAL**
```java
EstudianteService ctrl1 = EstudianteService.getInstance();
EstudianteService ctrl2 = EstudianteService.getInstance();
EstudianteService ctrl3 = EstudianteService.getInstance();

ctrl1.guardarEstudiante("1713175071", "Juan", "25");
ctrl2.guardarEstudiante("0926837465", "María", "22");
ctrl3.guardarEstudiante("1804567890", "Carlos", "30");

// ✅ TODOS los controladores ven los 3 estudiantes
// Esto demuestra que comparten la MISMA lista en memoria
```

### 3. **Factory - Múltiples Instancias**
```java
Estudiante est1 = EstudianteFactory.crearEstudiante(...);
Estudiante est2 = EstudianteFactory.crearEstudiante(...);
// ✅ Ambos tienen DIFERENTE hashCode (instancias independientes)
```

### 4. **Performance**
```
⏱️  SINGLETON (1000 accesos):  < 10 ms ⚡
⏱️  FACTORY (1000 creaciones): ~100-500 ms
```

### 5. **Memoria**
```
💾 SINGLETON (1 instancia):     ~1 KB
💾 FACTORY (1000 instancias):   ~50-100 KB
```

---

## 📈 Tabla Comparativa Final

Al final de `SingletonVsFactoryTest`, verás:

```
╔══════════════════════════════════════════════════════════════════════╗
║                    RESUMEN COMPARATIVO FINAL                         ║
╠══════════════════════════════════════════════════════════════════════╣
║ MÉTRICA                     │ SINGLETON          │ FACTORY            ║
╠══════════════════════════════════════════════════════════════════════╣
║ Instancias                  │ 1 (única) ✅       │ N (múltiples)      ║
║ Estado compartido           │ SÍ ✅              │ NO                 ║
║ Performance (1000x)         │ < 10ms ✅          │ ~100-500ms         ║
║ Consumo memoria             │ Mínimo ✅          │ Proporcional       ║
║ Validación de datos         │ Delega             │ Completa ✅        ║
║ Testabilidad                │ Media              │ Alta ✅            ║
║ Acoplamiento                │ Medio              │ Bajo ✅            ║
║ Cohesión                    │ Media              │ Alta ✅            ║
║ Thread-Safety               │ Requiere sync      │ Natural ✅         ║
║ Reusabilidad                │ Limitada           │ Alta ✅            ║
╚══════════════════════════════════════════════════════════════════════╝
```

---

## 🔍 Verificación de Cambios Realizados

### Clases Modificadas a Singleton:

1. **EstudianteRepository.java**
   ```java
   private static EstudianteRepository instance;
   
   private EstudianteRepository() { ... } // Constructor privado
   
   public static synchronized EstudianteRepository getInstance() {
       if (instance == null) {
           instance = new EstudianteRepository();
       }
       return instance;
   }
   ```

2. **EstudianteService.java**
   ```java
   private static EstudianteService instance;
   
   private EstudianteService() { ... } // Constructor privado
   
   public static synchronized EstudianteService getInstance() {
       if (instance == null) {
           instance = new EstudianteService();
       }
       return instance;
   }
   ```

3. **EstudianteUI.java**
   ```java
   public EstudianteUI() {
       service = EstudianteService.getInstance(); // Usar getInstance()
       // ...
   }
   ```

### Factory Pattern:

**EstudianteFactory.java** - Ya implementado con:
- `crearEstudiante()` - Creación con validación completa
- `crearDesdeArchivo()` - Creación desde CSV
- `crearParaActualizacion()` - Creación para edición
- Validación de cédula ecuatoriana (algoritmo oficial)

---

## ⚠️ Troubleshooting

### Si aparece "Cannot resolve symbol 'Test'"
1. Verificar que el `pom.xml` tenga JUnit 5:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
   </dependency>
   ```
2. **Clic derecho** en el proyecto → **Maven** → **Reload Project**

### Si los tests no aparecen en verde
1. **File** → **Project Structure**
2. **Modules** → Verificar que `src/test/java` esté marcado como **Test Sources** (verde)

### Si hay errores de compilación
1. **Build** → **Rebuild Project**
2. Verificar que el JDK sea **Java 21** (configurado en `pom.xml`)

---

## 🎓 Conclusiones Esperadas

Después de ejecutar las pruebas, deberías observar:

1. ✅ **Singleton** mantiene UNA SOLA instancia en toda la aplicación
2. ✅ Múltiples controladores **comparten la MISMA lista** de estudiantes
3. ✅ **Factory** crea múltiples instancias independientes
4. ✅ Singleton es **más rápido** y usa **menos memoria**
5. ✅ Factory tiene **mejor validación** y es **más testeable**
6. ✅ **Ambos patrones son complementarios** y se usan juntos

---

## 📚 Documentación Adicional

- **Documentación del Factory**: `FACTORY_PATTERN_DOCUMENTATION.md`
- **Código fuente**: `src/main/java/ec/edu/espe/datos/factory/`
- **Tests**: `src/test/java/ec/edu/espe/patrones/`

---

## 🏆 Resumen de Comandos Rápidos

```bash
# En IntelliJ Terminal (Alt + F12)

# Ejecutar todas las pruebas
mvn test

# Solo comparativas Singleton vs Factory
mvn test -Dtest=SingletonVsFactoryTest

# Solo pruebas del Factory
mvn test -Dtest=FactoryPatternTest

# Limpiar y ejecutar todo
mvn clean test

# Ver solo resultados (sin logs de Spring)
mvn test -Dtest=SingletonVsFactoryTest -q
```

---

¡Listo para ejecutar! 🚀

**Paso siguiente**: Abrir IntelliJ → Navegar a `src/test/java/ec/edu/espe/patrones/` → Ejecutar las pruebas
