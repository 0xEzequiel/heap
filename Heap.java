package aed;

import aed.Router;

public class Heap {

    private  Router[] elems;
    private int elementosDefinidos;

    Heap(Router[] elems) {
        this.elems = new Router[elems.length];
        this.elementosDefinidos = elems.length;
        int indiceRaiz = 0;
        for (int i = indiceRaiz; i < elems.length; i++) {
            this.elems[i] = elems[i];
        }
        heapify(indiceRaiz);
    }

    public Router desencolar() {
        elementosDefinidos--;
        Router maximo = elems[0];
        elems[0] = elems[elementosDefinidos];
        int indiceRaiz = 0;
        bajarElem(indiceRaiz);
        return maximo;
    }

    private void heapify(int actual) {

        int n = elementosDefinidos;
        int idxHijoIzq = hijoIzquierdo(actual);

        if (idxHijoIzq < n) {
            heapify(idxHijoIzq);
        }

        int idxHijoDer = hijoDerecho(actual);

        if (idxHijoDer < n) {
            heapify(idxHijoDer);
        }

        bajarElem(actual);
    }

    public int elemsdef() {
        return elementosDefinidos;
    }

    private int hijoDerecho(int indicePadre) {
        return 2*indicePadre+2;
    }

    private int hijoIzquierdo(int indicePadre) {
        return 2*indicePadre+1;
    }

    private void bajarElem(int padre) {
        int idxHijoIzq = hijoIzquierdo(padre);
        int n = elementosDefinidos;
        // Si no es hoja
        if (idxHijoIzq < n) {
            int idxHijoDer = hijoDerecho(padre);
            if (idxHijoDer < n) {
                elementoConDosHijos(padre, idxHijoIzq, idxHijoDer);
            } else {
                elementoConHijoUnico(padre, idxHijoIzq);
            }
        }
    }


    private void elementoConHijoUnico(int padre, int idxHijoIzq) {
        if (claveEsMayor(idxHijoIzq, padre)) {
            swap(idxHijoIzq, padre);
        }
    }

    private void elementoConDosHijos(int padre, int idxHijoIzq, int idxHijoDer) {
        if (claveEsMayor(idxHijoIzq, padre) || claveEsMayor(idxHijoDer, padre)) {
            if (claveEsMayor(idxHijoIzq, idxHijoDer)) {
                swap(idxHijoIzq, padre);
                bajarElem(idxHijoIzq);
            } else {
                swap(idxHijoDer, padre);
                bajarElem(idxHijoDer);
            }
        }
    }

    private void swap(int actual, int indicePadre) {
        Router temp = elems[indicePadre];
        elems[indicePadre] = elems[actual];
        elems[actual] = temp;
    }

    private boolean claveEsMayor(int actual, int indicePadre) {
        return elems[actual].compareTo(elems[indicePadre]) == 1;
    }
}
