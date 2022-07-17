package co.edu.poli.ces3.employees.servlets;

import co.edu.poli.ces3.employees.entities.Mascostas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "SrvlMascotas", value = "/SrvlMascotas")
public class SrvlMascotas extends HttpServlet {
    public static ArrayList<Mascostas> MASCOTAS = new ArrayList<>(Arrays.asList(
            new Mascostas(1212,"Lexon","is a happy","pitbull", false, false,
                    2, 16, "www.google.com"),
            new Mascostas(5656,"papa","is a beuatiful","pitbull", false, false,
                                  2, 16, "www.google.com"),
            new Mascostas(7878,"sedge","is a brother","pitbull", false, false,
                    2, 16, "www.google.com")
    ));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String idMascotas = request.getParameter("ID");

        if(idMascotas == null){
            out.print(gson.toJson(this.MASCOTAS)); //Pasar a formato Json
        } else {
            Mascostas result = searchMascota(Integer.parseInt(idMascotas));

            if(result != null){
                out.print(gson.toJson(result));
            } else {
                out.print(gson.toJson("Not found"));
            }
        }

        out.flush(); //Cerrar impresión
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        JsonObject body = JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();

        Mascostas mascota = new Mascostas(
                body.get("ID").getAsInt(),
                body.get("nombre").getAsString(),
                body.get("descripcion").getAsString(),
                body.get("raza").getAsString(),
                body.get("vacunado").getAsBoolean(),
                body.get("esteril").getAsBoolean(),
                body.get("disponible").getAsInt(),
                body.get("edad").getAsInt(),
                body.get("foto").getAsString()
        );



        this.MASCOTAS.add(mascota);

        out.print(gson.toJson("Mascota insertada existosamente"));
        out.print(gson.toJson(this.MASCOTAS));

        //employees.add(employee);

        //request.setAttribute("employees", employees);
        //request.getRequestDispatcher("views/employees/list.jsp").forward(request, response);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        JsonObject body = JsonParser.parseString(this.getParamsFromPost(request)).getAsJsonObject();

        boolean result = actualizarMascota(body);

        if(result){
            out.print(gson.toJson("Mascota actualizada existosamente"));
            out.print(gson.toJson(this.MASCOTAS));
        } else {
            out.print(gson.toJson("La mascota no existe!"));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        String idMascota = request.getParameter("id");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        if(idMascota == null){
            out.print(gson.toJson("Debe ingresar un ID"));
        } else {
            boolean result = eliminarMascota(Integer.parseInt(idMascota));

            if (result) {
                out.print(gson.toJson("Elemento eliminado existosamente"));
                out.print(gson.toJson(this.MASCOTAS));
            } else {
                out.print(gson.toJson("No se encontro la mascota!"));
            }
        }
    }

    private String getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String params = sb.toString();

        return params;
    }

    private Mascostas searchMascota(int mascotaId){
        ArrayList<Mascostas> myList = this.MASCOTAS;

        for(Mascostas cont : myList){

            if(cont.getID() == mascotaId){
                return cont;
            }
        }
        return null;
    }
    private boolean actualizarMascota(JsonObject body){
        ArrayList<Mascostas> myList = this.MASCOTAS;

        for(Mascostas cont : myList){
            if(cont.getID() == body.get("ID").getAsInt()){
                cont.setNombre(body.get("nombre").getAsString());
                cont.setDescripcion(body.get("descripcion").getAsString());
                cont.setRaza(body.get("raza").getAsString());
                cont.setVacunado(body.get("vacunado").getAsBoolean());
                cont.setEsteril(body.get("esteril").getAsBoolean());
                cont.setDisponible(body.get("disponible").getAsInt());
                cont.setEdad(body.get("edad").getAsInt());
                cont.setFoto(body.get("foto").getAsString());

                return true;
            }
        }
        return false;
    }

    private boolean eliminarMascota(int idMascota){
        ArrayList<Mascostas> myList = this.MASCOTAS;

        int contador = 0;
        for(Mascostas cont : myList){
            if(cont.getID() == idMascota){
                this.MASCOTAS.remove(contador);
                return true;
            }
            contador++;
        }
        return false;
    }
}
