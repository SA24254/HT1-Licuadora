import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Blender blender = new BasicBlender();
        Scanner scanner = new Scanner(System.in);
       
        // Menú de opciones
        String menu = "Seleccione una opción:\n"
                + "1. Prende la licuadora\n"
                + "2. Llena la licuadora con lo que se desea licuar\n"
                + "3. Incrementa velocidad\n"
                + "4. Consulta en qué velocidad está la licuadora\n"
                + "5. Consulta si la licuadora está llena\n"
                + "6. Vaciar la licuadora\n"
                + "7. Salir";

        int option = -1;

        // Bucle de interacción
        while (option != 7) {
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            // Acciones según opción seleccionada
            if (option == 1) {
                blender.turnOn();
                System.out.println("La licuadora está encendida.");
            } else if (option == 2) {
                System.out.print("Ingrese el contenido a licuar: ");
                String content = scanner.nextLine();
                try {
                    blender.addContent(content);
                    System.out.println("Contenido agregado a la licuadora.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (option == 3) {
                try {
                    blender.increaseSpeed();
                    System.out.println("Velocidad incrementada. Velocidad actual: " + blender.getCurrentSpeed());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (option == 4) {
                System.out.println("Velocidad actual: " + blender.getCurrentSpeed());
            } else if (option == 5) {
                System.out.println("¿La licuadora está llena? " + (blender.isFull() ? "Sí" : "No"));
            } else if (option == 6) {
                blender.emptyBlender();
                System.out.println("La licuadora ha sido vaciada.");
            } else if (option == 7) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}

interface Blender {
    void turnOn();
    void addContent(String content);
    void increaseSpeed();
    int getCurrentSpeed();
    boolean isFull();
    void emptyBlender();
}

class BasicBlender implements Blender {
    private boolean isOn = false;
    private int currentSpeed = 0;
    private String content = null;

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void addContent(String content) {
        if (!isOn) {
            throw new IllegalStateException("La licuadora debe estar encendida para agregar contenido.");
        }
        this.content = content;
    }

    @Override
    public void increaseSpeed() {
        if (!isOn) {
            throw new IllegalStateException("La licuadora debe estar encendida para aumentar la velocidad.");
        }
        if (content == null) {
            throw new IllegalStateException("La licuadora no puede funcionar vacía.");
        }
        if (currentSpeed < 10) {
            currentSpeed++;
        } else {
            throw new IllegalStateException("La velocidad máxima es 10.");
        }
    }

    @Override
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public boolean isFull() {
        return content != null;
    }

    @Override
    public void emptyBlender() {
        content = null;
        currentSpeed = 0;
    }
}
