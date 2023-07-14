package CuckooHash;

import java.util.Map;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

public class HashtableCuckoo<K, V> implements KWHashMap<K, V> {

    
    private Entry<K, V>[] table1;                       // 1st hash table
    private Entry<K, V>[] table2;                       // 2nd hash table
    private List<Entry<K, V> > overflow;                // list for the unaddable items
    private static final int START_CAPACITY = 100;      // static start capacity
    private double LOAD_THRESHOLD = 0.6;                // table percentage 
    private int tableSize = (int)(START_CAPACITY*LOAD_THRESHOLD);// actual table size
    private int numKeys;                                // # keys currently in entire cuckoo structure
 

  //  private int hash1 =    //hash function for table1??
   // private int hash2 =    //hash function for table2??


   
 /*For the constructor, you need to initialize the three structures; 
 * the tables and list. For the list, consider using an ArrayList.
 */
    public HashtableCuckoo() {

        table1 = new Entry[START_CAPACITY]; //capacity 0.6*START_CAPACITY
        table2 = new Entry[START_CAPACITY];              //capacity 0.6*START_CAPACITY
        overflow = new ArrayList<>();      //capacity of log2(n)

    }
    
        

  
    /** Contains key-value pairs for a hash table. */
    public static class Entry<K, V> implements Map.Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

