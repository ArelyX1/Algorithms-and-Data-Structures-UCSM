package com.arbolb.demo.model;


public class BTree {
    private BTreeNode root;
    private int t; // Grado mínimo

    public BTree(int t) {
        this.root = new BTreeNode(true);
        this.t = t;
    }

    public BTreeNode getRoot() {
        return root;
    }

    public void insert(int key) {
        BTreeNode r = root;
        if (r.keys.size() == 2 * t - 1) {
            BTreeNode s = new BTreeNode(false);
            s.children.add(r);
            splitChild(s, 0, r);
            insertNonFull(s, key);
            root = s;
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(BTreeNode node, int key) {
        int i = node.keys.size() - 1;
        if (node.leaf) {
            node.keys.add(0);
            while (i >= 0 && key < node.keys.get(i)) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, key);
        } else {
            while (i >= 0 && key < node.keys.get(i)) {
                i--;
            }
            i++;
            if (node.children.get(i).keys.size() == 2 * t - 1) {
                splitChild(node, i, node.children.get(i));
                if (key > node.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    private void splitChild(BTreeNode parent, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.leaf);
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.remove(t));
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }
        parent.children.add(i + 1, z);
        parent.keys.add(i, y.keys.remove(t - 1));
    }

    // Búsqueda
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(BTreeNode node, int key) {
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i++;
        }
        if (i < node.keys.size() && key == node.keys.get(i)) {
            return true;
        }
        if (node.leaf) {
            return false;
        } else {
            return search(node.children.get(i), key);
        }
    }

    // Eliminar (simplificado, solo elimina si está en una hoja)
    public void delete(int key) {
        delete(root, key);
        if (root.keys.size() == 0 && !root.leaf) {
            root = root.children.get(0);
        }
    }

    private void delete(BTreeNode node, int key) {
        int idx = 0;
        while (idx < node.keys.size() && key > node.keys.get(idx)) {
            idx++;
        }

        if (idx < node.keys.size() && node.keys.get(idx) == key) {
            if (node.leaf) {
                node.keys.remove(idx);
            } else {
                // Para simplificar, no eliminamos de nodos internos
                // (Implementación completa es más extensa)
            }
        } else {
            if (node.leaf) {
                // No encontrado
                return;
            } else {
                delete(node.children.get(idx), key);
            }
        }
    }
}
