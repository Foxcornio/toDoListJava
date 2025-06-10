/*
Ejercicio: Simulación de una Lista de Tareas Pendientes (To-Do List)
📋 Enunciado:
Implementa un sistema de lista de tareas (To-Do List) usando una lista enlazada simple en Java. Cada tarea debe contener:
1.Un ID único.
2.Una descripción.
3.Una prioridad (alta, media, baja).
4.Un estado (pendiente o completada).
5.Deberás implementar las siguientes operaciones:
  a. Agregar tarea.
  b. Eliminar tarea por ID.
  c. Marcar una tarea como completada.
  d. Listar todas las tareas.
  e. Listar solo tareas pendientes.
  f. Ordenar las tareas por prioridad (alta > media > baja).
📦 Requisitos técnicos:
Implementa tu propia clase Nodo y ListaTareas.
a.No se permite el uso de estructuras ya hechas como ArrayList, LinkedList u otras de la biblioteca estándar para la lista principal.
b.Usa enumeraciones para los estados y prioridades.
*/
import java.util.Scanner;
enum Estado { PENDIENTE, COMPLETADA } //Sirvevpara manejar el estado de la tarea
enum Prioridad { ALTA, MEDIA, BAJA } // Sirve para asignar unabprioridad a la tarea

class Tarea { // Tarea::Se encarga de crear tareas
    String id, descripcion; // creamos 2 variables tipo string
    Estado estado; // creamos una variable tipo estado
    Prioridad prioridad; // Creamos una variablr tipo Prioridad

    public Tarea(String id, String descripcion, Prioridad prioridad) { // Solicitamos al cliente el id de labtarea, la descripcion de la tarea y la prioridad de la tarea
        this.id = id; // almacenamos el id ingresado en la variable creada al inicio de la clase
        this.descripcion = descripcion; // almacrnamos la descripcion ingresada en la variable creada al inicio de la clase
        this.estado = Estado.PENDIENTE; // Asignamos eñ estado Pendiente porque la tarea recien se creara
        this.prioridad = prioridad; // Almacenamos la prioridad ingresada por el usuario en nuestra variable prioridad.
    }

    public String toString() { // toString retorna un string con todos los datos de la tarea
        return id + ": " + descripcion + " [" + prioridad + ", " + estado + "]";
    }
}

class Nodo { // Almacena las tareas
    Tarea tarea; // Creamos una variable de tipo Tarea
    Nodo siguiente; // creamos una variable de tipo Nodo

    public Nodo(Tarea tarea) { // Solicitamos un dato de tipo tarea
        this.tarea = tarea; // almacenamos la tarea en nuestra variable tarea
        this.siguiente = null; // agregamos un valor nulo a la variable siguiente de tipo Nodo
    }
}

class ListaTareas { // ListaTareas::Se encarga de agregar, eliminar, cambiar el estado de las tareas, listarvlas tareas y ordenarlas por prioridad.
    Nodo cabeza; // creamos una variable cabeza de tipo nodo para la lista.

    public void agregar(String id, String desc, Prioridad p) { // metodo para agregar tareas a la lista solicitamos 2 datos de tipo string y 1 de tipo Prioridad
        Nodo nuevo = new Nodo(new Tarea(id, desc, p)); // Creamos una objeto de tipo Nodo  lo instanciamos al instanciarlo le pasamos un objeto de tipo Tarea el cual contiene los datos solicitados por el metodo
        if (cabeza == null) {
          cabeza = nuevo; // verificamos que nuestra variable de tipo Nodo sea nula para asignarle el objeto nuevo
        }else{ // en caso de que no sea nula 
            Nodo temp = cabeza; // creamos una variable de tipo Nodo que almacenara la cabeza
            while (temp.siguiente != null){ // mientras el atributo siguiente de la variable recien creada sea nulo ejecutaremos el siguiente codigo 
            temp = temp.siguiente;
            // la variable recien creada almacenara del atributo siguiente del nodo actual es decir si el nodo actual es la cabeza y el objeto cabeza ya tiene un objeto nodo en el atributo siguiente que es diferente de nulo entonces el atributo siguiente se almacenara en la variable recien creada temp
            }
            temp.siguiente = nuevo; // cuando temp. siguiente sea nulo se almacenara el nuevo objeto de tipo Nodo
        }
    }

