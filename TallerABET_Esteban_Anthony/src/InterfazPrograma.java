import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class InterfazPrograma extends JFrame{
    private JScrollBar scrollBar1;
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTextField textFieldInNombre;
    private JTextField textFieldInCedula;
    private JTextField textFieldInSueldo;
    private JButton crearButtonInEmpleado;
    private JTextField textFieldMoNombre;
    private JTextField textFieldMoCedula;
    private JTextField textFieldMoSueldo;
    private JTextField textFieldMoBuscarCedula;
    private JButton ButtonMoModificar;
    private JButton ButtonMoBuscarCedula;
    private JTextArea textAreaVisualizarER;
    private JButton ButtonVisualizarER;
    private JTextArea textAreaRolDePagos;
    private JButton visualizarRolDePagos;
    Empleado nuevoEmpleado;
    system newSystem=new system();
public InterfazPrograma() {
    add(panelPrincipal);
    setSize(500,500);
    setLocationRelativeTo(null);
    setTitle("Proyecto ABET ESTEBAN & ANTHONY");
    desActivarModificar();
    // Acción del botón "Crear Empleado"
    crearButtonInEmpleado.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validación de campos vacíos
            if (textFieldInCedula.getText().isEmpty() || !validarCedula(textFieldInCedula.getText())) {
                JOptionPane.showMessageDialog(null, "El campo de cédula está vacío o no tiene 10 numeros\nverifique el correcto ingreso");
            } else if (textFieldInNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de nombre está vacío");
            } else if (textFieldInSueldo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de sueldo está vacío");
            } else if (!validarTextFieldSoloNumeros(textFieldInSueldo) || Integer.valueOf(textFieldInSueldo.getText())<0) {//validacion sueldo sea + y mayor a 0
                JOptionPane.showMessageDialog(null, "El campo del sueldo debe ser positivo y solo numeros");
            } else if (!validarTextFieldSoloNumeros(textFieldInCedula)) {//validacion que el nombre contega solo letras
                JOptionPane.showMessageDialog(null, "Valores ingresados para la cedula no validos, verifique si son numeros");
            } else {
                int cedula;
                try {
                    cedula = Integer.parseInt(textFieldInCedula.getText());
                } catch (NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "La cédula debe ser un número válido");
                    return; // Salir del método si la cédula no es válida
                }
                // Verificar si ya existe un empleado con esa cédula
                if (newSystem.searchBinary(newSystem.empleadosRegistrados, cedula) == null) {
                    if (!textFieldInNombre.getText().isEmpty() && !textFieldInCedula.getText().isEmpty() && !textFieldInSueldo.getText().isEmpty()) {
                        nuevoEmpleado = new Empleado(textFieldInCedula.getText(), textFieldInNombre.getText(), Double.valueOf(textFieldInSueldo.getText()));
                        newSystem.registrarEmpleado(nuevoEmpleado);
                        JOptionPane.showMessageDialog(null, "Se ha creado con éxito el empleado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese número de cédula");
                }
            }
        }
    });
    // Acción del botón "Modificar Empleado"
    ButtonMoModificar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Verificar si se ingresó una cédula válida y existe en los registros
            if (!textFieldMoBuscarCedula.getText().isEmpty() && newSystem.searchBinary(newSystem.empleadosRegistrados,Integer.valueOf(textFieldMoBuscarCedula.getText()))!=null ) {
                    if (Double.parseDouble(textFieldMoSueldo.getText()) > 0){
                    String cedulaMo = textFieldMoCedula.getText();
                    String nombreMo = textFieldMoNombre.getText();
                    Empleado empleadoAuxiliar = newSystem.searchBinary(newSystem.empleadosRegistrados, Integer.valueOf(textFieldMoBuscarCedula.getText()));//empleado encontrado
                // Modificar cédula del empleado
                if (empleadoAuxiliar != null && cedulaMo != null && !cedulaMo.isEmpty()) {//validacion campos vacios
                    if (cedulaMo.equals(textFieldMoBuscarCedula.getText())) {
                        if (validarCedula(cedulaMo)){
                        // Modificar cedula del empleado
                        if (empleadoAuxiliar != null) {
                            modificarCedulaEmpleado(empleadoAuxiliar, cedulaMo);
                        }
                        // Modificar nombre del empleado
                        if (!nombreMo.isEmpty()) {
                            modificarNombreEmpleado(empleadoAuxiliar, nombreMo);
                        }
                        // Modificar sueldo del empleado
                        if (!textFieldMoSueldo.getText().isEmpty()) {
                            modificarSueldoEmpleado(empleadoAuxiliar, Double.valueOf(textFieldMoSueldo.getText()));
                        }
                        reiniciarJtextFieldModificar();//limpiar textfield de modifivar
                        Collections.sort(newSystem.empleadosRegistrados);//ordenar lista
                    }else{
                        JOptionPane.showMessageDialog(null, "La cedula debe tener 10 numeros para modificarse");
                        }

                    } else {
                        if (validarCedula(cedulaMo)){
                            if (Double.parseDouble(textFieldMoSueldo.getText()) > 0){
                        Empleado empleadoAuxilia2r = newSystem.searchBinary(newSystem.empleadosRegistrados, Integer.valueOf(textFieldMoCedula.getText()));
                        if (empleadoAuxilia2r == null) {
                            // Modificar cédula del empleado
                            if (empleadoAuxiliar != null) {
                                modificarCedulaEmpleado(empleadoAuxiliar, cedulaMo);
                            }
                            // Modificar nombre del empleado
                            if (!nombreMo.isEmpty()) {
                                modificarNombreEmpleado(empleadoAuxiliar, nombreMo);
                            }
                            // Modificar sueldo del empleado
                            if (!textFieldMoSueldo.getText().isEmpty()) {
                                modificarSueldoEmpleado(empleadoAuxiliar, Double.valueOf(textFieldMoSueldo.getText()));
                            }
                            reiniciarJtextFieldModificar();//borrar textfield modificar
                            Collections.sort(newSystem.empleadosRegistrados);//ordenar
                             } else {
                                JOptionPane.showMessageDialog(null, "Ya existe un empleado con la cedula que quiere poner");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "El sueldo debe ser mayor a 0 para poder modificarse");
                            }
                        } else{
                            JOptionPane.showMessageDialog(null, "La cedula debe tener 10 numeros para modificarse");
                        }

                    }
                        } else {
                    JOptionPane.showMessageDialog(null, "Es necesaria la cedula para poder modificar");
                        }
                    }else{
                    JOptionPane.showMessageDialog(null, "El sueldo debe ser mayor a 0 para poder modificarse");
                    }

            }else {
                JOptionPane.showMessageDialog(null, "No existe esa cedula o el campo esta vacio");

            }
        }
    });
    // Acción del botón "Visualizar Empleados Registrados"
    ButtonVisualizarER.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mostrar la lista de empleados registrados en el textArea
            textAreaVisualizarER.setText(""+newSystem.mostrarListaDeEmpleados(newSystem.empleadosRegistrados,false));
        }
    });
    // Acción del botón "Visualizar Rol de Pagos"
    visualizarRolDePagos.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mostrar el rol de pagos en el textArea
            textAreaRolDePagos.setText(""+newSystem.mostrarListaDeEmpleados(newSystem.calcularRol(newSystem.empleadosRegistrados),true) );

        }
    });
    // Acción del botón "Buscar por Cédula"
    ButtonMoBuscarCedula.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Verificar si se ingresó una cédula válida y existe en los registros
            if (!textFieldMoBuscarCedula.getText().isEmpty() && newSystem.searchBinary(newSystem.empleadosRegistrados,Integer.valueOf(textFieldMoBuscarCedula.getText()))!=null) {

                activarModificar();
                String cedulaMo=textFieldMoCedula.getText();
                String nombreMo=textFieldMoNombre.getText();
                Empleado empleadoAuxiliar = newSystem.searchBinary(newSystem.empleadosRegistrados, Integer.valueOf(textFieldMoBuscarCedula.getText()));
                // Llenar los campos con los datos del empleado encontrado
                textFieldMoNombre.setText(empleadoAuxiliar.getNombre());
                textFieldMoCedula.setText(empleadoAuxiliar.getCedula());
                textFieldMoSueldo.setText(String.valueOf(empleadoAuxiliar.getSueldo()));
            }else {
                JOptionPane.showMessageDialog(null, "No existe esa cedula o el campo esta vacio");
            }
        }
    });
}
    // Activar campos de modificación
