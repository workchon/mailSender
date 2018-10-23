
package mensajeria;

public class Datos {

    private String Destinatario;
    
    private String Remitente;
    
    private String Mensaje;
    
    private String Contraseña;
    
    private String Propiedad;
    
    private String Asunto;
    
    public Datos() {
    }

    public Datos(String Destinatario, String Remitente, String Mensaje, String Contraseña, String Propiedad, String Asunto) {
        this.Destinatario = Destinatario;
        this.Remitente = Remitente;
        this.Mensaje = Mensaje;
        this.Contraseña = Contraseña;
        this.Propiedad = Propiedad;
        this.Asunto=Asunto;
    }

    public String getDestinatario() {
        return Destinatario;
    }

    public String getRemitente() {
        return Remitente;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public String getContraseña() {
        return Contraseña;
    }
    
    public String getAsunto(){
        return Asunto;
    }
    
    
}
