package index.bplusTree;

import storage.AbstractBlock;

// Extends AbstractBlock, will be extended by InternalNode and LeafNode
public class BlockNode extends AbstractBlock {

    public BlockNode(byte[] data) {
        super(data);
    }

    public BlockNode() {
        super();
    }

    public int getNumKeys() { //read the byte representing the number of keys from the internal node's data
        byte[] numKeysBytes = this.get_data(0, 2);
        return (numKeysBytes[0] << 8) | (numKeysBytes[1] & 0xFF);
    }
}

/*
-> numKeysByte is a array of 2 Bytes with offset 0

    public byte[] get_data(int offset, int length) {
        if(offset + length > data.length){ # checking if request is valid or not
            return null;
        }
        byte[] result = new byte[length];  #length is used for creating the array
        System.arraycopy(data, offset, result, 0, length); # copies the array 'data' into array 'result'
                                                           # offset is the starting position in the data array from where coping begin
                                                           # 0 is the starting index in result from where coping is done
                                                           # length is the amount of data getting copied
        return result;
    }

-> this function copies the first two bytes from the block into the numKeysByte

-> number of keys are the first two byte of the data (Block) i.e. numKeysBytes[0]:numKeysBytes[1] it returns decimal value
-> first few bytes of the block contains the metadata about the block
*/