public void activarModificar(){
        textFieldMoNombre.setEnabled(true);
        textFieldMoCedula.setEnabled(true);
        textFieldMoSueldo.setEnabled(true);
    }
    // Reiniciar campos de modificación
    public void reiniciarJtextFieldModificar(){
        textFieldMoNombre.setText("");
        textFieldMoCedula.setText("");
        textFieldMoSueldo.setText("");
        textFieldMoBuscarCedula.setText("");
    }
    // Desactivar campos de modificación
public void desActivarModificar(){
        textFieldMoNombre.setEnabled(false);
        textFieldMoCedula.setEnabled(false);
        textFieldMoSueldo.setEnabled(false);
    }
    // Modificar sueldo del empleado
    public void modificarSueldoEmpleado(Empleado empleadoModificar,Double sueldo){
        empleadoModificar.setSueldo(sueldo);
        JOptionPane.showMessageDialog(null,"Se ha modificado con exito el sueldo");
    }
    // Modificar cedula del empleado
    public void modificarCedulaEmpleado(Empleado empleadoModificar,String cedula){

        empleadoModificar.setCedula(cedula);
        JOptionPane.showMessageDialog(null, "Se ha modificado con exito la cedula");

    }
    // Modificar nombre del empleado
    public void modificarNombreEmpleado(Empleado empleadoModificar,String nombre){

            empleadoModificar.setNombre(nombre);
            JOptionPane.showMessageDialog(null,"Se ha modificado con exito el nombre");
    }
    //validar texto sea solo letras
    public boolean validarTextFieldSoloLetras(JTextField textField) {
        String texto = textField.getText();
        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false; // El texto contiene caracteres que no son letras
            }
        }
        return true; // El texto solo contiene letras
    }
    //validar que solo sean numeros los que se ingresan
    public boolean validarTextFieldSoloNumeros(JTextField textField) {
        String texto = textField.getText();
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // El texto contiene caracteres que no son números
            }
        }
        return true; // El texto solo contiene números
    }
    //validar cedula tenga 10 numeros
    public boolean validarCedula(String cedula) {
        if (cedula.length() <10) {
            return false; // La cédula no tiene 10 dígitos
        }

        for (char c : cedula.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // La cédula contiene caracteres que no son números
            }
        }

        return true; // La cédula es válida
    }
}
