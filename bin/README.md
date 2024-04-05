# Vortex Engine
## Descripcion
Vortex engine es una librería para crear videojuegos 2D simples en Java. Permite crear GameObjects y componentes instanciables de forma sencilla además de trabajar con escenas


## 📋 Características
- **Manejo de objetos y componentes:** El motor permite poder asignar diferente lógica a los objetos por medio de compontenes. Un objeto puede tener multiples componentes y trabajar con ellos es sencillo. Puede crear sus propios componentes fácilmente

- **Estructuracion por escenas:** Vortex Engine maneja los espacios renderizables por escena, permitiendo mostrar distintas escenas en la misma ventana de forma eficiente

## 🛠️ Building 

### Requisitos previos
- Java Development Kit (JDK) 18 o superior. Puede descargarla [aquí](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- Libreria JSFML: Simple and Fast Multimedia Library

### Pasos de compilación
1. Clona el repositorio: `git clone https://github.com/Asierso/VortexEngine.git` o bien descargue una de las versiones "release" de VortexEngine
2. Configure la version de Java preferiblemente en la versión [jdk 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
3. Compile el proyecto con Apache Maven usando `mvn package`

> [!NOTE]
> No se han realizado tests en versiones de java inferiores a la 18.0 por lo que se pueden experimentar bugs o incompatibilidades

## ✍️ Ejemplos de codigo

### Primera ventana
Se muestra el fragmento de un pequeño código para generar una nueva ventana y renderizar una escena vacía:
```java
Window ventana = new Window(600,500);
ventana.setTitle("Mi primera ventana");
ventana.setScene(new MainScene());
ventana.instantiate();
```

```java
public class MainScene implements Scene{
    @Override
    public void start() {
        //Se repite al iniciar la escena
    }

    @Override
    public void update(Window window, Iterable<Event> events) {
        //Se repite cada vez que se renderiza la escena
    }
}
```
---
### Primer objeto
Se muestra un pequeño fragmento de código para generar un GameObject con forma de rectangulo blanco, el cual se puede manipular mediante su transform

```java
public class Rectangle extends GameObject {
    @Override
    public void render(Window win) { 
        //Se repite al instanciar el objeto en cada actualizacion del render

        //Dibujar un pequeño rectangulo
        RectangleShape shape = new RectangleShape();
        shape.setFillColor(Color.WHITE);
        shape.setPosition(this.getPosition());
        shape.setRotation(this.getRotation());
        shape.setSize(new Vector2f(this.getBoxSize().x, this.getBoxSize().y));

        //Renderiza el objeto en la ventana actual
        win.getRender().draw(shape);
    }
}
```

Este objeto se puede instanciar en cualquier escena las veces que sea necesario. En el siguiente código se muestra un ejemplo de como se instancia un GameObject en una escena

```java
public class MainScene implements Scene{
    private Rectangle rectangulo;
    
    @Override
    public void start() {
        //Se crea el objeto y se definen sus propiedades
        rectangulo = new Rectangle();
        rectangulo.setPosition(30,30);
        rectangulo.setBoxSize(20,10);
    }

    @Override
    public void update(Window window, Iterable<Event> events) {
        //Se instancia el objeto en la escena actual
        rectangulo.instantiate(window);
    }
}
```

> [!TIP]
> Se pueden agregar componentes a los GameObject usando la sentencia ```gameObject.addComponent(new Componente())``` y acceder acceder al componente, usando el metodo ```gameObject.<Componente>getComponent(Componente.class)```