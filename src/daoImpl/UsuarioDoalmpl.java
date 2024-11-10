package daoImpl;

import model.Usuario;

public class UsuarioDoalmpl {
    private static final String FILE_USUARIOS = "usuarios.txt";
    private static final String FILE_IDSUSUARIOS = "idsusuarios.txt";
    Usuario[] usuarios = new Usuario[100];
    private Usuario[] stack;
    private int top;

    public UsuarioDoalmpl() {
        this.top = -1;
    }

    public void push(Usuario u) {
        if (top == usuarios.length - 1) {
            System.out.println("La pila esta llena");
        } else {
            top++;
            usuarios[top] = u;
        }

    }

    public Usuario pop() {
        if (top == -1) {
            return null;
        } else {
            top--;
            return usuarios[top + 1];

        }
    }

    public Usuario peek() {
        if (top == -1) {
            return null;
        } else {
            return usuarios[top];
        }
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void clear() {
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = null;
        }
        top = -1;
    }
    
}
