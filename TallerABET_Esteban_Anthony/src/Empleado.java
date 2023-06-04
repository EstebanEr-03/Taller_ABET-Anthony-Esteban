public class Empleado implements Comparable <Empleado>,Cloneable{//
    String cedula,nombre;// Cédula del empleado,// Nombre del empleado
    double sueldo;// Sueldo del empleado
    RolDePagos rolDePagos;// Objeto RolDePagos asociado al empleado
    // Constructor por defecto
    public Empleado() {
    }
    // Constructor que recibe cédula, nombre y sueldo del empleado, el cual se insertra en la lista empleadosRegistrados
    public Empleado(String cedula, String nombre, double sueldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }
    // Constructor que recibe cédula, nombre, sueldo y un objeto RolDePagos del empleado el cual se insertra en la lista empleadosRegistradosConRol
    public Empleado(String cedula, String nombre, double sueldo, RolDePagos rolDePagos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.rolDePagos = rolDePagos;
    }

    public String getCedula() {// Método getter para obtener la cédula del empleado
        return cedula;
    }

    public void setCedula(String cedula) {// Método setter para establecer la cédula del empleado
        this.cedula = cedula;
    }

    public String getNombre() {// Método getter para obtener el nombre del empleado
        return nombre;
    }

    public void setNombre(String nombre) {// Método setter para establecer el nombre del empleado
        this.nombre = nombre;
    }

    public double getSueldo() {// Método getter para obtener el sueldo del empleado
        return sueldo;
    }

    public void setSueldo(double sueldo) {// Método setter para establecer el sueldo del empleado
        this.sueldo = sueldo;
    }

    public RolDePagos getRolDePagos() {// Método getter para obtener el objeto RolDePagos asociado al empleado
        return rolDePagos;
    }

    public void setRolDePagos(RolDePagos rolDePagos) {// Método setter para establecer el objeto RolDePagos asociado al empleado
        this.rolDePagos = rolDePagos;
    }
    // Método que realiza una clonación profunda del objeto Empleado
    public Empleado deepClone() throws CloneNotSupportedException{
        Empleado clone=(Empleado) super.clone();
        return clone;
}
    // Método toString para obtener una representación en cadena de caracteres del objeto Empleado
    @Override
    public String toString() {
        return "\n\nEmpleado:\nNombre=" + nombre + "\nCedula=" + cedula +
                "\nSueldo=" + sueldo;
    }

    // Método compareTo para comparar dos objetos Empleado basándose en la cédula y asi ordenarlo de menor a mayor
    public int compareTo(Empleado o) {
        int comparedResult=cedula.compareTo(o.cedula);
        if(comparedResult>0){
            return 1;
        } else if (comparedResult<0) {
            return -1;
        } else {
            return 0;
        }
    }
}