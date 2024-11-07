package org.VargasTest;

import org.VargasTest.Datos.EstudianteDao;
import org.VargasTest.dominio.Estudiante;

import java.io.Console;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SistemaEstudiantesApp {
    public static void main(String[] args) {
       var salir = false;
       var consola = new Scanner(System.in);

       //Se crea una instancia servicio
        var estudianteDao = new EstudianteDao();
        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            }catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
    }

    private static  void mostrarMenu(){
        System.out.println("""
                ***Sistema de estudiantes***
                1. Listar Estudiante
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion: 
                """);
    }
    private static boolean ejecutarOpciones(Scanner consola, EstudianteDao estudianteDao){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir= false;
        switch (opcion){
            case 1 -> {//Listar estudiante
                System.out.println("Listado de estudiantes");
                var estudiantes = estudianteDao.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { //Buscar Estudiante por id
                System.out.println("Introduce el id_estudiante a buscar");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado =  estudianteDao.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("No se encontro al estudiante con id: " +  estudiante);

            }
            case 3 -> {//Agregar estudiante
                System.out.println("Agregar estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono");
                var telefono =  consola.nextLine();
                System.out.println("Email");
                var email = consola.nextLine();

                // crear el objeto estudiante (sin el id)
                 var estudiante = new Estudiante(nombre, apellido, telefono, email);
                 var agregado = estudianteDao.agregarEstudiante(estudiante);
                 if(agregado)
                     System.out.println("Se agrego el estudiante: " + estudiante);
                 else
                     System.out.println("No se pudo agregar al estudiante: " + estudiante);

            }
            case 4 -> {//Modificar
                System.out.println("Modificar estudiante");
                System.out.println("Id Estudiante");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono");
                var telefono =  consola.nextLine();
                System.out.println("Email");
                var email = consola.nextLine();

                //Crear objeto estudiante a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificarEstudiante(estudiante);
                if(modificado)
                    System.out.println("Estudiante Modificado: " + estudiante);
                else
                    System.out.println("No se pudo modificar: " + estudiante);


            }
            case 5 ->{ // Eliminar
             System.out.println("Eliminar estudiante");
             System.out.println("Id Estudiante: ");
             var idEstudiante = Integer.parseInt(consola.nextLine());
             var estudiante = new Estudiante(idEstudiante);
             var eliminado = estudianteDao.eliminarEstudiante(estudiante);
             if(eliminado)
                 System.out.println("Estudiante eliminado: " + estudiante);
             else
                 System.out.println("No se pudo eliminar: " + estudiante);

         }
            case 6 -> { // Salir
             System.out.println("Hasta Pronto!");
             salir = true;
         }
            default -> System.out.println("Opcion no reconocida");
        }
        return salir;
    }
}