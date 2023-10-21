class Empleado {
    int id;
    String nombre;
    int supervisorId;  // ID del supervisor

    public Empleado(int id, String nombre, int supervisorId) {
        this.id = id;
        this.nombre = nombre;
        this.supervisorId = supervisorId;
    }
}

class NodoEmpleado {
    Empleado empleado;
    NodoEmpleado[] subordinados;

    public NodoEmpleado(Empleado empleado, int maxSubordinados) {
        this.empleado = empleado;
        this.subordinados = new NodoEmpleado[maxSubordinados];
    }
}

public class ArbolJerarquiaEmpleados {
    private NodoEmpleado raiz;

    public ArbolJerarquiaEmpleados(Empleado empleadoRaiz) {
        raiz = new NodoEmpleado(empleadoRaiz, 10); // Supongamos un máximo de 10 subordinados por empleado
    }

    public void agregarEmpleado(Empleado empleado, int supervisorId) {
        agregarEmpleadoRec(raiz, empleado, supervisorId);
    }

    private void agregarEmpleadoRec(NodoEmpleado nodo, Empleado empleado, int supervisorId) {
        if (nodo == null) {
            return;
        }
        if (nodo.empleado.id == supervisorId) {
            for (int i = 0; i < nodo.subordinados.length; i++) {
                if (nodo.subordinados[i] == null) {
                    nodo.subordinados[i] = new NodoEmpleado(empleado, 10);
                    return;
                }
            }
        }
        for (NodoEmpleado subordinado : nodo.subordinados) {
            agregarEmpleadoRec(subordinado, empleado, supervisorId);
        }
    }

    public void imprimirJerarquia() {
        imprimirJerarquiaRec(raiz, 0);
    }

    private void imprimirJerarquiaRec(NodoEmpleado nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        System.out.print("  ".repeat(nivel));
        System.out.println(nodo.empleado.nombre);
        for (NodoEmpleado subordinado : nodo.subordinados) {
            imprimirJerarquiaRec(subordinado, nivel + 1);
        }
    }

    public static void main(String[] args) {
        Empleado raizEmpleado = new Empleado(1, "CEO", 0);
        ArbolJerarquiaEmpleados arbol = new ArbolJerarquiaEmpleados(raizEmpleado);

        arbol.agregarEmpleado(new Empleado(2, "Gerente de Ventas", 1), 1);
        arbol.agregarEmpleado(new Empleado(3, "Gerente de Marketing", 1), 1);
        arbol.agregarEmpleado(new Empleado(4, "Vendedor 1", 2), 2);
        arbol.agregarEmpleado(new Empleado(5, "Vendedor 2", 2), 2);
        arbol.agregarEmpleado(new Empleado(6, "Diseñador 1", 3), 3);
        arbol.agregarEmpleado(new Empleado(7, "Diseñador 2", 3), 3);

        System.out.println("Jerarquía de Empleados:");
        arbol.imprimirJerarquia();
    }
}
