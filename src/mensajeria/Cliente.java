package mensajeria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Cliente {

    Connection DB;
    Statement command;
    ResultSet resultado;

    ArrayList listaServidores;
    ArrayList listaPropiedades;
    Correo corr = new Correo();

    public ArrayList getPropiedad() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/mensajeria", "cartero", "cartero");

            command = DB.createStatement();
            command.execute("Select * from Servidores");
            resultado = command.getResultSet();
            while (resultado.next()) {  
                listaPropiedades.add(resultado.getString("configuracion"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            DB.close();
        }
        return listaPropiedades;

    }

    public ArrayList getServidor() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {

//       llenado de la lista de servidores 
//        File archivo = new File("C:\\Servidores.txt");
//        FileReader fr = new FileReader(archivo);
//        BufferedReader br = new BufferedReader(fr);
//        ArrayList servidores = new ArrayList();
//        if (archivo.exists()) {
//            while(br.ready())
//            {
//                servidores.add(br.readLine());
//            }
//        }
//        else
//        {
//            //System.out.println("no hay archivo d servidores");
//        }
//        
//        return servidores;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/mensajeria", "cartero", "cartero");

            command = DB.createStatement();
            command.execute("Select * from Servidores");
            listaServidores=new ArrayList();
            resultado = command.getResultSet();
            while (resultado.next()) {
                listaServidores.add(resultado.getString("Servidor"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            listaServidores.add("No hay Servidores");
        } finally {
            DB.close();
        }

        return listaServidores;

    }

    public void Enviar(String xhost) {

        try {
            String host = xhost;
            String user = corr.datos.getRemitente();
            String pass = corr.datos.getContraseña();
            String to = corr.datos.getDestinatario();
            String from = corr.datos.getRemitente();
            String subject = corr.datos.getAsunto();
            String messageText = corr.datos.getMensaje();
            boolean sessionDebug = true;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "Outlook.office365.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            if (host.equals("smtp.gmail.com")) {
                props.put("mail.smtp.ssl.enabled", "false");
            }

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void AgregarServidor(String servidor, String Propiedad) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {

        //Agrega servidor y su propiedad al archivo de texto
//        File archivo = new File("C:\\Server.txt");
//        
//        String texto = servidor + "," + Propiedad + "\n";
//        if(!archivo.exists()){
//                //crea el archivo
//                archivo.createNewFile();
//            }
//        BufferedReader reader = new BufferedReader(new FileReader("C:\\Server.txt"));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            String wea="";
//            String[] ARR=new String[0];
//            String strLine="";
//        while((line = reader.readLine())!= null){
//                String[] ARRT=ARR;
//                ARR=new String[ARR.length+1];
//                for (int i = 0; i < ARRT.length; i++) {
//                        ARR[i]=ARRT[i];
//                    }
//                ARR[ARR.length-1]=line;
//                sb.append(line+"\n");
//                wea=line;
//            }
//        String wo = wea;
//        PrintStream Archivo = new PrintStream("C:\\Server.txt");
//            for (int i = 0; i < ARR.length; i++) {
//                    Archivo.print(ARR[i]);
//                }
//            
//            Archivo.print(texto);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/mensajeria", "cartero", "cartero");
            command = DB.createStatement();
            command.execute("insert into Servidores(Servidor,configuracion) values('" + servidor + "','" + Propiedad + "')");
             

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            DB.close();
        }

    }

}
