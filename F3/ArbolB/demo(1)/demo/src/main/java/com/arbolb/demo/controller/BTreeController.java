package com.arbolb.demo.controller;

import com.arbolb.demo.model.BTree;
import com.arbolb.demo.model.BTreeNode;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/btree")
public class BTreeController {

    private final BTree btree = new BTree(2);

    @PostMapping("/insert")
    public BTreeNode insert(@RequestParam int key) {
        btree.insert(key);
        return btree.getRoot();
    }

    @GetMapping("/root")
    public BTreeNode getRoot() {
        return btree.getRoot();
    }

    @GetMapping("/search")
    public boolean search(@RequestParam int key) {
        return btree.search(key);
    }

    @DeleteMapping("/delete")
    public BTreeNode delete(@RequestParam int key) {
        btree.delete(key);
        return btree.getRoot();
    }
}
