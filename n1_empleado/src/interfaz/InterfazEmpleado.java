/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mundo.Empleado;
import mundo.Fecha;



/**
 * Esta es la ventana principal de la aplicación. Contiene a los paneles que tienen los botones y muestran la información del empleado.
 */
public class InterfazEmpleado extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * El empleado que se está mostrando
     */
    private Empleado empleado;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Es el panel que contiene los elementos para mostrar los datos del empleado
     */
    private PanelDatos panelDatos;

    /**
     * Es el panel que contiene los elementos para ver y modificar el salario del empleado
     */
    private PanelSalario panelSalario;

    /**
     * Es el panel que contiene los elementos para realizar consultas sobre el empleado
     */
    private PanelConsultas panelConsultas;

    /**
     * Es el panel que contiene los elementos para ejecutar las extensiones a la aplicación
     */
    private PanelExtensiones panelExtensiones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye una nueva interfaz inicializada con los datos de una empleado particular. <br>
     * <b>post: </b> El objeto InterfazEmpleado está inicializado y empleado == e.
     * @param e Empleado con el que se inicializa la interfaz. e != null.
     */
    public InterfazEmpleado( Empleado e )
    {
        setTitle( "Sistema de Empleados" );

        // construir los paneles
        JPanel panelCentral = new JPanel( );
        panelDatos = new PanelDatos( );
        panelSalario = new PanelSalario( this );
        panelConsultas = new PanelConsultas( );
        panelExtensiones = new PanelExtensiones( this );

        // organizar el panel principal
        getContentPane( ).setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelDatos, BorderLayout.NORTH );
        getContentPane( ).add( panelCentral, BorderLayout.CENTER );
        getContentPane( ).add( panelExtensiones, BorderLayout.SOUTH );

        // organizar el panel central
        panelCentral.setLayout( new BorderLayout( ) );
        panelCentral.add( panelSalario, BorderLayout.NORTH );
        panelCentral.add( panelConsultas, BorderLayout.CENTER );

        setSize( 530, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        empleado = e;
        panelConsultas.cambiarEmpleado( e );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Este método sirve para actualizar los campos de la forma con los datos actuales del empleado. <br>
     * <b>post: </b>Los campos de la ventana contienen la información del empleado. <br>
     */
    public void actualizarEmpleado( )
    {
        String nombre, apellido, sexo, fechaI, fechaN, imagen;
        int salario;

        nombre = empleado.darNombre( );
        apellido = empleado.darApellido( );

        int iSexo = empleado.darSexo( );
        if( iSexo == 1 )
            sexo = "m";
        else
            sexo = "f";

        fechaI = empleado.darFechaIngreso( );
        fechaN = empleado.darFechaNacimiento( );
        salario = empleado.darSalario( );
        imagen = empleado.darImagen( );

        panelDatos.actualizarCampos( nombre, apellido, sexo, fechaI, fechaN, imagen );
        panelSalario.actualizarSalario( salario );

        panelConsultas.limpiarCampos( );

        validate( );
    }

    /**
     * En este método se solicita al usuario que ingrese el nuevo salario del empleado y se actualiza su información. <br>
     * <b>post: </b> Se actualizó el salario del empleado y se presentó la información actualizada. <br>
     */
    public void modificarSalario( )
    {
        String strSalario = JOptionPane.showInputDialog( this, "Introduzca el nuevo salario" );
        if( strSalario != null )
        {
            try
            {
                int nuevoSalario = Integer.parseInt( strSalario );
                empleado.cambiarSalario( nuevoSalario );
                panelSalario.actualizarSalario( empleado.darSalario( ) );
            }
            catch( NumberFormatException nfe )
            {
                JOptionPane.showMessageDialog( this, "El salario indicado no es válido." );
            }
        }
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método 1 de extensión del ejemplo
     */
    public void reqFuncOpcion1( )
    {
        String respuesta = empleado.metodo1( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método 2 de extensión del ejemplo
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = empleado.metodo2( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz e inicializándola con un empleado.
     * @param args Los argumentos no son utilizados.
     */
    public static void main( String[] args )
    {

        Fecha fechaNacimiento = new Fecha( );
        fechaNacimiento.inicializar( 16, 6, 1982 );
        
        Fecha fechaIngreso = new Fecha( );
        fechaIngreso.inicializar( 5, 4, 2000 );

        Empleado e = new Empleado( );
        e.inicializar( "Pedro", "Matallana", 1, fechaNacimiento, fechaIngreso, 1500000 );
      
        e.cambiarImagen( "data/pm.jpg" );

        InterfazEmpleado femp = new InterfazEmpleado( e );
        femp.actualizarEmpleado( );
        femp.setVisible( true );
    }

}