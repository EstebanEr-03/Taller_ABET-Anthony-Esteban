import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class system {
    List<Empleado> empleadosRegistrados=new ArrayList<>();// Lista de empleados registrados
    List<Empleado> empleadosRegistradosConRolPagos=new ArrayList<>();// Lista de empleados registrados con roles de pagos
    // Constructor por defecto
    public system() {
    }
    // Método para registrar un empleado
    public void registrarEmpleado(Empleado empleadoRegistrar){
        empleadosRegistrados.add(empleadoRegistrar);// Agregar empleado a la lista de empleados registrados
        Collections.sort(empleadosRegistrados);// Ordenar la lista de empleados registrados por cédula
        Collections.sort(empleadosRegistradosConRolPagos);// Ordenar la lista de empleados registrados con roles de pagos por cédula
    }
    // Método para buscar un empleado por cédula utilizando búsqueda binaria
    public  Empleado searchBinary(List<Empleado> buscarEmpleadoPorCedulaLista, int targetTrackingCedula) {
        int izquierda=0, derecha= buscarEmpleadoPorCedulaLista.size() -1;
        while (izquierda<=derecha){
            int numMedio=(derecha+izquierda)/2;
            if(targetTrackingCedula==Integer.parseInt(buscarEmpleadoPorCedulaLista.get(numMedio).getCedula())){
                return buscarEmpleadoPorCedulaLista.get(numMedio);
            }else if(Integer.parseInt(buscarEmpleadoPorCedulaLista.get(numMedio).getCedula())<targetTrackingCedula){
                izquierda=numMedio+1;
            }else{
                derecha=numMedio-1;
            }
        }
        return null;// No se encontró el empleado con la cédula especificada
    }
    // Método para mostrar la lista de empleados
    public String mostrarListaDeEmpleados(List<Empleado> empleadosRegistradosLista,boolean rolPagos){
        String respuesta=" ";

        for (Empleado empleadoRegistrado:empleadosRegistradosLista)//recprre lista
        {
            if (empleadoRegistrado.getRolDePagos()!=null && rolPagos){
               respuesta+= empleadoRegistrado.toString().concat("\n"+empleadoRegistrado.getRolDePagos());//Guarda respuesta con rol de pagos
            }else{
                respuesta=empleadosRegistradosLista.toString();//guarda respuesta sin rol de pagos
                break;
            }
        }
        return respuesta;
    }
    // Método para mostrar la lista de empleados
    public List<Empleado> calcularRol(List<Empleado> empleadosCalcularImpuesto){
        empleadosRegistradosConRolPagos=new ArrayList<>() ;//lista donde se guardaran los empleados con el rol de pagos
        double sueldoNuev=0; //Inicializar valor del saldo nuevo a setear
        for (Empleado empleadoRecorreFor:empleadosCalcularImpuesto){//recorre lista que se le pasa por parametros
            try {
                Empleado empleadoAus=empleadoRecorreFor.deepClone();//se clona el empleado de la lista pasada por paramtros
                if (empleadoRecorreFor.getSueldo()<=5000){//valida primera condicion para sacar rol de pagos

                    empleadoAus.setRolDePagos(new RolDePagos(empleadoRecorreFor.getSueldo()*0.0935,0.0));//realiza calculo rol de pagos con 9.35% de aporte al seguro y se setea el rol de pagos
                    sueldoNuev=empleadoRecorreFor.getSueldo()-(empleadoRecorreFor.getSueldo()*0.0935);//saldo restado del rol de pagos
                    empleadoAus.setSueldo(sueldoNuev);//setea el saldo restado

                }else if (empleadoRecorreFor.getSueldo()>5000 && empleadoRecorreFor.getSueldo()<=10000){//valida segunda condicion para sacar rol de pagos
                    empleadoAus.setRolDePagos(new RolDePagos(empleadoRecorreFor.getSueldo()*0.0935,(empleadoRecorreFor.getSueldo()-5000)*0.10));//realiza calculo rol de pagos con 9.35% de aporte al seguro y 10% IR ; se setea el rol de pagos
                    sueldoNuev=empleadoRecorreFor.getSueldo()-(empleadoRecorreFor.getSueldo()*0.0935)-((empleadoRecorreFor.getSueldo()-5000)*0.10);//saldo restado del rol de pagos
                    empleadoAus.setSueldo(sueldoNuev);//setea el saldo restado

                }else if (empleadoRecorreFor.getSueldo()>10000 && empleadoRecorreFor.getSueldo()<=18000){//valida tercera condicion para sacar rol de pagos
                    empleadoAus.setRolDePagos(new RolDePagos(empleadoRecorreFor.getSueldo()*0.0935,(empleadoRecorreFor.getSueldo()-10000)*0.20));//realiza calculo rol de pagos con 9.35% de aporte al seguro y 20% IR ; se setea el rol de pagos
                    sueldoNuev=empleadoRecorreFor.getSueldo()-(empleadoRecorreFor.getSueldo()*0.0935)-((empleadoRecorreFor.getSueldo()-10000)*0.20);//saldo restado del rol de pagos
                    empleadoAus.setSueldo(sueldoNuev);//setea el saldo restado

                }else{
                    empleadoAus.setRolDePagos(new RolDePagos(empleadoRecorreFor.getSueldo()*0.0935,(empleadoRecorreFor.getSueldo()-18000)*0.30));//realiza calculo rol de pagos con 9.35% de aporte al seguro y 30% IR ; se setea el rol de pagos
                    sueldoNuev=empleadoRecorreFor.getSueldo()-(empleadoRecorreFor.getSueldo()*0.0935)-((empleadoRecorreFor.getSueldo()-18000)*0.30);//saldo restado del rol de pagos
                    empleadoAus.setSueldo(sueldoNuev);//setea el saldo restado

                }
                empleadosRegistradosConRolPagos.add(empleadoAus);//aniade al empleado con el nuevo sueldo y rol de pagos
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

        }
        return empleadosRegistradosConRolPagos;//retorna lista con empleados y el nuevo sueldo y rol de pagos
    }
}
