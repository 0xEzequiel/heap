package aed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class InternetToolkit {
    public InternetToolkit() {
    }

    // Complejidad: O(n).
    public Fragment[] tcpReorder(Fragment[] fragments) {
        // Insertion sort
        int n = fragments.length;

        for (int i = 1; i < n; ++i) {
            Fragment elem = fragments[i];

            int j = i-1;
            while (j >= 0 && fragments[j].compareTo(elem) > 0) {
                fragments[j+1] = fragments[j];
                j = j-1;
            }

            fragments[j+1]=elem;
        }
        return fragments;
    }

    // Complejidad O(n + k log(n)), donde n es la cantidad de routers de entrada.
    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // Para cumplir las metas de complejidad utilizo una implementación
        // propia de un heap (ver Heap.java).
        Router[] res = new Router[k];
        Heap heap = new Heap(routers);
        int i = 0;

        while (i < k && heap.elemsdef() > 0) {
            Router max = heap.desencolar();
            if (max.getTrafico() >= umbral) {
                res[i] = max;
                i++;
            }
        }
        return res;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        IPv4Address[] res = new IPv4Address[ipv4.length];

        for (int i = 0; i < ipv4.length; i++) {
            // Convierto los elems a direcciones
            IPv4Address elem = new IPv4Address(ipv4[i]);
            res[i] = elem;
        }

        ordenarPorOcteto(res, 3);
        ordenarPorOcteto(res, 2);
        ordenarPorOcteto(res, 1);
        ordenarPorOcteto(res, 0);

        return res;
    }

    private void ordenarPorOcteto(IPv4Address[] arr, int idxOcteto) {
        for (int i = 1; i < arr.length; i++) {
            int j = i-1;
            IPv4Address elem = arr[i];
            while (j>=0 && arr[j].getOctet(idxOcteto) > elem.getOctet(idxOcteto)) {
                arr[j+1] = arr[j];
                j-=1;
            }
            arr[j+1] = elem;
        }
    }

}
