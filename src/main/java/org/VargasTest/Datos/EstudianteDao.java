package org.VargasTest.Datos;


import org.VargasTest.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.VargasTest.Conexion.Conexion.getConexion;

//Data acces object
public class EstudianteDao {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }


        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());

        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());

            }
        }
        return estudiantes;
    }

    //finById
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)" + "VALUES(?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error");
            }
        }
        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Ocurrio un error " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

        }
        return false;


    }

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al elminiar estudiante: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        var estudiantesDao = new EstudianteDao();

        //Agregar estudiante
       /* var nuevoEstudiante = new Estudiante("Carlos", "Lara", "67312768", "carlos@mail.com");
        var agregado = estudiantesDao.agregarEstudiante(nuevoEstudiante);
        if(agregado)
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        else
            System.out.println("No se agrego el estudiante: " + nuevoEstudiante);
        */




        //Modificar
        /*var estudianteModificar = new Estudiante(1, "Juan Carlos", "Juarez", "612762317", "juan@mail.com");
        var modificado = estudiantesDao.modificarEstudiante(estudianteModificar);
        if(modificado)
            System.out.println("Estudiante modificado: " + estudianteModificar);
        else
            System.out.println("No se pudo modificar: " + estudianteModificar);
*/

        // Eliminar estudiante

        /*var estudianteEliminar = new Estudiante(2);
        var eliminado =  estudiantesDao.eliminarEstudiante(estudianteEliminar);
        if (eliminado)
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        else
            System.out.println("No se pudo eliminar el estudiante: " + estudianteEliminar);
*/


        // Listar los estudiantes
        System.out.println("Listado Estudiantes");
        List<Estudiante> estudiantes = estudiantesDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        //Buscar por id
        /*var estudiante1 = new Estudiante(1);
        System.out.println("Estudiante antes de la busqueda: " +estudiante1);
        var encontrado = estudiantesDao.buscarEstudiantePorId(estudiante1);
        if(encontrado)
            System.out.println("Estudiante encontrado: " + estudiante1);
        else
            System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
        */

    }
}
