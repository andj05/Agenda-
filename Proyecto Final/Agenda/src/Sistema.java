import java.util.Scanner;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        ManejadorBD db = new ManejadorBD();
        ManejadorContacto mc = new ManejadorContacto(db);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("---- Menú Agenda ----");
            System.out.println("1. Agregar Contacto");
            System.out.println("2. Modificar Contacto");
            System.out.println("3. Eliminar Contacto");
            System.out.println("4. Mostrar Contactos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                System.out.println(" ");
                    System.out.println("__+__ Agregar Contacto __+__");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = scanner.nextLine();
                    System.out.print("Empresa: ");
                    String empresa = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();

                    Contacto contacto = new Contacto(0, nombre, apellidos, empresa, telefono, correo);
                    mc.guardar(contacto);
                    System.out.println("Contacto agregado con éxito");
                    System.out.println(" ");
                    break;

                    case 2:
                    System.out.println(" ");
                    System.out.println("___@___ Modificar Contacto___@___");
                    System.out.print("ID del contacto a modificar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");
                    System.out.println("Seleccione el campo a modificar:");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellidos");
                    System.out.println("3. Empresa");
                    System.out.println("4. Teléfono");
                    System.out.println("5. Correo");
                    System.out.print("Opción: ");
                    int campo = scanner.nextInt();
                    scanner.nextLine();
                
                    String nuevoValor;
                    switch (campo) {
                        case 1:
                        System.out.println(" ");
                            System.out.print("Nuevo Nombre: ");
                            nuevoValor = scanner.nextLine();
                            mc.actualizarCampo(idActualizar, "nombre", nuevoValor);
                            break;
                        case 2:
                            System.out.print("Nuevos Apellidos: ");
                            nuevoValor = scanner.nextLine();
                            mc.actualizarCampo(idActualizar, "apellidos", nuevoValor);
                            break;
                        case 3:
                            System.out.print("Nueva Empresa: ");
                            nuevoValor = scanner.nextLine();
                            mc.actualizarCampo(idActualizar, "empresa", nuevoValor);
                            break;
                        case 4:
                            System.out.print("Nuevo Teléfono: ");
                            nuevoValor = scanner.nextLine();
                            mc.actualizarCampo(idActualizar, "telefono", nuevoValor);
                            break;
                        case 5:
                            System.out.print("Nuevo Correo: ");
                            nuevoValor = scanner.nextLine();
                            mc.actualizarCampo(idActualizar, "correo", nuevoValor);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    System.out.println("Campo modificado con éxito");
                    System.out.println(" ");
                    break;

                case 3:
                    System.out.println("____! Eliminar Contacto !____");
                    System.out.print("ID del contacto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    mc.eliminar(idEliminar);
                    System.out.println("Contacto eliminado con éxito");
                    System.out.println(" ");
                    break;
                case 4:
                System.out.println("=============================================================================================================");
                System.out.println("                                           & Lista de Contactos &                                            ");
                System.out.println("=============================================================================================================");
                System.out.printf("| %-4s | %-15s | %-15s | %-15s | %-16s | %-25s |\n", 
                                  "ID", "Nombre", "Apellidos", "Empresa", "Teléfono", "Correo");
                System.out.println("=============================================================================================================");
            
                List<Contacto> contactos = mc.obtener();
                for (Contacto c : contactos) {
                    System.out.printf("| %-4d | %-15s | %-15s | %-15s | %-16s | %-25s |\n", 
                                      c.getId(), c.getNombre(), c.getApellidos(), c.getEmpresa(), 
                                      c.getTelefono(), c.getCorreo());
                }
                System.out.println("=============================================================================================================");
                System.out.println(" ");
                    break;

                case 5:
                    System.out.println("Saliendo del agenda...");
                    db.desconectar(); 
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