    public boolean eliminar(String id) { // eliminar:: elimina un elemento de la lista de nodos y devuelve un booleano , el metodo solicita una variable de tipo string
        if (cabeza == null) return false; // Verificamos que la variable cabeza de tipo Nodo no sea nulo, en caso de ser nulo devolvemos un false
        if (cabeza.tarea.id.equals(id)) { // comparamos con el atributo id del objeto tarea que esta en la variable cabeza de tipo nodo con la variable ingresada por el cliente utilizando el metodo equals.
            cabeza = cabeza.siguiente; // En caso dr devolver true  almacenamos en la variable cabeza el atributo siguiente 
            return true; // retornamos true
        }
        Nodo temp = cabeza; // Si fallo el condicional anterior creamos una nueva variable temp de tipo nodo y le asignamos el objeto cabeza
        while (temp.siguiente != null && !temp.siguiente.tarea.id.equals(id))
            temp = temp.siguiente; // Con un ciclo while hacemos que si temp.siguiente es diferente de nulo y el metodo equals que compara el id del objeto tarea almacenado en el atributo siguiente retorna falso se ejecutara la linea de codigo que almacena el atributo siguiente en la variable temp.
        if (temp.siguiente == null) return false; // cuando temp.siguiente sea nulo retornaremos false
        temp.siguiente = temp.siguiente.siguiente; // en caso de que no sea nulo almacenaremos en temp.siguiente  el valor del atributo siguiente de elbsiguiente actual es decir si estamos en el objetoA almacenaremos en objetoA.siguiente[objetoB] el atributo siguiente osea objetoC
        return true; // una vez almacenado el nodo siguiente retornamos true
    }

    public boolean marcarCompletada(String id) { // marcarCompletada:: Este metodo devuelve un booleano y solicita una variable de tipo string su objetivo es cambiar el estado de una tarea
        Nodo temp = cabeza; // creamos una variable temp de tipo Nodo y le asignamos el objeto cabeza 
        while (temp != null) { //mientras temp sea diferente de nulo se ejecutara el siguiente bloque de codigo
            if (temp.tarea.id.equals(id)) { // si el metodo equals nos devuelve true despues de comparar el id del objeto tarea del nodo ejecutamos el siguiente codigo
                temp.tarea.estado = Estado.COMPLETADA; //cambiamos el estado de la tarea igualando el estado del objeto tarea con la constante Estado.COMPLETADA recuerda que el atributo estado del objeto tarea es de tipo Estado
                return true; // retornamos true
            }
            temp = temp.siguiente; // En caso de que el id no sea igual temp sera igual al atributo siguiente que almacena otra tarea.
        }
        return false; // Una vez terminado el ciclo terminamos el metodo y retornamos false
    }

    public void listar() { //listar::metodo vacio que solicita una variable booleana su objetivo es imprimir en pantalla las tareas como lista para el cliente
        Nodo temp = cabeza; //creamos una variable temp de tipo no y almacenamos el objeto cabeza
        while (temp != null) { // mientras temp sea distinto de nulo se ejecutara el siguiente codigo
                System.out.println(temp.tarea); // En caso de que cualquiera de las 2 condiciones se cumplan se imprimira en pantalla temp.tarea
             temp = temp.siguiente; // asignamos a la variable temp de tipo Nodo el atributo siguiente que es otro nodo con otra tarea.
        }
    }

    public void ordenarPorPrioridad() { // ordenarPorPrioridad::metodo de tipo vacio que ordena las tareas por prioridad
        if (cabeza == null || cabeza.siguiente == null) return; // si cabeza o su atributo siguientebson nulos terminamos el metodo.
        Nodo actual = cabeza, index = null; // en caso de que ningun condicional se cumpla almacenamos enbla variabl4 actual de tipo Nodo el objeto cabeza de tipo Nodo, y creamos otra variable llamada index von valor nulo
        while (actual != null) { // mientras actual sea diferente de nulo se ejecutara el siguiente codigo
            index = actual.siguiente; //almacenamos en nuestra variable index el atributo siguiente del nodo actual, el cual es otro nodo.
            while (index != null) { // mientras index sea diferente de nulo ejecutaremos el siguiente codigo
                if (index.tarea.prioridad.ordinal() < actual.tarea.prioridad.ordinal()) {//recuperamos el ordinal de la propiedad prioridad de las tareas de index y actual con el metodo ordinal(), en caso de que el ordinal de actual sea mayor a index se ejecutara el siguiente codigo
                    Tarea temp = actual.tarea; // Creamos un objeto temp de tipo Tarea y le asignamos el valor del atributo tarea de nuestro Nodo actual
                    actual.tarea = index.tarea; // remplazamos el atributo tarea de nuestro Nodo actual con el atributo tarea de nuestro nodo index
                    index.tarea = temp; // remplazamos el atributo tarea de nuestro nodo index con la Tarea temp
                }
                index = index.siguiente;  // si la condicion no se cumple remplazamos index con su nodo siguiente
            }
            actual = actual.siguiente; // una vez terminado el ciclo de index almacenamos el nodo siguiente en el nodo actual.
        }
    }
    
