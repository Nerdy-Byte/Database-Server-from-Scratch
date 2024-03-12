package index.bplusTree;

/*
    * Internal Node - num Keys | ptr to next free offset | P_1 | len(K_1) | K_1 | P_2 | len(K_2) | K_2 | ... | P_n
    * Only write code where specified
    * Bytes 0-1 number of keys
    * Bytes 2-3 pointer to the next free offset
    * Bytes 4-5 Left child pointer of the first key
 */
public class InternalNode<T> extends BlockNode implements TreeNode<T> {

    // Class of the key
    Class<T> typeClass;

    // Constructor - expects the key, left and right child ids
    public InternalNode(T key, int left_child_id, int right_child_id, Class<T> typeClass) {

        super();
        this.typeClass = typeClass;

        byte[] numKeysBytes = new byte[2];
        numKeysBytes[0] = 0;
        numKeysBytes[1] = 0;

        this.write_data(0, numKeysBytes);

        byte[] child_1 = new byte[2];
        child_1[0] = (byte) ((left_child_id >> 8) & 0xFF);
        child_1[1] = (byte) (left_child_id & 0xFF);

        this.write_data(4, child_1);

        byte[] nextFreeOffsetBytes = new byte[2];
        nextFreeOffsetBytes[0] = 0;
        nextFreeOffsetBytes[1] = 6;

        this.write_data(2, nextFreeOffsetBytes);

        // also calls the insert method
        this.insert(key, right_child_id);
        return;
    }

    @Override
    public T[] getKeys() {
        //this method finds all the keys stored in the the block and return them in the form of array
        int numKeys = getNumKeys(); //obtaining the key of the block
        T[] keys = (T[]) new Object[numKeys]; //create an array of keys of type T of length numKeys

        int offset = 6;
        for(int i=0; i<numKeys; i++){
            byte[] len = this.get_data(offset, 2);
            int l = (len[0]<<8) | (len[1]&0xff); //length of the ith key in the data block
            offset = offset+2;
            byte[] key = this.get_data(offset, l); //got the key

            offset = offset + l + 2; //updating the offset for next key
            //convert the byte into T and store it inside the keys array
            String k = new String(key);
            keys[i] = (T) k;
        }
        return keys;
    }

    @Override
    public void insert(T key, int right_block_id) {

        /* Write your code here */
        // either data can be entered directly
        // or we need to split the block and move to the parent block

    }

//    public int findk(int blkpt){
//
//    }

    @Override
    public int search(T key) {
        // for InternalNode, it will return the block id of the child node
        /* Write your code here */
        int numKeys = getNumKeys();
        int offset = 6;
        byte[] childp = this.get_data(4,2);
        int ck = (childp[0]<<8) | (childp[1]&0xff);
        for(int i=0; i<numKeys; i++){
            byte[] len = this.get_data(offset, 2);
            int l = (len[0]<<8) | (len[1]&0xff);
            offset = offset+2;
            byte[] keys = this.get_data(offset, l);
            offset = offset + l;
            byte[] child = this.get_data(offset,2);
            String k = new String(keys);
            T fk = (T) k;
            if(fk == key){
                return ck;
            }
            ck = (child[0]<<8) | (child[1]&0xff);
            offset = offset + 2;
        }

        return -1;
    }

    public int[] getChildren() {

        byte[] numKeysBytes = this.get_data(0, 2);
        int numKeys = (numKeysBytes[0] << 8) | (numKeysBytes[1] & 0xFF);

        int[] children = new int[numKeys + 1];

        /* Write your code here */

        return children;

    }

}
