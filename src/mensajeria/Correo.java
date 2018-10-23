
package mensajeria;

public class Correo {
    
    public Datos datos;

    public Correo() {
    }
    
    public Datos llenarDatos(String xDestinatario,String xRemitente,String xMensaje,String xContrasenia, String xPropiedad, String xAsunto)
    {
        return  datos = new Datos(xDestinatario, xRemitente, xMensaje, xContrasenia, xPropiedad, xAsunto);
    }
}