    public String detectarId(String id){ //detectarId::Este metodo busca el id ingresado para que no se repita en la lista enlazada devuelve un string y solicita un string
      if(cabeza==null){ // Si la cabeza es nula devolvemos 0
        return "0";
      }
      if(cabeza.tarea.id==id){ // si el id del objeto tarea del nodo cabeza es igual al id ingresado devolvemos el id
        return cabeza.tarea.id;
      }
      Nodo temp=cabeza; // Creamos una variable temp de tipo nodo para poder recorrer los elementos de la lista enlazada
      boolean existe=false; // Creamos una variable booleana para guardar el estado de si el id es igual a el de la tarea
      while(temp.siguiente!=null){ // mientras el nodo siguiente sea diferente de nulo se ejecutara el siguiente codigo
        temp=temp.siguiente; // temp sera igual a el atributo siguiente
        if(temp.tarea.id==id){ // Si el id de la tarea del nodo actual es igual al id ingresado
          existe=true; // le asignamos el valor true a existe
        }
      }
      if(!existe) return id; // si existe es falso retornamos el valor id ingresado
      return temp.tarea.id; // en caso de no ser falso retornamos el id de la tarea del nodo actual
    }
}

public class Usuario { // Usuario clase para contactar con el cliente
    public static void main(String[] args) { 
        ListaTareas lista = new ListaTareas();
        seleccionar(lista);
    }
    
    public static void seleccionar(ListaTareas lista){
      String opciones="Selecciona el numero de la opcion que deseas hacer con tus tareas:\n 1.Agregar Tareas. \n 2.Eliminar una tarea. \n 3.Concluir Tarea. \n 4.Mostrar Tareas. \n 5.Ordenar tareas por prioridad.\n 6.Salir";
        int opcionElegida;
        Scanner scanner=new Scanner(System.in);
        do{
          System.out.println(opciones);
          int opcion=scanner.nextInt();
          opcionElegida=opcion;
          Prioridad eleccion = Prioridad.ALTA;
          switch(opcion){
            case 1:
              scanner.nextLine();
              System.out.println("A continuacion escribe la tarea a realizar y selecciona la prioridad.\n");
              String desc = scanner.nextLine();
              
              System.out.println("Prioridad:\n1.Alta.\n2.Media.\n3.Baja");
              int prioridad=scanner.nextInt();
              
              int idRandom=(int)(Math.random() * 100);
              String id=Integer.toString(idRandom);
              String res=lista.detectarId(id);
              if(res==id){
                idRandom=Integer.parseInt(res);
                idRandom++;
              }
              id=Integer.toString(idRandom);
              switch(prioridad){
                case 1:
                  eleccion = Prioridad.ALTA;
                  break;
                case 2:
                  eleccion = Prioridad.MEDIA;
                  break;
                case 3:
                  eleccion = Prioridad.BAJA;
                  break;
              }
              lista.agregar(id,desc,eleccion);
              break;
            case 2:
              System.out.println("Digita el id de la tarea que deseas eliminar:");
              scanner.nextLine();
              String idTask=scanner.nextLine();
              if(!lista.eliminar(idTask)){
                System.out.println("No fue posible eliminar la tarea, c9ntacta con un administrador para solucionar el problema");
              }else{
                lista.listar();
                System.out.println("Tarea eliminada con exito.");
              }
              break;
            case 3:
              System.out.println("Digita el id de la tarea que deseas marcar como completada:");
              scanner.nextLine();
              idTask=scanner.nextLine();
              if(!lista.marcarCompletada(idTask)){
                System.out.println("No fue posible completar la tarea, contacta con un administrador para solucionar el problema");
              }else{
                lista.listar();
                System.out.println("Tarea completada con exito.");
              }
              break;
            case 4:
              lista.listar();
              break;
            case 5:
              lista.ordenarPorPrioridad();
              lista.listar();
              break;
          }
        } while(opcionElegida!=6 && opcionElegida<6 && opcionElegida!=0);
    }
}