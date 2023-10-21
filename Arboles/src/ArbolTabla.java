class Registro {
    int id;
    String nombre;
    int edad;

    public Registro(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
    }
}

class NodoRegistro {
    Registro registro;
    NodoRegistro izq;
    NodoRegistro der;

    public NodoRegistro(Registro registro) {
        this.registro = registro;
        this.izq = null;
        this.der = null;
    }
}

public class ArbolTabla {
    private NodoRegistro raiz;

    public ArbolTabla() {
        raiz = null;
    }

    public void insertar(Registro registro) {
        raiz = insertarRec(raiz, registro);
    }

    private NodoRegistro insertarRec(NodoRegistro raiz, Registro registro) {
        if (raiz == null) {
            return new NodoRegistro(registro);
        }

        if (registro.id < raiz.registro.id) {
            raiz.izq = insertarRec(raiz.izq, registro);
        } else if (registro.id > raiz.registro.id) {
            raiz.der = insertarRec(raiz.der, registro);
        }
        return raiz;
    }

    public Registro buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Registro buscarRec(NodoRegistro raiz, int id) {
        if (raiz == null || raiz.registro.id == id) {
            return (raiz != null) ? raiz.registro : null;
        }

        if (id < raiz.registro.id) {
            return buscarRec(raiz.izq, id);
        }

        return buscarRec(raiz.der, id);
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoRegistro raiz) {
        if (raiz != null) {
            inOrdenRec(raiz.izq);
            System.out.println(raiz.registro);
            inOrdenRec(raiz.der);
        }
    }

    public static void main(String[] args) {
        ArbolTabla tabla = new ArbolTabla();
        tabla.insertar(new Registro(1, "Juan", 25));
        tabla.insertar(new Registro(2, "María", 30));
        tabla.insertar(new Registro(3, "Carlos", 22));

        System.out.println("Registros en la tabla:");
        tabla.inOrden();

        int idBuscado = 2;
        Registro resultado = tabla.buscar(idBuscado);

        if (resultado != null) {
            System.out.println("\nRegistro encontrado: " + resultado);
        } else {
            System.out.println("\nRegistro con ID " + idBuscado + " no encontrado.");
        }
    }
}