    /**
         * Return a String representation of the Entry
         * @return a String representation of the Entry
         *         in the form key = value
         */
        @Override
        public String toString() {
            return key.toString() + "," + value.toString();
        }


    }
    /*
    * Returns the size of Cuckoo structure, which is NOT the capacity, 
    * but rather the number of elements currently in the tables and stash.
    */
    @Override
    public int size() {
        return numKeys; 
    }


    /*
    * Returns true if the Cuckoo structure has no elements currently in it.
    */
    @Override
    public boolean isEmpty() {
        if (this.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }



/*Determines the hash value of a given object,modulus thetableSize.  
To do so, it first takes thehashCode() of the given object. */
    private int getHash(Object o, int num){
        
        int hCode = o.hashCode();
        
        if (num == 1){
            hCode = hCode << 16 | hCode >>> 16; //try to flop the order to make unique
            return hCode % tableSize;   //possibly remove %tablesize
        }
        if (num == 0){
            return hCode % tableSize; 
        }
        
        else{
            return -1;
        }   
    }







    /*
    * Returns the value for the key, or null if the key is not found. 
    * We only ever need to check the 1 possible position in table 1, the 
    * 1 possible position in table 2, and/or overflow list
    */
    @Override
    public V get(Object key) {
        int index1 = getHash(key, 0);
        if (index1 <0){
            index1 += tableSize;
        }

        int index2 = getHash(key, 1);
        if (index2 <0){
            index2 += tableSize;
        }
        if (table2[index2].key == key){
            V val2 = table2[index2].value;
            return val2;
        }

        if (table1[index1].key == key){
            V val1 = table1[index1].value;
            return val1;
        }
        
        else {
            for (Entry<K, V> next : overflow){
                if (next.key == key){
                    return next.value;
                }
            }
        }
        return null;
        
    }
    
/*dds a given key to the Cuckoo structure, or updates the value if 
the key exists.  Returns the oldvalue, if the key existed, or null otherwise.*/
    @Override
    public V put(K key, V value) {  
        int index1 = getHash(key, 0);
        if (index1 <0){
            index1 += tableSize;
        }

        int index2 = getHash(key, 1);
        if (index2 <0){
            index2 += tableSize;
        }    


        if(table1[index1].key == key && table1[index1] != null){
            return table1[index1].setValue(value);
        }

        else if(table2[index2] != null && table2[index2].key == key){
            return table2[index2].setValue(value);
        }

        else if(!overflow.isEmpty()){
            for(Entry<K, V> next : overflow){
                if(next.key == key){
                    return next.setValue(value);
                }
            }
        }

        //helper to test the new entry spot is available or move it along
        boolean[][] helper = new boolean[2][tableSize]; 
        for(int i = 0; i < helper.length; i++){
            for(int j = 0; j < helper[i].length; j++){
                helper[i][j] = false;
            }
        }
        Entry<K, V> newE = new Entry<>(key, value);
        int indexNew = getHash(newE.key, 0);
        if(indexNew < 0){
            indexNew += tableSize;
        }

        if(helper[0][indexNew]){
            overflow.add(newE);
        }
        else {
            Entry<K, V> temp = table1[indexNew];
            table1[indexNew] = newE;
            newE = temp;
            helper[0][indexNew] = true;

            int counter = 1;
            int num = 1;
            while(newE != null && overflow.size()< numKeys+1){
                if(num == 0){
                    indexNew = getHash(newE.key, 0);

                    if(indexNew < 0){
                        indexNew += tableSize;
                    }

                    if(helper[0][indexNew]) {
                        overflow.add(newE);
                        break;
                    }
                    else {
                        temp = table1[indexNew];
                        table1[indexNew] = newE;
                        newE = temp;
                        helper[0][indexNew] = true;
                    }
                }
                else{
                    indexNew = getHash(newE.key, 1);
                    if(indexNew < 0){
                        indexNew += tableSize;
                    }

                    if(helper[1][indexNew] == true) {
                        overflow.add(newE);
                        break;
                    }
                    else{
                        temp = table2[indexNew];
                        table2[indexNew] = newE;
                        newE = temp;
                        helper[1][indexNew] = true;
                    }
                }
                
                counter++;
            }
            if(counter > (numKeys + 1)){
                overflow.add(newE);
            }
        }
        numKeys++;
        return null;
    }


/*
Removes the element associated with the key, and returns the associated value, 
or null if the keyis not found.To remove,  if the element is in either hashtable,  
we simply set it to null.  Then,  we have tocheck all of the elements in the overflow 
list to check if any of them can be put in the spot we justemptied.
*/

    @Override  
    public V remove(Object key) {
        int index2 = getHash(key, 1);
        int index1 = getHash(key, 0);
        
        if(index2 < 0){
            index2 += tableSize;
        }
        if(index1 < 0){
            index1 += tableSize;
        }

        if(table1[index1] != null && table1[index1].key == key){
            V dVal = table1[index1].value;
            table1[index1] = null;
            numKeys--;

            List<Entry<K, V>> DELETED = new ArrayList<>();

            for(Entry<K, V> next : overflow){
                int nextIndex = getHash(next.key, 0);
                if(nextIndex < 0){
                    nextIndex += tableSize;
                }

                if(nextIndex ==index1) {
                    table1[nextIndex] = next;
                    DELETED.add(next);
                    break;
                }
            }
            overflow.removeAll(DELETED);
            return dVal;
        }
        else if(table2[index2] != null && table2[index2].key == key){
            List<Entry<K, V>> DELETED = new ArrayList<>();

            V dVal = table2[index2].value;
            table2[index2] = null;
            numKeys--;
            for(Entry<K, V> next :overflow){
                int nextIndex = getHash(next.key, 1);
                
                if(nextIndex== index2) {
                    table2[nextIndex] = next;
                    DELETED.add(next);
                    break;
                }
                if(nextIndex <0){
                    nextIndex += tableSize;
                }
            }
            overflow.removeAll(DELETED);
            return dVal;
        }
        else {
            for(Entry<K, V> next: overflow){
                if(next.key == key){
                    V dVal = next.value;
                    overflow.remove(next);
                    numKeys--;
                    return dVal;
                }
            }
        }
        return null;
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i == tableSize; i++){
            if (table1[i] != null){
                sb.append("[" + i + "," + table1[i].toString() + ",table1]" + "\n");
            }
        }
        for (int i = 0; i == tableSize; i++){
            if (table2[i] != null){
                sb.append("[" + i + "," + table2[i].toString() + ",table2]" + "\n");
            }
        }
        for (int i = 0; i < tableSize; i++){
            if (!overflow.isEmpty()){
                sb.append("[" + i + "," + overflow.toString() + ",overflow]" + "\n");
            }
        }
        return sb.toString();

    }



    


   

}






