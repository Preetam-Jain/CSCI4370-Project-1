package uga.cs4370.mydb.impl;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class HashTable {

    private int totalElements = 0;
    private String attr;

    @SuppressWarnings("unchecked")
    private LinkedList<Node>[] index = (LinkedList<Node>[]) new LinkedList[16];

    public HashTable(String attr) {
        this.attr = attr;
        for (int i = 0; i < 16; i++) {
            index[i] = new LinkedList<Node>();
        } // creating array for buckets
    }

    public String getAttr() {
        return this.attr;
    }

    public void insert(long hash, int rowIndex) {
        Node value = new Node(rowIndex, hash);
        this.totalElements++;
        double averageChainLength = (double)totalElements / index.length;

        if (averageChainLength >= .75) { // if average chain length reaches .75 or above, rehash and create new buckets
            rehash(value);
        } else { // added node to hash table using hashed value 
            int nodeIndex = (int)(hash % index.length);
            index[nodeIndex].add(value);
        }

    }

    private void rehash(Node newNode) {
        @SuppressWarnings("unchecked")
        LinkedList<Node>[] newIndex = (LinkedList<Node>[]) new LinkedList[index.length * 2]; // creating new array of buckets with twice the size
        // creating new buckets
        for (int i = 0; i < newIndex.length; i++) {
            newIndex[i] = new LinkedList<Node>();
        }
        // copying over original buckets
        for (LinkedList<Node> bucket : this.index) {
            for (Node node : bucket) {
                long hash = node.getKey();
                int bucketIndex = (int)(hash % newIndex.length);
                newIndex[bucketIndex].add(node);
            }
        }
        // storing the new element from before
        long hash = newNode.getKey();
        int bucketIndex = (int)(hash % newIndex.length);
        newIndex[bucketIndex].add(newNode);
        this.index = newIndex;
    }

    public List<Integer> search(String value) { 
        // provides easy search by simply using hash value modulus'd as the index in the array 
        List<Integer> values = new ArrayList<>();
        long hash = RelationImpl.hash(value);
        int bucketIndex = (int)(hash % index.length); 
        LinkedList<Node> bucket = index[bucketIndex]; 
        // need to iterate through the bucket to check for matching hash keys
        for (Node node : bucket) {
            if (hash == node.getKey()) values.add(node.getIndex());
        }
        return values;
    }
}
